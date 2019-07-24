package controleur.CreateFleet;

import vue.CreateFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmarinePlusButtonControleur implements ActionListener {

    private CreateFleet vue;

    public SubmarinePlusButtonControleur(CreateFleet vue){
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        vue.setSubmarineTextField((vue.getSubmarineValue()+1)+"");
        vue.setValueTotal();
    }
}
