package Serveur;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public String login = "";
	public BufferedReader in = null;
	public PrintWriter out = null;
	
	public Client(Socket socket) {
		try {
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter(socket.getOutputStream());
		
		} catch (Exception e) {				
			System.out.println(login + " ne répond pas.");
		}
	}
	
	public boolean Exists(){
		boolean exists = false;
		
		for(Client c : Serveur.clientsList){
			if(c.login.equals(this.login)){
				exists = true;
				break;
			}		
		}
		
		return exists;		
	}
	
	public static Client getClient(String login) {
		Client client = null;
		
		for(Client c : Serveur.clientsList){
			if(c.login.equals(login)){
				client = c;
				break;
			}		
		}
		
		return client;
	}
}
