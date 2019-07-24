package controleur.CreateFleet;

import vue.CreateFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AircraftPlusButtonControleur implements ActionListener {

    private CreateFleet vue;

    public AircraftPlusButtonControleur(CreateFleet vue){
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        vue.setAircraftTextField((vue.getAircraftValue()+1)+"");
        vue.setValueTotal();
    }
}
