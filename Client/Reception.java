package Client;

import java.io.BufferedReader;

public class Reception implements Runnable {

	private BufferedReader in;
	private String message = null;
	public static boolean serverOnline = true;
	
	public Reception(BufferedReader in){	
		this.in = in;
	}
	
	public void run() {
		
		while(true){
	        try {
	        	
			message = in.readLine();
			
			//Si le serveur s'est déconnecté
        	if(message == null){
        		Terminal.out("Le serveur s'est déconnecté.", Color.RED);
        		serverOnline = false;
        		break;
        	}
        	
        	Terminal.out(message);		
			
		    } catch (Exception e) {	
		    	serverOnline = false;
        		break;
			}
		}
	}

}
