package Serveur;
import java.net.*;
import java.io.*;

public class Authentification implements Runnable {

	private Socket socket;
	private Client client;
	private int loginReturnCode = 1;
	public BufferedReader in = null;
	public Thread reception;
	
	public Authentification(Socket s) {
		socket = s;
	}
	
	public void run() {
	
		try {
			client = new Client(socket);
			
			while(loginReturnCode != 0) {
				
				client.out.println("Entrez votre login : ");
				client.out.flush();			
				client.login = client.in.readLine();
				
				//Le pseudo doit comprendre entre 3 et 16 caractère
				if(client.login.replaceAll("\\s+","").length() < 3 || client.login.replaceAll("\\s+","").length() > 16)
					loginReturnCode = 1;
					
				//Le pseudo ne doit pas être déjà utilisé
				else if(client.Exists())
					loginReturnCode = 2;
				
				//Gestion des espaces
				else if(!client.login.replaceAll("\\s+","").equals(client.login))
					loginReturnCode = 3;
				
				else 
					loginReturnCode = 0;
					
				client.out.println(loginReturnCode);
				client.out.flush();		
					
			}
			
			Terminal.out(client.login + " (" + socket.getInetAddress().getHostAddress() + ") vient de se connecter.", Color.GREEN);
			Emission.sendMessageToAll(client.login + " vient de se connecter.", Color.GREEN);
			Serveur.clientsList.add(client);			
			
			reception = new Thread(new Reception(client));
			reception.start();	
			
			

		} catch (Exception e) {
			
			Terminal.out(socket.getInetAddress().getHostName() + " a abandonné la phase d'authentification.", Color.RED);
		}
	}
}