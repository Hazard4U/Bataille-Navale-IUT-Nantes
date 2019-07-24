package controleur.Game;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.BadCoordException;
import info1.ships.Coord;
import info1.ships.ICoord;
import vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ShotShipControler implements MouseListener {
    private FenetrePrincipale vue;

    private Game currentGame;
    private Player currentPlayer;
    private Network net;

    private String baseurl;

    public ShotShipControler(FenetrePrincipale vue, Network net, Player currentPlayer) {
        this.vue = vue;
        this.currentPlayer = currentPlayer;
        this.currentGame = this.vue.getGame();
        this.baseurl = "http://" + this.vue.getIp() + ":8888/api/v0";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String currentCoord = ((JLabel) ((JPanel) e.getSource()).getComponent(0)).getText();
        List<ICoord> list = new ArrayList<>();
        this.currentGame = this.vue.getGame();
        System.out.println(currentGame);
        try {
            int requestValue = net.getInfo(baseurl, currentGame, currentPlayer);
            System.out.println(requestValue);
            if (requestValue == 10) {
                Coord coord = new Coord(currentCoord);
                int shotValue = Network.playOneTurn(baseurl, currentGame, currentPlayer, coord);
                System.out.println("Tir : " + shotValue);
                list.add(coord);
                if (shotValue == 0) {
                    this.vue.setPanelBackgroundColor(2, list, false, false, true);
                    this.vue.playSound(1);
                } else if (shotValue == 1) {
                    System.out.println("Touche");
                    this.vue.setTextCaseRestanteValue((this.vue.getTextCaseRestanteValue() - 1) + "");
                    this.vue.setPanelBackgroundColor(1, list, false, false, true);
                    this.vue.playSound(2);
                } else if (shotValue == 10) {
                    System.out.println("Coule");
                    this.vue.setTextCaseRestanteValue((this.vue.getTextCaseRestanteValue() - 1) + "");
                    this.vue.setTextBateauCouleValue((this.vue.getTextBateauCouleValue() + 1) + "");
                    this.vue.setPanelBackgroundColor(1, list, false, false, true);
                    this.vue.playSound(2);
                } else if (shotValue == 100) {
                    System.out.println("Gagne");
                    this.vue.setTextCaseRestanteValue((this.vue.getTextCaseRestanteValue() - 1) + "");
                    this.vue.setTextBateauCouleValue((this.vue.getTextBateauCouleValue() + 1) + "");
                    this.vue.setPanelBackgroundColor(1, list, false, false, true);
                    this.vue.enableReplayButton(true);
                    this.vue.setEndGameMessage("Gagné", Color.GREEN);
                    this.vue.playSound(3);
                }
            }
        } catch (BadCoordException erreur) {
            System.out.println("Coordonnée de tir invalide");
        } catch (UnirestException erreur) {
            System.out.println("Erreur innatendu");
        } catch (BadIdException erreur) {
            System.out.println("Erreur network");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println(currentGame);
        String label = ((JLabel) ((JPanel) e.getSource()).getComponent(0)).getText();
        setAnimation(label, true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println(currentGame);
        String label = ((JLabel) ((JPanel) e.getSource()).getComponent(0)).getText();
        setAnimation(label, false);
    }

    private void setAnimation(String label, boolean entered) {

        try {
            System.out.println(currentGame);
            String currentCoord = label;
            System.out.println("label" + label);
            Coord coord = new Coord(currentCoord);

            List<ICoord> list = new ArrayList<>();
            list.add(coord);
            if (entered) {
                this.currentGame = this.vue.getGame();
                int requestValue = net.getInfo(baseurl, currentGame, currentPlayer);
                System.out.println(requestValue);
                if (requestValue == 100) {
                    return;
                } else if (requestValue == -100) {
                    this.vue.enableReplayButton(true);
                    this.vue.setEndGameMessage("Perdu", Color.RED);
                } else if (requestValue != 10) {
                    System.out.println("Impossible de tirer");
                    this.vue.setPanelBackgroundColor(2, list, false, false, false);
                } else {
                    System.out.println("Possible de tirer");
                    this.vue.setPanelBackgroundColor(1, list, false, false, false);
                }
            } else {
                System.out.println("LIGHT GREY");
                this.vue.setPanelBackgroundColor(4, list, false, false, false);
            }


        } catch (BadCoordException erreur) {
            System.out.println("Coordonnée de tir invalide");
        } catch (UnirestException erreur) {
            System.out.println("Erreur innatendu");
        } catch (BadIdException erreur) {
            System.out.println("Erreur network");
        }
    }


}

