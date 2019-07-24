
import info1.ships.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ShipsTest {

    /**Test les méthodes de la classe AIRCRAFT_CARRIER
     * @param nom le nom du bateau
     * @param front la case représentant l'avant du bateau
     * @param back la case représentant l'arrière du bateau
     * @param OracleNom l'Oracle qui contient le nom du bateau
     * @param OracleFront l'Oracle qui contient la case représentant l'avant du bateau
     * @param OracleBack l'Oracle qui contient la case représentant l'arrière du bateau
     * @throws CoordsBadShipException Exception relative à la validité des coordonnées choisies par rapport au bateau
     * @throws BadCoordException Exception relative à la validité des coordonnées
     */
    @ParameterizedTest
    @MethodSource("AIRCRAFTProvider")
    void testAircraftMethodes (String nom, String front, String back, String OracleNom ,String OracleFront, String OracleBack) throws CoordsBadShipException,BadCoordException {
        AircraftCarrier air1=new AircraftCarrier(nom,front,back);

        String resName=String.valueOf(air1.getName());
        String resFront=String.valueOf(air1.getFront());
        String resBack=String.valueOf(air1.getBack());

        assertEquals(OracleNom,resName,"name = ");
        assertEquals(OracleFront,resFront,"front = ");
        assertEquals(OracleBack,resBack,"back = ");
    }

    static Stream AIRCRAFTProvider(){
        return Stream.of(
                Arguments.of("AIRCRAFT_CARRIER","B1","B5","AIRCRAFT_CARRIER","B1","B5"),
                Arguments.of("AIRCRAFT_CARRIER","C1","G1","AIRCRAFT_CARRIER","C1","G1")
        );
    }

    /**Test du constructeur de la classe AIRCRAFT_CARRIER
     */
    @Test
    void testConstruAircraftBadShip(){
        Assertions.assertThrows(CoordsBadShipException.class,
                () ->{ AircraftCarrier air=new AircraftCarrier("AIRCRAFT_CARRIER", "A2", "A8");});
    }

    /**Test du constructeur de la classe AIRCRAFT_CARRIER
     */
    @Test
    void testConstruAircraftBadCoord(){
        Assertions.assertThrows(BadCoordException.class,
                () ->{ AircraftCarrier air=new AircraftCarrier("AIRCRAFT_CARRIER", "A12", "A8");});
    }

    /*------------------------------------------------------------------*/


    /**Test les méthodes de la classe BATTLESHIP
     * @param nom le nom du bateau
     * @param front la case représentant l'avant du bateau
     * @param back la case représentant l'arrière du bateau
     * @param OracleNom l'Oracle qui contient le nom du bateau
     * @param OracleFront l'Oracle qui contient la case représentant l'avant du bateau
     * @param OracleBack l'Oracle qui contient la case représentant l'arrière du bateau
     * @throws CoordsBadShipException Exception relative à la validité des coordonnées choisies par rapport au bateau
     * @throws BadCoordException Exception relative à la validité des coordonnées
     */
    @ParameterizedTest
    @MethodSource("BATTLESHIPProvider")
    void testBattleshipMethodes (String nom, String front, String back, String OracleNom ,String OracleFront, String OracleBack) throws CoordsBadShipException,BadCoordException {
        Battleship bat1=new Battleship(nom,front,back);

        String resName=String.valueOf(bat1.getName());
        String resFront=String.valueOf(bat1.getFront());
        String resBack=String.valueOf(bat1.getBack());

        assertEquals(OracleNom,resName,"name = ");
        assertEquals(OracleFront,resFront,"front = ");
        assertEquals(OracleBack,resBack,"back = ");
    }

    static Stream BATTLESHIPProvider(){
        return Stream.of(
                Arguments.of("BATTLESHIP","B1","B4","BATTLESHIP","B1","B4"),
                Arguments.of("BATTLESHIP","C1","F1","BATTLESHIP","C1","F1")
        );
    }

    /**Test du constructeur de la classe BATTLESHIP
     */
    @Test
    void testConstruBattleshipBadShip(){
        Assertions.assertThrows(CoordsBadShipException.class,
                () ->{ Battleship bat1=new Battleship("BATTLESHIP", "A2", "A3");});
    }

    /**Test du constructeur de la classe BATTLESHIP
     */
    @Test
    void testConstruBattleshipBadCoord(){
        Assertions.assertThrows(BadCoordException.class,
                () ->{ Battleship bat1=new Battleship("BATTLESHIP", "A12", "A8");});
    }


    /*------------------------------------------------------------------*/


    /**Test les méthodes de la classe CRUISER
     * @param nom le nom du bateau
     * @param front la case représentant l'avant du bateau
     * @param back la case représentant l'arrière du bateau
     * @param OracleNom l'Oracle qui contient le nom du bateau
     * @param OracleFront l'Oracle qui contient la case représentant l'avant du bateau
     * @param OracleBack l'Oracle qui contient la case représentant l'arrière du bateau
     * @throws CoordsBadShipException Exception relative à la validité des coordonnées choisies par rapport au bateau
     * @throws BadCoordException Exception relative à la validité des coordonnées
     */
    @ParameterizedTest
    @MethodSource("CRUISERProvider")
    void testCruiserMethodes (String nom, String front, String back, String OracleNom ,String OracleFront, String OracleBack) throws CoordsBadShipException,BadCoordException {
        Cruiser crui1=new Cruiser(nom,front,back);

        String resName=String.valueOf(crui1.getName());
        String resFront=String.valueOf(crui1.getFront());
        String resBack=String.valueOf(crui1.getBack());

        assertEquals(OracleNom,resName,"name = ");
        assertEquals(OracleFront,resFront,"front = ");
        assertEquals(OracleBack,resBack,"back = ");
    }

    static Stream CRUISERProvider(){
        return Stream.of(
                Arguments.of("CRUISER","B1","B3","CRUISER","B1","B3"),
                Arguments.of("CRUISER","C1","E1","CRUISER","C1","E1")
        );
    }

    /**Test du constructeur de la classe CRUISER
     */
    @Test
    void testConstruCruiserBadShip(){
        Assertions.assertThrows(CoordsBadShipException.class,
                () ->{ Cruiser crui1=new Cruiser("CRUISER", "A1", "A2");;});
    }

    /**Test du constructeur de la classe CRUISER
     */
    @Test
    void testConstruCruiserBadCoord(){
        Assertions.assertThrows(BadCoordException.class,
                () ->{ Cruiser crui1=new Cruiser("CRUISER", "A12", "A8");;});
    }


    /*------------------------------------------------------------------*/


    /**Test les méthodes de la classe DESTROYER
     * @param nom le nom du bateau
     * @param front la case représentant l'avant du bateau
     * @param back la case représentant l'arrière du bateau
     * @param OracleNom l'Oracle qui contient le nom du bateau
     * @param OracleFront l'Oracle qui contient la case représentant l'avant du bateau
     * @param OracleBack l'Oracle qui contient la case représentant l'arrière du bateau
     * @throws CoordsBadShipException Exception relative à la validité des coordonnées choisies par rapport au bateau
     * @throws BadCoordException Exception relative à la validité des coordonnées
     */
    @ParameterizedTest
    @MethodSource("DESTROYERProvider")
    void testDestroyerMethodes (String nom, String front, String back, String OracleNom ,String OracleFront, String OracleBack) throws CoordsBadShipException,BadCoordException {
        Destroyer des1=new Destroyer(nom,front,back);

        String resName=String.valueOf(des1.getName());
        String resFront=String.valueOf(des1.getFront());
        String resBack=String.valueOf(des1.getBack());

        assertEquals(OracleNom,resName,"name = ");
        assertEquals(OracleFront,resFront,"front = ");
        assertEquals(OracleBack,resBack,"back = ");
    }

    static Stream DESTROYERProvider(){
        return Stream.of(
                Arguments.of("DESTROYER","B1","B2","DESTROYER","B1","B2"),
                Arguments.of("DESTROYER","C1","D1","DESTROYER","C1","D1")
        );
    }

    /**Test du constructeur de la classe DESTROYER
     */
    @Test
    void testConstruDestroyerBadShip(){
        Assertions.assertThrows(CoordsBadShipException.class,
                () ->{ Cruiser crui1=new Cruiser("DESTROYER", "A1", "A4");;});
    }

    /**Test du constructeur de la classe DESTROYER
     */
    @Test
    void testConstruDestroyerBadCoord(){
        Assertions.assertThrows(BadCoordException.class,
                () ->{ Cruiser crui1=new Cruiser("DESTROYER", "A12", "A8");;});
    }


    /*------------------------------------------------------------------*/


    /**Test de la classe SUBMARINE
     * @param nom le nom du bateau
     * @param xy la case représentant l'avant/l'arrière du bateau
     * @param OracleNom l'Oracle qui contient le nom du bateau
     * @param Oraclexy l'Oracle qui contient la case représentant l'avant/l'arrière du bateau
     * @throws CoordsBadShipException Exception relative à la validité des coordonnées choisies par rapport au bateau
     * @throws BadCoordException Exception relative à la validité des coordonnées
     */
    @ParameterizedTest
    @MethodSource("SUBMARINEProvider")
    void testSubmarineMethodes (String nom, String xy, String OracleNom ,String Oraclexy) throws CoordsBadShipException,BadCoordException {
        Submarine sub1=new Submarine(nom,xy);

        String resName=String.valueOf(sub1.getName());
        String resFront=String.valueOf(sub1.getFront());
        String resBack=String.valueOf(sub1.getBack());

        assertEquals(OracleNom,resName,"name = ");
        assertEquals(Oraclexy,resFront,"xy = ");
        assertEquals(Oraclexy,resBack,"xy = ");
    }

    static Stream SUBMARINEProvider(){
        return Stream.of(
                Arguments.of("SUBMARINE","A1","SUBMARINE","A1")
        );
    }

    /**Test du constructeur de la classe SUBMARINE
     */
    @Test
    void testConstruSubmarineBadCoord(){
        Assertions.assertThrows(BadCoordException.class,
                () ->{ Submarine sub1=new Submarine("SUBMARINE", "A12");;});
    }

    /**
     * Test la méthode getCategory() de chaque sous-classe de ships
     * @throws BadCoordException Exception relative à la validité des coordonnées
     * @throws CoordsBadShipException Exception relative à la validité des coordonnées choisies par rapport au bateau
     */

    @Test
    void testGetCategorie() throws BadCoordException, CoordsBadShipException {
        AircraftCarrier a1 = new AircraftCarrier("AIRCRAFT_CARRIER", "A1", "A5");
        Battleship b1 = new Battleship("BATTLESHIP", "C2", "C5");
        Cruiser c1 = new Cruiser("CRUISER", "E1", "E3");
        Destroyer d1 = new Destroyer("DESTROYER", "F2", "F3");
        Submarine s1 = new Submarine("SUBMARINE", "A7");
        assertEquals(ShipCategory.AIRCRAFT_CARRIER, a1.getCategory(), "Catégorie AC est égal à :");
        assertEquals(ShipCategory.BATTLESHIP, b1.getCategory(), "Catégorie B est égal à :");
        assertEquals(ShipCategory.CRUISER, c1.getCategory(), "Catégorie C est égal à :");
        assertEquals(ShipCategory.DESTROYER, d1.getCategory(), "Catégorie D est égal à :");
        assertEquals(ShipCategory.SUBMARINE, s1.getCategory(), "Catégorie S est égal à :");
    }



    /**
     * Test la methode getSize() de chaque sous-classe de ship
     * @throws BadCoordException
     * @throws CoordsBadShipException
     */

    @Test
    void testGetSize() throws BadCoordException, CoordsBadShipException {
        AircraftCarrier a1 = new AircraftCarrier("AIRCRAFT_CARRIER", "A1", "A5");
        Battleship b1 = new Battleship("BATTLESHIP", "C2", "C5");
        Cruiser c1 = new Cruiser("CRUISER", "E1", "E3");
        Destroyer d1 = new Destroyer("DESTROYER", "F2", "F3");
        Submarine s1 = new Submarine("SUBMARINE", "A7");
        assertEquals(5, a1.getSize(), "Size AC est égal à :");
        assertEquals(4, b1.getSize(), "Size B est égal à :");
        assertEquals(3, c1.getSize(), "Size C est égal à :");
        assertEquals(2, d1.getSize(), "Size D est égal à :");
        assertEquals(1, s1.getSize(), "Size S est égal à :");
    }

    /**
     * Test de la méthode getCoord()  dans le sens horizontal
     * @throws BadCoordException
     * @throws CoordsBadShipException
     */

    @Test
    void testGetCoord() throws BadCoordException, CoordsBadShipException {
        AircraftCarrier a1 = new AircraftCarrier("AIRCRAFT_CARRIER", "A1", "A5");
        Battleship b1 = new Battleship("BATTLESHIP", "C2", "C5");
        Cruiser c1 = new Cruiser("CRUISER", "E1", "E3");
        Destroyer d1 = new Destroyer("DESTROYER", "F2", "F3");
        Submarine s1 = new Submarine("SUBMARINE", "A7");

        List<ICoord> l1 = new LinkedList<>();
        for (int i = 1; i<=5 ; i++){
            l1.add(new Coord("A"+String.valueOf(i)));
        }

        List<ICoord> l2 = new LinkedList<>();
        for (int j = 2; j<=5; j++){
            l2.add(new Coord("C"+String.valueOf(j)));
        }

        List<ICoord> l3 = new LinkedList<>();
        for (int j = 1; j<=3; j++){
            l3.add(new Coord("E"+String.valueOf(j)));
        }

        List<ICoord> l4 = new LinkedList<>();
        for (int j = 2; j<=3; j++){
            l4.add(new Coord("F"+String.valueOf(j)));
        }

        List<ICoord> l5 = new LinkedList<>();
        l5.add(new Coord("A"+String.valueOf(7)));


        assertEquals(l1, a1.getCoords(), "Coords AC est égal à :");
        assertEquals(l2, b1.getCoords(), "Coords B est égal à :");
        assertEquals(l3, c1.getCoords(), "Coords C est égal à :");
        assertEquals(l4, d1.getCoords(), "Coords D est égal à :");
        assertEquals(l5, s1.getCoords(), "Coords S est égal à :");
    }

    /**
     * Test de la méthode getCoords dans le sens vertical
     * @throws BadCoordException
     * @throws CoordsBadShipException
     */

    @Test
    void testGetCoord1() throws BadCoordException, CoordsBadShipException {
        AircraftCarrier a1=new AircraftCarrier("AIRCRAFT_CARRIER","A4","E4");
        Battleship b1=new Battleship("BATTLESHIP","A4","D4");
        Cruiser c1=new Cruiser("CRUISER","A4","C4");
        Destroyer d1=new Destroyer("DESTROYER","A4","B4");
        Submarine s1=new Submarine("SUBMARINE","A4");

        Coord co1=new Coord("A4");
        Coord co2=new Coord("B4");
        Coord co3=new Coord("C4");
        Coord co4=new Coord("D4");
        Coord co5=new Coord("E4");

        List l1=new LinkedList<>();
        l1.add(co1);
        l1.add(co2);
        l1.add(co3);
        l1.add(co4);
        l1.add(co5);

        List l2=new LinkedList<>();
        l2.add(co1);
        l2.add(co2);
        l2.add(co3);
        l2.add(co4);

        List l3=new LinkedList<>();
        l3.add(co1);
        l3.add(co2);
        l3.add(co3);

        List l4=new LinkedList<>();
        l4.add(co1);
        l4.add(co2);


        List l5=new LinkedList<>();
        l5.add(co1);

        assertEquals(l1,a1.getCoords(),"Coords AC est égale à :");
        assertEquals(l2,b1.getCoords(),"Coords B est égale à :");
        assertEquals(l3,c1.getCoords(),"Coords C est égale à :");
        assertEquals(l4,d1.getCoords(),"Coords D est égale à :");
        assertEquals(l5,s1.getCoords(),"Coords S est égale à :");
    }

    /**
     * Test de la méthode equals() n°1
     * @throws BadCoordException
     * @throws CoordsBadShipException
     */

    @Test
    void testEquals() throws BadCoordException, CoordsBadShipException  {
        AircraftCarrier a1 = new AircraftCarrier("AIRCRAFT_CARRIER", "A1", "A5");
        Battleship b1 = new Battleship("BATTLESHIP", "C2", "C5");
        Cruiser c1 = new Cruiser("CRUISER", "E1", "E3");
        Destroyer d1 = new Destroyer("DESTROYER", "F2", "F3");
        Submarine s1 = new Submarine("SUBMARINE", "A7");

        Object o1 = a1;
        Object o2 = b1;
        Object o3 = c1;
        Object o4 = d1;
        Object o5 = s1;

        assertEquals(true, a1.equals(o1), "Le bateau est égal à l'objet :");
        assertEquals(true, b1.equals(o2), "Le bateau est égal à l'objet :");
        assertEquals(true, c1.equals(o3), "Le bateau est égal à l'objet :");
        assertEquals(true, d1.equals(o4), "Le bateau est égal à l'objet :");
        assertEquals(true, s1.equals(o5), "Le bateau est égal à l'objet :");


    }

    /**
     * Test de la méthode equals() n°2
     * @throws BadCoordException
     * @throws CoordsBadShipException
     */

    @Test
    void testEquals2() throws BadCoordException, CoordsBadShipException {
        AircraftCarrier a1 = new AircraftCarrier("AIRCRAFT_CARRIER", "A1", "A5");
        Battleship b1 = new Battleship("BATTLESHIP", "C2", "C5");
        Cruiser c1 = new Cruiser("CRUISER", "E1", "E3");
        Destroyer d1 = new Destroyer("DESTROYER", "F2", "F3");
        Submarine s1 = new Submarine("SUBMARINE", "A7");

        Object o6 = new Object();
        o6 = new AircraftCarrier("AIRCRAFT_CARRIER","A1","A5");
        Object o7 = new Object();
        o7 = new Battleship("BATTLESHIP", "C2", "C5");
        Object o8 = new Object();
        o8 = new Cruiser("CRUISER", "E1", "E3");
        Object o9 = new Object();
        o9 = new Destroyer("DESTROYER", "F2", "F3");
        Object o10 = new Object();
        o10 = new Submarine("SUBMARINE", "A7");

        assertEquals(true, a1.equals(o6), "Le bateau est égal à l'objet :");
        assertEquals(true, b1.equals(o7), "Le bateau est égal à l'objet :");
        assertEquals(true, c1.equals(o8), "Le bateau est égal à l'objet :");
        assertEquals(true, d1.equals(o9), "Le bateau est égal à l'objet :");
        assertEquals(true, s1.equals(o10), "Le bateau est égal à l'objet :");


    }

    /**
     * Test de la méthode toString()
     * @throws BadCoordException
     * @throws CoordsBadShipException
     */

    @Test
    void testToString() throws BadCoordException, CoordsBadShipException{
        AircraftCarrier a1 = new AircraftCarrier("AIRCRAFT_CARRIER", "A1", "A5");
        String res = "AIRCRAFT_CARRIER :\ntaille = 5\navant = A1\narriere = A5";
        assertEquals(res, a1.toString(), "Le toString du bateau est égal à : ");
    }


}