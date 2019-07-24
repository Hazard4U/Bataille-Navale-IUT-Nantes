package info1.ships;

/**
 * Classe définissant un navire de catégorie "sous-marin" (taille 1)
 * @author lanoix-a
 */

public class Submarine extends Ship {

    public Submarine(String name, String xy)
            throws BadCoordException, CoordsBadShipException {
        super(name, xy, xy);
    }

    @Override
    public ShipCategory getCategory() {
        return ShipCategory.SUBMARINE;
    }

}
