package controleur.CreateFleet;

import vue.CreateFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DestroyerPlusButtonControleur implements ActionListener {

    private CreateFleet vue;

    public DestroyerPlusButtonControleur(CreateFleet vue){
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        vue.setDestroyerTextField((vue.getDestroyerValue()+1)+"");
        vue.setValueTotal();

    }
}
