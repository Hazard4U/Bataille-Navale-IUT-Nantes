package info1.ships;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * une implémentation "abstraite" d'un bateau quelconque, de taille indéterminée
 */


public abstract class Ship implements IShip {

    private String name;
    private Coord coordAvant;
    private Coord coordArriere;



    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * construit un bateau quelconque
     * @param name le nom du bateau
     * @param ayFront la coordonnée de la proue du bateau
     * @param ayBack la coordonnée de la poupe du bateau
     * @throws BadCoordException si l'une des coordonnées données ne définit pas une coordonnée alphanumérique correcte
     * @throws CoordsBadShipException si les coordonnées données ne permettent pas de définir un bateau correct :
     *  une ligne, une colonne, de la bonne taille, etc.
     */
    public Ship(String name, String ayFront, String ayBack)
            throws BadCoordException, CoordsBadShipException {

        this.name = name;

        this.coordAvant = new Coord(ayFront);
        this.coordArriere = new Coord(ayBack);


        if(this.coordAvant.getX() == this.coordArriere.getX()){
            int size = Math.abs(this.coordArriere.getY() - this.coordAvant.getY()) +1;
            if(size != this.getSize()){
                throw new CoordsBadShipException();
            }
        }else if(this.coordAvant.getY() == this.coordArriere.getY()){
            int size = Math.abs(this.coordArriere.getX() - this.coordAvant.getX()) +1;
            System.out.println(size);
            System.out.println(this.getSize());
            if(size != this.getSize()){
                throw new CoordsBadShipException();
            }
        }else{
            throw new CoordsBadShipException();
        }
    }


    @Override
    public List<ICoord> getCoords() {
        List<ICoord> coords = new LinkedList<>();
        if (this.coordAvant.getX() == this.coordArriere.getX()){

            if(this.coordArriere.getY() < this.coordAvant.getY()){
                for (int i = this.coordAvant.getY(); i >= this.coordArriere.getY(); i--){
                    try{
                        coords.add(new Coord(this.coordAvant.getAlphaX() + "" + i));
                    }catch (BadCoordException erreur){
                        System.out.println("Erreur coordonnée");
                    }
                }
            }else{
                for (int i = this.coordAvant.getY(); i <= this.coordArriere.getY(); i++){
                    try{
                        coords.add(new Coord(this.coordAvant.getAlphaX() + "" + i));
                    }catch (BadCoordException erreur){
                        System.out.println("Erreur coordonnée");
                    }
                }
            }
        }else{
            if(this.coordArriere.getX() < this.coordAvant.getX()){
                for (int i = this.coordAvant.getX(); i >= this.coordArriere.getX(); i--){
                    try{
                        coords.add(new Coord(getAlphaX(i) + "" + this.coordAvant.getY()));
                    }catch (BadCoordException erreur){
                        System.out.println("Erreur coordonnée");
                    }
                }
            }else{
                for (int i = this.coordAvant.getX(); i <= this.coordArriere.getX(); i++){
                    try{
                        coords.add(new Coord(getAlphaX(i) + "" + this.coordAvant.getY()));
                    }catch (BadCoordException erreur){
                        System.out.println("Erreur coordonnée");
                    }
                }
            }
        }

        return coords;
    }

    @Override
    public ICoord getFront() {
        return coordAvant;
    }

    @Override
    public ICoord getBack() {
        return coordArriere;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return this.getCategory().getSize();
    }

    @Override
    public String toString() {
        return this.name + " :\ntaille = " + this.getSize() + "\navant = " + this.coordAvant.toString() + "\narriere = " + this.coordArriere.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return name.equals(ship.name) &&
                coordAvant.equals(ship.coordAvant) &&
                coordArriere.equals(ship.coordArriere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordAvant, coordArriere);
    }

    private String getAlphaX(int entier){
        char alphaX = 'A';
        switch (entier){
            case 1: return "A";
            case 2: return "B";
            case 3: return "C";
            case 4: return "D";
            case 5: return "E";
            case 6: return "F";
            case 7: return  "G";
            case 8: return "H";
            case 9: return "I";
            case 10: return "J";
            default: return "A";
        }
    }
}

