package controleur.CreateFleet;

import vue.CreateFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DestroyerMoinsButtonControleur implements ActionListener {

    private CreateFleet vue;

    public DestroyerMoinsButtonControleur(CreateFleet vue){
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int value = vue.getDestroyerValue() <= 1 ? 0 : vue.getDestroyerValue()-1;
        vue.setDestroyerTextField(value+"");
        vue.setValueTotal();

    }
}
