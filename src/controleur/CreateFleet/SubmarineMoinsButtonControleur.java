package controleur.CreateFleet;

import vue.CreateFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmarineMoinsButtonControleur implements ActionListener {

    private CreateFleet vue;

    public SubmarineMoinsButtonControleur(CreateFleet vue){
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int value = vue.getSubmarineValue() <= 1 ? 0 : vue.getSubmarineValue()-1;
        vue.setSubmarineTextField(value+"");
        vue.setValueTotal();

    }
}
