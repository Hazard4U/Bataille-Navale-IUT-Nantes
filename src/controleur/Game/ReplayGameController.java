package controleur.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.BadCoordException;
import info1.ships.NavyFleet;
import info1.ships.UncompleteFleetException;
import vue.FenetrePrincipale;

public class ReplayGameController implements ActionListener{
    private FenetrePrincipale vue;
    private Player player;

    public ReplayGameController(FenetrePrincipale vue, Player player) {
        this.vue = vue;
        this.player = player;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        FenetrePrincipale principale = new FenetrePrincipale("Bataille navale", player, null, this.vue.getIp());
        principale.setSize(1250,700);
        principale.setResizable(false);
        principale.setVisible(true);
        this.vue.dispose();

    }
}
