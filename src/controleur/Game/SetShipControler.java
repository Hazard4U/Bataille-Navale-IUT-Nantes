package controleur.Game;

import info1.ships.*;
import vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.*;
import java.io.UncheckedIOException;
import java.sql.SQLOutput;

public class SetShipControler implements MouseListener, KeyListener {
    private FenetrePrincipale vue;
    private NavyFleet modele;

    private static Ship currentShip;
    private Coord avant;
    private Coord arriere;
    private int rotation;
    private int size;
    private String currentJLabelText;

    public SetShipControler(FenetrePrincipale vue,NavyFleet flotte){
    this.vue = vue;
    this.modele = flotte;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'r'){
            rotation = (rotation + 1) % 4;
            if(currentJLabelText != ""){
                unsetAnimation();
                setAnimation();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try{

            if (this.vue.getSelectedShip() != -1) {
                shipCondition();
                this.modele.add(currentShip);
                this.vue.setSelectedShipValue((this.vue.getSelectedShipValue()-1)+"");
                setAnimation();
                System.out.println("Ajout navire");
            }
        }catch (Exception erreur){
            System.out.println("Impossible d'ajouter le bateau");
        }finally {
            if(this.modele.isComplete()){
                this.vue.enableCreateGameButton(true);
            }
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
        if(this.vue.getSelectedShip() != -1){
                currentJLabelText = ((JLabel)((JPanel)e.getSource()).getComponent(0)).getText();
                setAnimation();

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(this.vue.getSelectedShip() != -1){
            unsetAnimation();
            currentShip = null;
        }
    }

    private void setArrive() throws BadCoordException{
        int arriveX = 0;
        int arriveY = 0;
        switch (rotation) {
            case 0:
                System.out.println("Switch 0");
                arriveX = avant.getX() + (size-1);
                arriveY = avant.getY();
                break;
            case 1:
                System.out.println("Switch 1");
                arriveX = avant.getX();
                arriveY = avant.getY() - (size-1);
                break;
            case 2:
                System.out.println("Switch 2");
                arriveX = avant.getX() - (size-1);
                arriveY = avant.getY();
                break;
            case 3:
                System.out.println("Switch 3");
                arriveX = avant.getX();
                arriveY = avant.getY() + (size-1);
                break;
        }
        arriere = new Coord(this.vue.getAlphaX(arriveX) + arriveY);
    }

    private Ship buildShip(int size) throws BadCoordException,CoordsBadShipException{
        switch (size) {
            case 1:return new Submarine("AIRCRAFT_CARRIER",avant.toString());
            case 2: return new Destroyer("AIRCRAFT_CARRIER",avant.toString(),arriere.toString());
            case 3: return new Cruiser("AIRCRAFT_CARRIER",avant.toString(),arriere.toString());
            case 4: return new Battleship("AIRCRAFT_CARRIER",avant.toString(),arriere.toString());
            case 5: return new AircraftCarrier("AIRCRAFT_CARRIER",avant.toString(),arriere.toString());
            default: return new Submarine("AIRCRAFT_CARRIER",avant.toString());
        }
    }

    private void setAnimation(){
        try {
            shipCondition();
            this.vue.setPanelBackgroundColor(1,currentShip.getCoords(),true,true,false);
        }catch (BadCoordException erreur){
            System.out.println("Impossible coordonnée éronnée au survol");
            currentShip = null;
        }catch (CoordsBadShipException erreur){
            System.out.println("Impossible de créer le bateau Coor");
            currentShip = null;
        }catch (NullPointerException erreur){
            erreur.printStackTrace();
            System.out.println("Impossible de créer le bateau Null");
            currentShip = null;
        }catch (Exception erreur){
            System.out.println(erreur.getMessage());
            this.vue.setPanelBackgroundColor(2,currentShip.getCoords(),true,true,false);
        }
    }

    private void unsetAnimation(){
        if(currentShip != null){
            this.vue.setPanelBackgroundColor(3,currentShip.getCoords(),true,true,false);
        }
    }

    private void shipCondition() throws BadCoordException,CoordsBadShipException,NullPointerException,Exception{
        size = this.vue.getSelectedShip();
        System.out.println("Taille du bateau selectionné : " + size);

        avant = new Coord(currentJLabelText);
        System.out.println("Case de l'avant : " + avant);


        /*this.setArrive();
        System.out.println("Case de l'arrière : " + arriere);

        currentShip = buildShip(size);*/
        try{
            this.setArrive();
            System.out.println("Case de l'arrière : " + arriere);

            currentShip = buildShip(size);
        }catch (Exception erreur){
            boolean valide = false;
            while(!valide){
                try{
                    this.setArrive();

                    currentShip = buildShip(size);
                    System.out.println("Case de l'arrière : " + arriere);
                    valide = true;
                }catch (Exception erreur2){
                    size--;
                }
            }
            throw new Exception("Bateau trop grand");
        }



        if(this.vue.getSelectedShipValue() <= 0){
            throw new Exception("Plus de bateau");
        }
        if(!this.modele.isFree(currentShip.getCoords())){
            throw new Exception("Bateaux superposés");
        }
        if(this.modele.isComplete()){
            throw new Exception("Flotte complète");
        }
    }
}
