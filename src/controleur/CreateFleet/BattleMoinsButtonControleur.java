package controleur.CreateFleet;

import vue.CreateFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleMoinsButtonControleur implements ActionListener {

    private CreateFleet vue;

    public BattleMoinsButtonControleur(CreateFleet vue){
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int value = vue.getBattleValue() <= 1 ? 0 : vue.getBattleValue()-1;
        vue.setBattleTextField(value+"");
        vue.setValueTotal();

    }
}
