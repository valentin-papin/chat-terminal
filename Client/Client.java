package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URL;

public class Client {

	public static Socket socket = null;
	public static Thread authenticationThread;
	public static String version = "1.0.0";
	
	public static void run() {
		
		try {
			System.out.println(Color.YELLOW + "Demande de connexion ... " + Color.RESET);
			String serverIP = getServerIP();
			int serverPort = getServerPort();
			System.out.println(Color.CYAN + "Serveur localisé à : " + serverIP + ":" + serverPort + Color.RESET);
			
			socket = new Socket(serverIP,serverPort);
			//socket = new Socket("localhost",59500);
			System.out.println(Color.GREEN + "Connexion établie avec le serveur." + Color.RESET);
			
			authenticationThread = new Thread(new Authentification(socket));
			authenticationThread.start();
			
		} catch (Exception e) {
			System.out.println(Color.RED + "Impossible de se connecter au serveur." + Color.RESET);	
		}
	}
	
	public static String getServerIP() throws Exception {
	      StringBuilder result = new StringBuilder();
	      String line;
	      
	      URL url = new URL("http://www.valentinpapin.fr/ip");    
	      //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cache-etu.univ-artois.fr", 3128));    
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      	      
	      while ((line = rd.readLine()) != null)
	         result.append(line);
	      
	      rd.close();
	      return result.toString();
	}
	
	public static int getServerPort() throws Exception {
	      StringBuilder result = new StringBuilder();
	      String port;
	      
	      URL url = new URL("http://www.valentinpapin.fr/port");    
	      //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cache-etu.univ-artois.fr", 3128));    
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      	      
	      while ((port = rd.readLine()) != null)
	         result.append(port);
	      
	      rd.close();
	      return (Integer.parseInt(result.toString()));
	}
}