package controleur.CreateFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import info1.network.Player;
import vue.CreateFleet;
import vue.FenetrePrincipale;

public class SetShipsNumberController implements ActionListener {
	private Player p;
	private CreateFleet vue;
	
	public SetShipsNumberController(CreateFleet vue,  Player p) {
		this.p = p;
		this.vue = vue;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.vue.getValueTotal() != 20) {
			this.vue.setCapacityError("La place totale occupée par vos bateaux doit être égale à 20");
		} else {
			int[] bateaux = new int[5];
			/* 0 = aircraft
			 * 1 = battle
			 * 2 = cruiser
			 * 3 = destroyer
			 * 4 = submarine
			 */
			
			bateaux[0] = this.vue.getAircraftValue();
			bateaux[1] = this.vue.getBattleValue();
			bateaux[2] = this.vue.getCruiserValue();
			bateaux[3] = this.vue.getDestroyerValue();
			bateaux[4] = this.vue.getSubmarineValue();
			
			FenetrePrincipale principale = new FenetrePrincipale("Bataille navale",this.p, bateaux, this.vue.getIp());
            principale.setSize(1250, 700);
            principale.setResizable(false);
            principale.setVisible(true);
            this.vue.dispose();
			
		}
		
	}

}
