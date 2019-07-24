package controleur.CreateFleet;

import vue.CreateFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AircraftMoinsButtonControleur implements ActionListener {

    private CreateFleet vue;

    public AircraftMoinsButtonControleur(CreateFleet vue){
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int value = vue.getAircraftValue() <= 1 ? 0 : vue.getAircraftValue()-1;
        vue.setAircraftTextField(value+"");

        vue.setValueTotal();
    }
}
