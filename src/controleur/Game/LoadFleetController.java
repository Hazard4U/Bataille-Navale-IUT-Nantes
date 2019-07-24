package controleur.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


import info1.ships.AircraftCarrier;
import info1.ships.BadCoordException;
import info1.ships.Battleship;
import info1.ships.CoordsBadShipException;
import info1.ships.Cruiser;
import info1.ships.Destroyer;
import info1.ships.ICoord;
import info1.ships.NavyFleet;
import info1.ships.Submarine;
import vue.FenetrePrincipale;

public class LoadFleetController implements ActionListener {
	private FenetrePrincipale vue;

	public LoadFleetController(FenetrePrincipale vue) {
		super();
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String file_name = this.vue.getSelectedFleet()+".txt";
		
			String[][] save = new String[10][10];
			Scanner sc=null;
			NavyFleet Copy=new NavyFleet();

			try{
			sc=new Scanner(new File("savefleets/"+file_name));
			}catch (FileNotFoundException e1){
			e1.getMessage();
			}

			for(int i=0;i<10;i++){
			for(int y=0;y<10;y++){
			if (sc.hasNextLine()){
			save[i][y]=sc.nextLine();
			}
			}
			}


			for(int i=0;i<10;i++){
			String cat="";
			for(int y=0;y<10;y++){
			cat+=save[i][y];

			switch (save[i][y]) {

			case "p" :
			if(y+4<10){
			if ((save[i][y+4].equals("p")&& save[i][y+3].equals("p") && save[i][y+2].equals("p")&& save[i][y+1].equals("p")) || i+4<10 && ((!(save[i+1][y].equals("p")) || !(save[i+2][y].equals("p")) || !(save[i+3][y].equals("p")) || !(save[i+4][y].equals("p") )))){
			AircraftCarrier air;
			try {
				air = new AircraftCarrier("air1", Copy.intToAlphabetic(i)+String.valueOf(y+1), Copy.intToAlphabetic(i)+String.valueOf(y+5) );
				Copy.add(air);
			} catch (BadCoordException | CoordsBadShipException e1) {
				e1.printStackTrace();
			}
			}
			}
			if(i+4<10){
			if(save[i+4][y].equals("p") ) {
			try {
				AircraftCarrier air = new AircraftCarrier("air1", Copy.intToAlphabetic(i)+String.valueOf(y+1), Copy.intToAlphabetic(i+4)+String.valueOf(y+1) );
				Copy.add(air);
			} catch (BadCoordException | CoordsBadShipException e1) {
				e1.printStackTrace();
			}
			}
			}
			break;

			case "b":
			if(y+3<10) {
			if ((save[i][y + 3].equals("b") && save[i][y + 2].equals("b") && save[i][y + 1].equals("b"))|| i+3<10 && ((!(save[i+1][y].equals("b")) || !(save[i+2][y].equals("b")) || !(save[i+3][y].equals("b"))))) {
			Battleship battle;
			try {
				battle = new Battleship("battle1", Copy.intToAlphabetic(i) + String.valueOf(y + 1), Copy.intToAlphabetic(i) + String.valueOf(y + 4));
				Copy.add(battle);
			} catch (BadCoordException | CoordsBadShipException e1) {
				e1.printStackTrace();
			}
			}
			}
			if(i+3<10){
			if (save[i+3][y].equals("b")) {
			Battleship battle;
			try {
				battle = new Battleship("battle1", Copy.intToAlphabetic(i) + String.valueOf(y + 1), Copy.intToAlphabetic(i + 3) + String.valueOf(y + 1));
				Copy.add(battle);
			} catch (BadCoordException | CoordsBadShipException e1) {
				e1.printStackTrace();
			}
			}
			}
			break;

			case "c":
			if(y+2<10){
			if ((save[i][y+2].equals("c") && save[i][y+1].equals("c")) || i+2<10 &&  ((!(save[i+1][y].equals("c")) || !(save[i+2][y].equals("c"))))){
			Cruiser cruis;
			try {
				cruis = new Cruiser("Cruis1", Copy.intToAlphabetic(i)+String.valueOf(y+1), Copy.intToAlphabetic(i)+String.valueOf(y+3) );
				Copy.add(cruis);
			} catch (BadCoordException | CoordsBadShipException e1) {
				e1.printStackTrace();
			}
			}
			}
			if(i+2<10){
			if (save[i+2][y].equals("c")){
			Cruiser cruis;
			try {
				cruis = new Cruiser("Cruis1", Copy.intToAlphabetic(i)+String.valueOf(y+1), Copy.intToAlphabetic(i+2)+String.valueOf(y+1) );
				Copy.add(cruis);
			} catch (BadCoordException | CoordsBadShipException e1) {
				e1.printStackTrace();
			}
			}
			}
			break;

			case "t":
			if(y+1<10){
			if (save[i][y+1].equals("t") || i+1<10 && ((!(save[i+1][y].equals("t"))))){
			Destroyer dest;
			try {
				dest = new Destroyer("Dest1", Copy.intToAlphabetic(i)+String.valueOf(y+1), Copy.intToAlphabetic(i)+String.valueOf(y+2) );
				Copy.add(dest);
			} catch (BadCoordException | CoordsBadShipException e1) {
				e1.printStackTrace();
			}

			}
			}
			if(i+1<10){
			if (save[i+1][y].equals("t")) {
			Destroyer dest;
			try {
				dest = new Destroyer("Dest1", Copy.intToAlphabetic(i) + String.valueOf(y + 1), Copy.intToAlphabetic(i + 1) + String.valueOf(y + 1));
				Copy.add(dest);
			} catch (BadCoordException | CoordsBadShipException e1) {
				e1.printStackTrace();
			}
			}
			}
			break;

			case "s":
				Submarine sub;
				try {
					sub = new Submarine("sub1", Copy.intToAlphabetic(i)+String.valueOf(y+1) );
					Copy.add(sub);
				} catch (BadCoordException | CoordsBadShipException e1) {
					e1.printStackTrace();
				}
			break;
			}
			}
			}


			this.vue.setFlotte(Copy);
			this.vue.setPanelBackgroundColor(0, new ArrayList<ICoord>(), true, true, false);
			
		
	}
	
	
	
	
}
