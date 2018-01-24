package Serveur;

public class Custom {
	
	public static boolean isCustom(String message) {
		if(message.substring(0, 1).equals("/")){
			
			if(message.equals("/who"))
				who();
			else if(message.contains("/mp"))
				mp(message);
			else if(message.equals("/help"))
				help();
			else
				error(message);
			
			

			return true;
		}
		else
			return false;
	}
	
	
	public static void who(){
		if(Serveur.clientsList.isEmpty())
			Terminal.out("Aucun client connecté.", Color.YELLOW);
		else {
			Terminal.out("Client(s) connecté(s) :", Color.CYAN);
			for(Client client : Serveur.clientsList) {
				Terminal.out(client.login, Color.CYAN + Terminal.BOLD_FONT);
			}
		}	
	}
	
	public static void mp(String message) {
		String dest = null;
		String msg = null;
		boolean isValid = false;
				
		String parts[] = message.split("\\s+", 2);
		
		if(parts.length == 2) {
			String destAndMsg[] = parts[1].split(" ", 2);
			
			if(destAndMsg.length == 2) {
				dest = destAndMsg[0];
				msg = destAndMsg[1];
				
				if(msg.trim().length() == 0)
					Terminal.out("/mp : Le message est vide.", Color.YELLOW);
				else
					isValid = true;
			} else
				Terminal.out("/mp : message manquant.", Color.YELLOW);	
		} else
			Terminal.out("/mp : destinataire et message manquants.", Color.YELLOW);
		
		
		if(isValid) {
			Client client = Client.getClient(dest);
			
			if(client != null) {
				client.out.println(Color.BLUE + "de " + Terminal.BOLD_FONT + "Serveur" + Terminal.NORMAL_FONT + " : " + msg + Color.RESET);
				client.out.flush();
			}
			else
				Terminal.out(dest + " : destinataire introuvable.", Color.YELLOW);			
		}	
	}
	
	public static void help() {
		Terminal.out("Commandes disponibles :", Color.YELLOW);
		Terminal.out("/who : affiche les clients connectés.", Color.YELLOW);
		Terminal.out("/mp [destinataire] [message] : envoie un message privé.", Color.YELLOW);
		Terminal.out("/time : affiche l'heure de réception des messages dans le chat.", Color.YELLOW);
		Terminal.out("/help : affiche les commandes disponibles.", Color.YELLOW);
	}
	
	public static void error(String commande) {
		Terminal.out(commande + " : commande non reconnue.", Color.YELLOW);
		Terminal.out("Tapez /help pour afficher la liste des commandes disponibles.", Color.YELLOW);
	}
}
