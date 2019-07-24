import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Player;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;



/**
 * Des cas de tests pour la classe Game
 */

public class GameTest {

    /**
     * Test le constructeur de Game avec un ID négatif
     */
    @Test
    public void testConstructor1(){

        Assertions.assertThrows(BadIdException.class,
                () -> {Game test = new Game(-1);}
        );
    }

    /**
     * Test le constructeur de Game avec un ID nul
     */

    @Test
    public void testConstructor2(){
        Assertions.assertThrows(BadIdException.class,
                () -> {Game test = new Game(0);}
        );
    }

    /**
     * Test le constructeur de la classe Game avec un ID positif
     * @throws BadIdException
     */

    @Test
    void testConstructor3() throws BadIdException {
        Game test = new Game(1235);
    }

    /**
     * Test la méthode getId de la classe Game
     * @throws BadIdException
     */

    @Test
    void testGetId() throws BadIdException {
        Game test = new Game(8);
        int res = test.getId();
        assertEquals(8, res, "L'id de la game est égale à : ");

    }

    /**
     * Test la méthode initiate(Player) puis la méthode getInitiator() pour un seul initié
     * @throws BadIdException
     */

    @Test
    void testInitiator() throws BadIdException {
        Game test = new Game(8);
        Player p1 = new Player("Jack");
        test.initiate(p1);
        Player res = test.getInitiator();
        assertEquals(p1, res, "Le player est égal à :" );
    }

    /**
     * Test la méthode initiate(Player) puis la méthode getInitiator() pour plusieurs initié
     * @throws BadIdException
     */

    @Test
    void testInitiator2() throws BadIdException {
        Game test = new Game(8);
        Player p1 = new Player("Jack");
        Player p2 = new Player("Rob");
        test.initiate(p1);
        test.initiate(p2);
        Player res = test.getInitiator();
        assertEquals(p2, res, "Le player est égal à :" );
    }

    /**
     * Test la méthode getInitiator() pour aucun initié
     * @throws BadIdException
     */

    @Test
    void testInitiator3() throws BadIdException {
        Game test = new Game(8);
        Player res = test.getInitiator();
        assertEquals(null, res, "Le player est égal à :" );
    }

    /**
     * Test la méthode join(Player) puis la méthode getGuest() pour un seul guest ajouté
     * @throws BadIdException
     */

    @Test
    void testGuest() throws BadIdException {
        Game test = new Game(8);
        Player p1 = new Player("Jack");
        test.initiate(p1);
        Player guest = new Player("Ron");
        test.join(guest);
        Player res = test.getGuest();
        assertEquals(guest, res, "Le player est égal à :" );
    }

    /**
     * Test la méthode join(Player) puis la méthode getGuest() pour plusieurs guests ajoutés
     * @throws BadIdException
     */

    @Test
    void testGuest2() throws BadIdException {
        Game test = new Game(8);
        Player p1 = new Player("Jack");
        test.initiate(p1);
        Player guest = new Player("Ron");
        Player guest2 = new Player("Leo");
        test.join(guest2);
        test.join(guest);
        Player res = test.getGuest();
        assertEquals(guest, res, "Le player est égal à :" );
    }

    /**
     * Test la méthode join(Player) puis la méthode getGuest() pour aucun guest ajouté
     * @throws BadIdException
     */

    @Test
    void testGuest3() throws BadIdException {
        Game test = new Game(8);
        Player p1 = new Player("Jack");
        test.initiate(p1);
        Player res = test.getGuest();
        assertEquals(null, res, "Le player est égal à :" );
    }

    /**
     * Test de la méthode equals(Object) n°1
     * @throws BadIdException
     */

    @Test
    void testEquals() throws BadIdException {
        Game test = new Game(8);
        Object o = new Object();
        o=test;
        boolean eq = (test.equals(o));
        assertEquals(true, eq, "La game et l'objet sont égaux : ");

    }

    /**
     * Test de la méthode equals(Object) n°2
     * @throws BadIdException
     */

    @Test
    void testEquals2() throws BadIdException {
        Game test = new Game(8);
        Object o = 8;
        Object o2 = 12;

        boolean eq = (test.equals(o));
        boolean eq2 = (test.equals(o2));
        assertEquals(false, eq, "La game et l'objet sont égaux : ");
        assertEquals(false, eq2, "La game et l'objet sont égaux : ");

    }

    /**
     * Test de la méthode toString()
     * @throws BadIdException
     */

    @Test
    void testToString() throws BadIdException {
        Game test = new Game(15);
        Player p1 = new Player("Jhon");
        Player p2 = new Player("Jack");
        test.initiate(p1);
        test.join(p2);
        String res = "[15 : Jhon vs Jack]";
        assertEquals(res, test.toString(), "toString de la game est égal à :");
    }

    

}