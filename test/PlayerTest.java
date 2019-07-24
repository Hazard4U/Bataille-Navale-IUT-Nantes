import info1.network.Player;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * des cas de tests pour la classe Player
 */


public class PlayerTest {

    /**Méthode qui test le constructeur de la classe Player
     *
     */
    @Test
    void testConstructeur() {
        Player p=new Player("Tomtom");
    }

    @Test
    void testConstructeur2(){
        Player p=new Player("");
    }

    @Test
    void testConstructeur3(){
        Player p=new Player(null);
    }

    /**Méthode qui test la méthode getName de la classe Player
     *
     */
    @Test
    void testGetName() {
        Player p=new Player("Tomtom");
        String res=p.getName();
        assertEquals("Tomtom",res,"Le nom doit être le même =? (Tomtom) -> getName");
    }

    /**Méthode qui test la méthode equals de la classe Player
     *
     */
    @Test
    void testEquals1() {
        Player p1=new Player("Tomtom");
        Object p2= "Nana";
        boolean res=p1.equals(p2);
        assertEquals(false,res,"Le joueur et l'objet sont égaux retour attendu =? (false car un Objet et un Joueur ne sont pas la même chose)");
    }

    /**Méthode qui test la méthode equals de la classe Player
     *
     */
    @Test
    void testEquals2() {
        Player p1=new Player("Tomtom");
        Player p2=new Player("Nana");
        boolean res=p1.equals(p2);
        assertEquals(false,res,"Les deux joueurs sont égaux retour attendu =? (false car les deux joueurs n'ont pas le même nom)");
    }

    /**Méthode qui test la méthode equals de la classe Player
     *
     */
    @Test
    void testEquals3() {
        Player p1=new Player("Tomtom");
        Player p2=new Player("Tomtom");
        boolean res=p1.equals(p2);
        assertEquals(true,res,"Les deux joueurs sont égaux retour attendu =? (true car les deux joueurs ont le même nom)");
    }

    /**Méthode qui test la méthode equals de la classe Player
     *
     */
    @Test
    void testEquals4() {
        Player p1=new Player("Tomtom");
        Player p2=p1;
        boolean res=p1.equals(p2);
        assertEquals(true,res,"Les deux joueurs sont égaux retour attendu =? (true car les deux joueurs sont le même)");
    }


    /**Méthode qui test la méthode toString de la classe Player
     *
     */
    @Test
    void testToString() {
        Player p1=new Player("Tomtom");
        String res=p1.toString();
        assertEquals("Tomtom",res,"Le nom doit être le même =? (Tomtom) -> toString");
    }

    /**Méthode qui test la méthode asJSON
     *
     */
    @Test
    void testJson() {
        Player p1=new Player("Tomtom");
        JSONObject jsPlayer = new JSONObject();
        String res = String.valueOf(jsPlayer.put("nom", p1.getName()));
        assertEquals("{\"nom\":\"Tomtom\"}",res,"Le nom doit être le même =? (Tomtom) -> toString");
    }


}