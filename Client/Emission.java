package Client;
import java.io.PrintWriter;
import java.util.Scanner;

public class Emission implements Runnable {

	private PrintWriter out;
	private String message = null;
	public static Scanner sc = null;
	private String login;
	
	public Emission(PrintWriter out, String login) {
		this.out = out;
		this.login = login;
	}

	public void run() {
		
		sc = new Scanner(System.in);
		  
		while(Reception.serverOnline) {
			try {
				System.out.print(login + " : ");
				message = sc.nextLine();
				
				if(message.trim().length() > 0) {	
					out.println(message);
					out.flush();
				}	
				else {
					System.out.print(Terminal.CURSOR_PREVIOUS_LINE);
					System.out.print(Terminal.DELETE_LINE);
				}			
			} catch(Exception ex) {
                break;
			}			
		}
	}
}