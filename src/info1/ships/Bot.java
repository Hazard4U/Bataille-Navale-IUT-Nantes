package info1.ships;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.*;

import java.util.List;

public class Bot {

    public static final String HTTP_LOCALHOST = "http://localhost:8888/api/v0";
    public static final int WAIT_TIME = 300;
    public static final String BOT = "Nono le petit Robot";

    public static String[] AlphaX = {"_", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};


    public static void main(String[] args) {

        try {
            System.out.println("Lancement du bot "+ BOT + "...");
            Player botPlayer = new Player(BOT);
            List<Player> players = Network.listActivePlayers(HTTP_LOCALHOST);
            if (! players.contains(botPlayer)) {
                if (Network.suscribeNewPlayer(HTTP_LOCALHOST, botPlayer)) {
                    System.out.println(BOT + " s'inscrit sur le serveur...");
                }
            }
            System.out.println("---------------");
            System.out.println("> Parties initialisées : " + Network.listInitializedGames(HTTP_LOCALHOST));

            System.out.println("---------------");
            NavyFleet flotte = new NavyFleet();
            flotte.add(new AircraftCarrier("monPorteAvion", "E5", "E9"));
            flotte.add(new Battleship("monCuirasse", "B2", "E2"));
            flotte.add(new Submarine("monSousMarin", "G10"));
            flotte.add(new Cruiser("monCroiseur", "B8", "B6"));
            flotte.add(new Destroyer("monTorpilleur", "H3", "H4"));
            flotte.add(new Destroyer("autreTorpilleur", "D9", "C9"));
            flotte.add(new Cruiser("autreCroiseur", "J8", "H8"));

            System.out.println("> Flotte : " + flotte.asJSON().toString());
            System.out.println("---------------");
            Game currentGame = Network.initNewGame(HTTP_LOCALHOST, botPlayer, flotte);
            System.out.println("> Parties initialisées : " + Network.listInitializedGames(HTTP_LOCALHOST));
            System.out.println("---------------");
            System.out.println("> Attente qu'un joueur rejoigne la partie...");
            int res = 1;
            while (res == 1) {
                Thread.sleep(WAIT_TIME);
                res = Network.getInfo(HTTP_LOCALHOST, currentGame, botPlayer);
                System.out.print(".");
            }
            System.out.print("OK ; \n La partie peut commencer...");
            res = Network.getInfo(HTTP_LOCALHOST, currentGame, botPlayer);

            int x = 1;
            int y = 1;
            boolean partieTerminee = false;
            while (!partieTerminee) {
                if (res == 10) {
                    Coord shoot = new Coord(AlphaX[x]+y);
                    y++;
                    if (y == 11) {
                        x++;
                        y=1;
                    }
                    System.out.println(BOT + " tire en " + shoot.toString());
                    int res2 =Network.playOneTurn(HTTP_LOCALHOST, currentGame, botPlayer, shoot);
                    if (res2 == 0)
                        System.out.println("   (manqué)");
                    else if (res2 == 1)
                        System.out.println("   > touché <");
                    else if (res2 == 10)
                        System.out.println("   >> coulé <<");
                    else if (res2 == 100) {
                        System.out.println("   **** Partie gagnée par " + BOT +" ****");
                        partieTerminee = true;
                    }
                }
                else if (res == -100) {
                    System.out.println("   ((( Partie perdue )))");
                    partieTerminee = true;
                }
                else {
                    System.out.print(".");
                    Thread.sleep(WAIT_TIME);
                }
                res = Network.getInfo(HTTP_LOCALHOST, currentGame, botPlayer);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            System.out.println("Il faut commencer par lancer le serveur...");
        } catch (BadCoordException e) {
            e.printStackTrace();
        } catch (CoordsBadShipException e) {
            e.printStackTrace();
        } catch (UncompleteFleetException e) {
            e.printStackTrace();
        } catch (BadIdException e) {
            e.printStackTrace();
        }
    }
}
