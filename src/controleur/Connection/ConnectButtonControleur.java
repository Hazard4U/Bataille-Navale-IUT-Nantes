package controleur.Connection;

import vue.Connection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mashape.unirest.http.exceptions.UnirestException;

import info1.network.Network;
import info1.network.Player;
import vue.FenetrePrincipale;


public class ConnectButtonControleur implements ActionListener {
    private Connection vue;
    private Network net;
    
    public ConnectButtonControleur(Connection vue, Network net){
        this.vue = vue;
        this.net= net;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	Player currentPlayer = new Player(vue.getName());
    	
    	try {
			boolean result = net.suscribeNewPlayer("http://"+this.vue.getIp()+":8888/api/v0", currentPlayer);
			if(result) {
				FenetrePrincipale principale = new FenetrePrincipale("Bataille navale", currentPlayer, null, this.vue.getIp());
	            principale.setSize(1250,700);
	            principale.setResizable(false);
	            principale.setVisible(true);
	            this.vue.dispose();
			} else {
				this.vue.showErrorPseudo("Un joueur avec ce nom existe déjà");
			}
		} catch (UnirestException e1) {
			this.vue.showErrorIP("Le serveur est fermé");
		} catch (RuntimeException erreur){
    		erreur.printStackTrace();
			this.vue.showErrorIP("L'IP est incorrecte");
		}
    }
}
