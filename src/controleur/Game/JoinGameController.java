package controleur.Game;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.BadCoordException;
import info1.ships.NavyFleet;
import info1.ships.UncompleteFleetException;
import vue.FenetrePrincipale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JoinGameController implements ActionListener {
    private FenetrePrincipale vue;
    private Network net;
    private Player player;
    private NavyFleet flotte;
    private Game currentGame;

    private String baseurl;

    public JoinGameController(FenetrePrincipale vue, Player player) {
        this.vue = vue;
        this.net = net;
        this.player = player;
        this.baseurl = "http://"+this.vue.getIp()+":8888/api/v0";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            currentGame = this.vue.getSelectedGame();
            boolean gameJoined = Network.joinGame(baseurl,currentGame,this.player,this.vue.getFlotte());
            System.out.println("Game pushed" + currentGame);
            this.vue.setGame(currentGame);
            if(gameJoined){
                this.vue.setVisibleChooseShipPanel(false);
                this.vue.setVisibleGuestGrillePanel(true);
                this.vue.setUnSelectedRadio();
                this.vue.setVisibleRightPanelGame(true);
                this.vue.setVisibleRightPanelLobby(false);
                System.out.println("Partie rejoins");
                this.vue.playSound(4);
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    int requestValue = net.getInfo(baseurl, currentGame, player);
                                    if(requestValue == 1){
                                        vue.setAdversaireName("Aucun");
                                    }else{
                                        vue.setAdversaireName(currentGame.getInitiator().getName());
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
            }


        } catch (UncompleteFleetException erreur) {
            this.vue.setErrorJoin("Erreur flotte incomplète");
        }catch (BadCoordException erreur){
            System.out.println("Coordonnées érronées");
        }catch (UnirestException erreur){
            System.out.println("Erreur innatendue");
        }catch (NullPointerException erreur){
            this.vue.setErrorJoin("Erreur aucune partie sélectionnée");
            System.out.println("Game null");
        }
    }
}
