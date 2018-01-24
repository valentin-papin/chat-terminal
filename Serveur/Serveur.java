package Serveur;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Serveur {

	private static ServerSocket serverSocket = null;
	private static Socket socket = null;
	public static ArrayList<Client> clientsList = new ArrayList<Client>();
	public static Thread authenticationThread;
	public static Thread emissionThread;

	public static void main(String[] args) {

		//Lance le serveur sur le port 59500
        try {
        	serverSocket = new ServerSocket(getServerPort());
            System.out.println("Le serveur est à l'écoute du port " + serverSocket.getLocalPort());

        } catch (IOException e) {
        	System.out.println("Le port " + serverSocket.getLocalPort() + " est déjà utilisé !");
        }

        System.out.println("PID du serveur : " + getPID());

        //On lance le Thread pour envoyer les messages
        emissionThread = new Thread(new Emission());
        emissionThread.start();
         
        //Accepte la connexion des clients
        try {
			while(true) {
				
				socket = serverSocket.accept();
				Terminal.out("Nouvelle demande de connexion de " + socket.getInetAddress().getHostName() 
						     + " (" + socket.getInetAddress().getHostAddress() + ")", Color.YELLOW);
				
				authenticationThread = new Thread(new Authentification(socket));
				authenticationThread.start();
			}
		} catch (Exception e) {
			
			Terminal.out("Erreur lors de la connexion d'un client");
		}	
	}

	public static long getPID() {
		String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
		return Long.parseLong(processName.split("@")[0]);
	}
	
	public static int getServerPort() {
		StringBuilder result = new StringBuilder();
	    String port;
	    
	    //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cache-etu.univ-artois.fr", 3128)); 
	    
	    
		try{
			URL url = new URL("http://www.valentinpapin.fr/port");    
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		      
		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      	      
	        while ((port = rd.readLine()) != null)
	        	result.append(port);
	      
	        rd.close();
		}
		catch (Exception e){}
	       
	    return (Integer.parseInt(result.toString()));
	}
}
