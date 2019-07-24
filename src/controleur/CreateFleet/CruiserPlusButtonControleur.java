package controleur.CreateFleet;

import vue.CreateFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CruiserPlusButtonControleur implements ActionListener {

    private CreateFleet vue;

    public CruiserPlusButtonControleur(CreateFleet vue){
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        vue.setCruiserTextField((vue.getCruiserValue()+1)+"");
        vue.setValueTotal();

    }
}
