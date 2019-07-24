package info1.ships;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * une implementation de l'interface ICoord manipulant des coordonnées alphanumériques comme "A1", "B6", "J3", etc.
 */

public class Coord implements ICoord {

    private String xy;

    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     * <p>
     * constructeur d'un objet Coord
     *
     * @param xy la coordonnée aphanumérique sous la forme d'une chaine de caractères
     * @throws BadCoordException si la chaine de caractère ne permet pas de définir une coordonnée alphanumérique
     */
    public Coord(String xy) throws BadCoordException {
        Pattern pattern = Pattern.compile(xy);
        Matcher matcher = pattern.matcher("^\\d");

        if (Pattern.matches("^[a-jA-J]+([1-9]|10)$", xy)) {
            this.xy = xy;
        } else {
            throw new BadCoordException();
        }
    }

    @Override
    public char getAlphaX() {
        return this.xy.charAt(0);
    }

    @Override
    public int getX() {
        char retourC = this.xy.charAt(0);
        int retourI = Character.getNumericValue(retourC);
        return retourI - 9;
    }

    @Override
    public int getY() {
        int i = Integer.valueOf(String.valueOf(xy.charAt(1)));
        if (xy.length() == 3) {
            i = i * 10 + Integer.valueOf(String.valueOf(xy.charAt(2)));
        }
        return i;
    }

    @Override
    public String toString() {
        return this.xy;
    }

    @Override
    public boolean equals(Object o) {

        if (this.toString().equals(o.toString().toUpperCase())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xy);
    }



    public static void main(String[] args) throws BadCoordException {

        Coord test = new Coord("A6");
        System.out.println(test.getY());
        System.out.println(test.getX());
        System.out.println(test.getAlphaX());
        System.out.println(test.toString());
     
        Object o = new Object();
        o = "A6";
        System.out.println(test.equals(o));


    }
}