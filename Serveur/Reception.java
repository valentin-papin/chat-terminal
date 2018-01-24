package Serveur;

public class Reception implements Runnable {

    private String message = null;
    private Client client;
    
    public Reception(Client client){
        this.client = client;
    }
	
	public void run() {
		
		while(true){
	        try {
	        	
	        	message = client.in.readLine();   
	        	
	        	//Si le client s'est déconnecté
	        	if(message == null){
	        		Terminal.out(client.login + " s'est déconnecté.", Color.YELLOW);
	        		Emission.sendMessageToAll(client.login + " s'est déconnecté.", Color.YELLOW);
	        		Serveur.clientsList.remove(client);
	        		break;
	        	}
	        	
	        	Terminal.out(client.login + " : " + message);
				Emission.relayMessageToClients(client, message);
				
		    } catch (Exception e) {
		    	Terminal.out(client.login + " s'est déconnecté.", Color.YELLOW);
        		Emission.sendMessageToAll(client.login + " s'est déconnecté.", Color.YELLOW);
        		Serveur.clientsList.remove(client);
        		break;
			}
		}
	}
}
