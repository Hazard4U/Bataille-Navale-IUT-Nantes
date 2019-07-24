package controleur.CreateFleet;

import vue.CreateFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattlePlusButtonControleur implements ActionListener {

    private CreateFleet vue;

    public BattlePlusButtonControleur(CreateFleet vue){
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        vue.setBattleTextField((vue.getBattleValue()+1)+"");
        vue.setValueTotal();
    }
}
