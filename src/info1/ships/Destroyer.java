package info1.ships;


/**
 * classe définissant un navire de catégorie "Tropilleur" (taille 2)
 * @author lanoix-a
 */

public class Destroyer extends Ship {

    public Destroyer(String name, String xyFront, String xyBack)
            throws BadCoordException, CoordsBadShipException {
        super(name, xyFront, xyBack);
    }

    @Override
    public ShipCategory getCategory() {
        return ShipCategory.DESTROYER;
    }

}
