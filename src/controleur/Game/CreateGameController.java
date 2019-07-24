package controleur.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.BadCoordException;
import info1.ships.NavyFleet;
import info1.ships.UncompleteFleetException;
import vue.FenetrePrincipale;

public class CreateGameController implements ActionListener{
	private FenetrePrincipale vue;
	private Network net;
	private Player player;
	private NavyFleet flotte;
	private Game currentGame;

	private String baseurl;

	public CreateGameController(FenetrePrincipale vue, Player player) {
		this.vue = vue;
		this.player = player;
		this.baseurl = "http://"+this.vue.getIp()+":8888/api/v0";
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			currentGame = Network.initNewGame(baseurl,this.player,this.vue.getFlotte());
			this.vue.setGame(currentGame);
			this.vue.setVisibleChooseShipPanel(false);
			this.vue.setVisibleGuestGrillePanel(true);
			this.vue.setUnSelectedRadio();
			this.vue.setVisibleRightPanelGame(true);
			this.vue.setVisibleRightPanelLobby(false);
			System.out.println("Partie créee");
			this.vue.playSound(4);
			boolean firstTime = true;

			new java.util.Timer().schedule(
					new java.util.TimerTask() {
						@Override
						public void run() {
							try {
								int requestValue = Network.getInfo(baseurl, currentGame, player);
								if(requestValue == 1){
									vue.setAdversaireName("Aucun");
								}else{
									if (firstTime){
										for(Game game : net.listInitializedGames(baseurl)){
											if(game.getId() == currentGame.getId()){
												vue.setAdversaireName(game.getGuest().getName());
											}
										}
									}
								}
								if(requestValue == 10){
									vue.myTurnToPlay(true);
								}else if(requestValue == -10){
									vue.myTurnToPlay(false);
								}
							}catch (Exception erreur){
							}
						}
					},
					1000,1000
			);
		} catch (UncompleteFleetException erreur) {
			this.vue.setErrorCreate("Erreur flotte incomplète");
		}catch (BadCoordException erreur){
			System.out.println("Coordonnées érronées");
		}catch (UnirestException erreur){
			System.out.println("Erreur innatendue");
		}


	}
}
