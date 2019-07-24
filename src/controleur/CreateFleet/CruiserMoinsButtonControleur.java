package controleur.CreateFleet;

import vue.CreateFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CruiserMoinsButtonControleur implements ActionListener {

    private CreateFleet vue;

    public CruiserMoinsButtonControleur(CreateFleet vue){
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int value = vue.getCruiserValue() <= 1 ? 0 : vue.getCruiserValue()-1;
        vue.setCruiserTextField(value+"");
        vue.setValueTotal();

    }
}
