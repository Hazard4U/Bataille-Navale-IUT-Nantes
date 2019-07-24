package controleur.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import info1.network.Network;
import info1.network.Player;
import vue.CreateFleet;
import vue.FenetrePrincipale;

public class NewFleetController implements ActionListener{
	private FenetrePrincipale vue;
	private Network n;
	private Player p;
	public NewFleetController(FenetrePrincipale vue, Network n, Player p) {
		this.vue = vue;
		this.n = n;
		this.p = p;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		CreateFleet createfleet = new CreateFleet("Bataille navale", this.vue, this.n,this.p, this.vue.getIp());
		createfleet.setSize(1250, 700);
		createfleet.setResizable(false);
		createfleet.setVisible(true);
        this.vue.dispose();
	}
}
