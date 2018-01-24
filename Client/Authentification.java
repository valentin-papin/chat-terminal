package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Authentification implements Runnable {

	private Socket socket = null;
	private Thread reception, emission;
	private String login = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Scanner sc;
	private boolean connect = false;
	
	public Authentification(Socket socket){	
		this.socket = socket;
	}
	
	public void run() {
		
		try {	
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
			sc = new Scanner(System.in);
		
			while(!connect){
			
				System.out.print(in.readLine());
				login = sc.nextLine();
				out.println(login);
				out.flush();
								
				//Action en fonction du code de retour envoyé par la serveur		
				switch(in.readLine()) {
					case "0": Terminal.out("Bienvenue " + login + " !", Color.GREEN);
							  connect = true;
							  break;
					case "1": Terminal.out("Le pseudo doit comprendre entre 3 et 16 caractères.", Color.YELLOW);
							  break;
					case "2": Terminal.out("Ce pseudo est déjà utilisé.", Color.YELLOW);
							  break;
					case "3": Terminal.out("Le pseudo ne doit comporter aucun espace.", Color.YELLOW);
							  break;
					default: Terminal.out("Erreur de connexion inconnue.", Color.YELLOW);
							 break;
				}
			}
			
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			reception = new Thread(new Reception(in));
			reception.start();
			emission = new Thread(new Emission(out, login));
			emission.start();
		
		} catch (Exception e) {	
			Terminal.out("Le serveur ne répond pas.", Color.RED);
		}
	}

}
