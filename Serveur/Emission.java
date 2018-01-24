package Serveur;
import java.util.Scanner;

public class Emission implements Runnable {

	private String message = null;
	private Scanner sc = null;
	
	public void run() {
		sc = new Scanner(System.in);
		  
		while(true) {
			System.out.print(Color.RED + "Serveur : " + Color.RESET);
			message = sc.nextLine();
			
			if(message.trim().length() > 0) {	
				if(!Custom.isCustom(message)){
					for(Client client : Serveur.clientsList){
						client.out.println(Color.RED + "Serveur : " + message + Color.RESET);
						client.out.flush();
					}	
				}
			}	
			else {
				System.out.print(Terminal.CURSOR_PREVIOUS_LINE);
				System.out.print(Terminal.DELETE_LINE);
			}				
		}	
	}
	
	public static void relayMessageToClients(Client clientEmetteur, String message){
		for(Client clientRecepteur : Serveur.clientsList){
			
			if(!clientRecepteur.login.equals(clientEmetteur.login)){
				clientRecepteur.out.println(clientEmetteur.login + " : " + message);
				clientRecepteur.out.flush();
			}				
		}
	}
	
	public static void relayMessageToClients(Client clientEmetteur, String message, String color){
		for(Client clientRecepteur : Serveur.clientsList){
			
			if(!clientRecepteur.login.equals(clientEmetteur.login)){
				clientRecepteur.out.println(color + clientEmetteur.login + " : " + message + Color.RESET);
				clientRecepteur.out.flush();
			}				
		}
	}
	
	public static void sendMessageToAll(String message){
		for(Client client : Serveur.clientsList){
			client.out.println(message);
			client.out.flush();
		}		
	}
	
	public static void sendMessageToAll(String message, String color){
		for(Client client : Serveur.clientsList){
			client.out.println(color + message + Color.RESET);
			client.out.flush();
		}		
	}
}