package Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class Chat {

	private static String version;
	
	public static void main(String[] args) {
		
		System.out.println(Color.CYAN + "Chat v" + Client.version + Color.RESET);
				
		try {
			version = getServerVersion();
			
			//Si le client est à jour
			if(version.equals(Client.version)) {
				System.out.println(Color.GREEN + "Le client est à jour.");
				Client.run();
			}
			else {
				System.out.println(Color.YELLOW + "Mise à jour vers la version " + version + " ..." + Color.RESET);
				
				File currentDir = new File(System.getProperty("user.dir"));
				
				for (File f : currentDir.listFiles()) {
				    if (f.getName().endsWith(".class") && !f.getName().equals("Chat.class")) {
				        f.delete();
				    }
				}
				
				//FileUtils.copyURLToFile(URL, File)
				
				
				
				
			}
		} catch (Exception e) {
			System.out.println(Color.RED + "Impossible de vérifier la version du client." + Color.RESET);
		}
	}
	
	public static String getServerVersion() throws Exception {
	      StringBuilder result = new StringBuilder();
	      String line;
	      
	      URL url = new URL("http://val.papin.free.fr/chat/version");    
	      //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cache-etu.univ-artois.fr", 3128));    
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      	      
	      while ((line = rd.readLine()) != null)
	         result.append(line);
	      
	      rd.close();
	      return result.toString();
	}
}
