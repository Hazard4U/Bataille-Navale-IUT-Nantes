package info1.ships;

/**
 * classe définissant un navire de catégorie "Cuirassé" (taille 4)
 * @author lanoix-a
 */

public class Battleship extends Ship {

    public Battleship(String name, String xyFront, String xyBack)
            throws BadCoordException, CoordsBadShipException {
        super(name, xyFront, xyBack);
    }

    @Override
    public ShipCategory getCategory() {
        return ShipCategory.BATTLESHIP;
    }

}
