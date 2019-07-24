package info1.ships;


/**
 * Classe définissant un navire de catégorie "Croiseur" (taille 3)
 * @author lanoix-a
 */

public class Cruiser extends Ship {

    public Cruiser(String name, String xyFront, String xyBack)
            throws BadCoordException, CoordsBadShipException {
        super(name, xyFront, xyBack);
    }

    @Override
    public ShipCategory getCategory() {
        return ShipCategory.CRUISER;
    }

}
