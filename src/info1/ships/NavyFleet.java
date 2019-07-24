package info1.ships;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


/**
 * Classe définissant une flotte de navires
 */

public class NavyFleet implements INavyFleet {

	private int nbr_cases;
	private int nbr_max;
	private int[][] matrice;
	private ArrayList<IShip> bateaux;
	private static String[] FileList;
	private static int compteur;
	private static File allFleet;
	private static ArrayList<String> listeOfFile;





	/**
	 * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
	 *
	 * Construit une nouvelle flotte
	 */
	public NavyFleet() {
		this.nbr_cases = 0;
		this.nbr_max = 20;
		this.matrice = new int[this.nbr_max][this.nbr_max];
		this.FileList = new String[50];
		this.compteur = 0;
		this.bateaux = new ArrayList<IShip>();
		for(int i=0;i<10;i++) {
			for(int y=0;y<10;y++) {
				this.matrice[i][y] =0;
			}
		}
	}

	public void afficher_matrice() {
		for(int i =0; i<10;i++) {
			for(int y =0;y<10;y++) {
				System.out.print(this.matrice[i][y]);
			}
			System.out.println("");
		}
	}

	@Override
	public int remainingSize() {
		return this.nbr_max-this.nbr_cases;
	}

	@Override
	public boolean isComplete() {
		return this.nbr_cases == this.nbr_max;
	}


	@Override
	public int add(IShip IShip) {
		if(this.isComplete()) {
			return -2;
		}
		else if(this.bateaux.indexOf(IShip) != -1) {
			return -1;
		} else {
			List<ICoord> coords = IShip.getCoords();
			for(ICoord each: coords) {
				if(this.matrice[each.getX()-1][each.getY()-1] == 1) {
					return -3;
				}
			}
			if(this.nbr_cases+IShip.getSize() > this.nbr_max) {
				return -2;
			} else {

			}
			this.bateaux.add(IShip);
			this.nbr_cases+= IShip.getSize();
			for(ICoord each: coords) {
				this.matrice[each.getX()-1][each.getY()-1] = 1;
			}
			return 0;
		}
	}

	public boolean isFree(List<ICoord> coords) {
		for(ICoord each: coords) {
			if(this.matrice[each.getX()-1][each.getY()-1] == 1) {
				return false;
			}
		}
		return true;
	}


	@Override
	public List<IShip> getShips() {
		Collections.sort(this.bateaux);
		return this.bateaux;
	}

	@Override
	public Set<IShip> getShips(ShipCategory shipCategory) {
		Set<IShip> trier = new HashSet<IShip>();
		for(IShip each: this.bateaux) {
			if(each.getCategory() == shipCategory) {
				trier.add(each);
			}
		}
		return trier;
	}

	@Override
	public boolean isBelgianConfiguration() {
		int cuirasse = 0;
		int croiser = 0;
		int torpilleur = 0;
		int sousm = 0;
		for(IShip each: this.bateaux) {
			if(each.getCategory() == ShipCategory.DESTROYER) {
				torpilleur++;
			} else if(each.getCategory() == ShipCategory.BATTLESHIP) {
				cuirasse++;
			} else if(each.getCategory() == ShipCategory.CRUISER) {
				croiser++;
			} else if(each.getCategory() == ShipCategory.SUBMARINE) {
				sousm++;
			}
		}
		return cuirasse == 1 && croiser == 2 && torpilleur == 3 && sousm == 4;
	}

	@Override
	public boolean isFrenchConfiguration() {
		int cuirasse = 0;
		int croiser = 0;
		int torpilleur = 0;
		int sousm = 0;
		int portea = 0;
		for(IShip each: this.bateaux) {
			if(each.getCategory() == ShipCategory.DESTROYER) {
				torpilleur++;
			} else if(each.getCategory() == ShipCategory.BATTLESHIP) {
				cuirasse++;
			} else if(each.getCategory() == ShipCategory.CRUISER) {
				croiser++;
			} else if(each.getCategory() == ShipCategory.SUBMARINE) {
				sousm++;
			} else if(each.getCategory() == ShipCategory.AIRCRAFT_CARRIER) {
				portea++;
			}
		}
		return cuirasse == 1 && croiser == 2 && torpilleur == 2 && sousm == 1 && portea == 1;
	}


	/**
	 * Stock la flotte dans un fichier sous forme de matrice
	 * @param nomFichier
	 */

	public void save_fleet(String nomFichier)  {
		if(this.isComplete()) {
			String[][] save = new String[10][10];
			for(int i=0;i<10;i++) {
				for(int y=0;y<10;y++) {
					save[i][y] = "-";
				}
			}

			for(IShip boat: this.bateaux) {
				List<ICoord> coords = boat.getCoords();
				for(ICoord coord: coords) {
					switch(boat.getCategory()) {
						case AIRCRAFT_CARRIER:
							save[coord.getX()-1][coord.getY()-1] = "p";
							break;
						case DESTROYER:
							save[coord.getX()-1][coord.getY()-1] = "t";
							break;
						case CRUISER:
							save[coord.getX()-1][coord.getY()-1] = "c";
							break;
						case SUBMARINE:
							save[coord.getX()-1][coord.getY()-1] = "s";
							break;
						case BATTLESHIP:
							save[coord.getX()-1][coord.getY()-1] = "b";
							break;
					}
				}
			}

			List lines = new ArrayList();
			Path file =Paths.get(nomFichier);


			String lignes = "";
			for(int i=0;i<10;i++) {
				for(int y=0;y<10;y++) {
					lignes+=save[i][y];
					lines.add(lignes);
					lignes="";
				}
			}


			try {
				Files.write(file, lines, StandardCharsets.UTF_8);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//ajouterTab(nomFichier);
		//Integer[] f = nbShipFleet();
		//fileConfig(f, nomFichier);
	}

	public boolean matriceContains(String s){
		boolean res = false;
		for (int i = 0; i<FileList.length ; i++){
			if (FileList[i]==s){
				res = true;
			}
		}
		return res;
	}

	public void ajouterTab(String s){
		if (!matriceContains(s)){
		FileList[compteur]=s;
		compteur++; }
		else {
			System.out.println("Ce fichier est déha contenu dans la liste");
		}

		allFleet = new File("testHell.txt");
		Path file = Paths.get(allFleet.getName());
		listeOfFile = new ArrayList<>();

		if (!listeOfFile.contains(FileList[compteur-1])) {
			listeOfFile.clear();
			listeOfFile.add(FileList[compteur-1]);
		}

		try {
			Files.write(file, listeOfFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//   A RETRAVAILLER    ::::::::::::::::

	public void addFileList(String nameFile){
		ajouterTab(nameFile);
	}

	public String getFileList() throws IOException {
		

		return readFile("testHell.txt");
	}

	public String readFile(String nameFile) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(nameFile));
		String res;
		String end = new String();
		while ((res = bf.readLine()) != null){
			System.out.println(res);
			end = end + res;

		}

		return end;
	}

	/**
	 * Méthode permettant de lire un fichier contenant des entiers correspondant à une matrice
	 * @param nameFile  nom du fichier à lire
	 * @return une matrice contenant des entiers d'un fichiers dans l'ordre indiqué
	 * @throws FileNotFoundException
	 */

	public Integer[] readFileConfig(String nameFile) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(nameFile));
		Integer[] res = new Integer[5];
		int i=0;
		while(sc.hasNextInt()){
			res[i]= sc.nextInt();
			i++;
		}
		System.out.println(affichRead(res));
		return res;
	}


	/**
	 * Méthode affichant les entiers contenus dans une matrice d'entiers
	 * @param alpha Matrice contenant des entiers
	 * @return  retourne sous forme de string les entiers
	 */

	public String affichRead(Integer[] alpha){
		String res = new String();
		for(int i=0; i<alpha.length; i++){
			res = res + alpha[i]+"\n";
		}
		return res;
	}

	/**
	 * Méthode renvoyant un fichier contenant le nombre de type de bateau d'une configuration  dans le sens suivant : Aircraft_Carrier, Battleship, Cruiser, Destroyer, Submarine
	 * @param hell Matrice contenant les nombres de type de bateau dans l'ordre indiqué
	 * @param nameFile nom du fichier dans lequel on va écrire les entiers
	 * @return
	 */

	public File fileConfig(Integer[] hell, String nameFile){
		File configFile = new File(nameFile);
		String res = new String();
		for (int i=0 ; i<hell.length ; i++){
			res= res+String.valueOf(hell[i])+"\n";
		}
		Path file = Paths.get(configFile.getName());

		try {
			Files.write(file, Collections.singleton(res), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return configFile;
	}

	/**
	 * Méthode renvoyant une matrice d'int correspondant au nombre de chaque type de bateau d'une configuration , dans le sens décroissant du nombre de case
	 * @param a nb Aircraft_Carrier
	 * @param b nb Battleship
	 * @param c	nb Cruiser
	 * @param d	nb Destroyer
	 * @param s nb Submarine
	 * @return
	 */

	public Integer[] configBoat(int a, int b, int c, int d, int s){
		Integer[] config = new Integer[5];
		config[0]=a;
		config[1]=b;
		config[2]=c;
		config[3]=d;
		config[4]=s;
		return config;
	}

	/**
	 * renvoi la flotte chargée à partir d'un fichier
	 * @param nomFichier
	 * @return
	 * @throws IOException
	 * @throws BadCoordException
	 * @throws CoordsBadShipException
	 */

	public NavyFleet charge_fichier(String nomFichier) throws IOException, BadCoordException, CoordsBadShipException {
		String[][] save = new String[10][10];
		Scanner sc=null;
		NavyFleet Copy=new NavyFleet();

		try{
			sc=new Scanner(new File(nomFichier));
		}catch (FileNotFoundException e){
			e.getMessage();
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
							if ((save[i][y+4].equals("p")&& save[i][y+3].equals("p") && save[i][y+2].equals("p")&& save[i][y+1].equals("p")) || i+4 < 10 && ((!(save[i+1][y].equals("p")) || !(save[i+2][y].equals("p")) || !(save[i+3][y].equals("p")) || !(save[i+4][y].equals("p") )))){
								AircraftCarrier air = new AircraftCarrier("air1", Copy.intToAlphabetic(i)+String.valueOf(y+1), Copy.intToAlphabetic(i)+String.valueOf(y+5) );
								Copy.add(air);
							}
						}
						if(i+4<10){
							if(save[i+4][y].equals("p") ) {
								AircraftCarrier air = new AircraftCarrier("air1", Copy.intToAlphabetic(i)+String.valueOf(y+1), Copy.intToAlphabetic(i+4)+String.valueOf(y+1) );
								Copy.add(air);
							}
						}
						break;

					case "b":
						if(y+3<10) {
							if ((save[i][y + 3].equals("b") && save[i][y + 2].equals("b") && save[i][y + 1].equals("b"))|| i+3<10 &&((!(save[i+1][y].equals("b")) ||  !(save[i+2][y].equals("b")) || !(save[i+3][y].equals("b"))))) {
								Battleship battle = new Battleship("battle1", Copy.intToAlphabetic(i) + String.valueOf(y + 1), Copy.intToAlphabetic(i) + String.valueOf(y + 4));
								Copy.add(battle);
							}
						}
						if(i+3<10){
							if (save[i+3][y].equals("b")) {
								Battleship battle = new Battleship("battle1", Copy.intToAlphabetic(i) + String.valueOf(y + 1), Copy.intToAlphabetic(i + 3) + String.valueOf(y + 1));
								Copy.add(battle);
							}
						}
						break;

					case "c":
						if(y+2<10){
							if ((save[i][y+2].equals("c") && save[i][y+1].equals("c")) || i+2<10 && ((!(save[i+1][y].equals("c")) || !(save[i+2][y].equals("c"))))){
								Cruiser cruis = new Cruiser("Cruis1", Copy.intToAlphabetic(i)+String.valueOf(y+1), Copy.intToAlphabetic(i)+String.valueOf(y+3) );
								Copy.add(cruis);
							}
						}
						if(i+2<10){
							if (save[i+2][y].equals("c")){
								Cruiser cruis = new Cruiser("Cruis1", Copy.intToAlphabetic(i)+String.valueOf(y+1), Copy.intToAlphabetic(i+2)+String.valueOf(y+1) );
								Copy.add(cruis);
							}
						}
						break;

					case "t":
						if(y+1<10){
							if (save[i][y+1].equals("t") || i+1<10 &&  ((!(save[i+1][y].equals("t"))))){
								Destroyer dest = new Destroyer("Dest1", Copy.intToAlphabetic(i)+String.valueOf(y+1), Copy.intToAlphabetic(i)+String.valueOf(y+2) );
								Copy.add(dest);

							}
						}
						if(i+1<10){
							if (save[i+1][y].equals("t")) {
								Destroyer dest = new Destroyer("Dest1", Copy.intToAlphabetic(i) + String.valueOf(y + 1), Copy.intToAlphabetic(i + 1) + String.valueOf(y + 1));
								Copy.add(dest);
							}
						}
						break;

					case "s":
						Submarine sub = new Submarine("sub1", Copy.intToAlphabetic(i)+String.valueOf(y+1) );
						Copy.add(sub);
//System.out.println("marche sub");
						break;
				}
			}
			System.out.println(cat);
		}


		return Copy;
	}

	/**
	 * Méthode permettant de convertir les entiers de 0 à en lettre Majuscule correspondantes dans l'ordre alphabétique  et de retourner cette lettre
	 * @param i entier auxquel on veut associer une lettre
	 * @return une lettre sous forme de String
	 */

	public String intToAlphabetic ( int i){
		String res = new String("");
		switch (i) {
			case 0:
				res = "A";
				break;
			case 1:
				res = "B";
				break;
			case 2:
				res = "C";
				break;
			case 3:
				res = "D";
				break;
			case 4:
				res = "E";
				break;
			case 5:
				res = "F";
				break;
			case 6:
				res = "G";
				break;
			case 7:
				res = "H";
				break;
			case 8:
				res = "I";
				break;
			case 9:
				res = "J";
				break;

		}
		return res;
	}

	/**
	 * Renvoi la liste du nombre de bateau de la flotte, dans l'ordre suivant : Aircraft_Carrier ; Battleship ; Cruiser ; Destroyer ; Submarine
	 * @return
	 */

	public Integer[] nbShipFleet(){
		Integer[] liste = new Integer[5];
		int AircraftCarrier = 0;
		int Cruiser = 0;
		int Battleship = 0;
		int Destroyer = 0;
		int Submarine = 0;
		for(IShip each: this.bateaux) {
			if(each.getCategory() == ShipCategory.DESTROYER) {
				Destroyer++;
			}else if(each.getCategory() == ShipCategory.AIRCRAFT_CARRIER) {
				AircraftCarrier++;
			}else if(each.getCategory() == ShipCategory.BATTLESHIP) {
				Battleship++;
			} else if (each.getCategory() == ShipCategory.CRUISER) {
				Cruiser++;
			} else if(each.getCategory() == ShipCategory.SUBMARINE) {
				Submarine++;
			}
		}
		liste[0]=AircraftCarrier;
		liste[1]=Battleship;
		liste[2]=Cruiser;
		liste[3]=Destroyer;
		liste[4]=Submarine;

		return liste;
	}




	@Override
	public String toString () {
		return "NavyFleet [nbr_cases=" + nbr_cases + ", nbr_max=" + nbr_max + ", matrice=" + Arrays.toString(matrice)
				+ ", bateaux=" + bateaux + "]";
	}




	public static void main (String[]args) throws BadCoordException, CoordsBadShipException, IOException {

		NavyFleet flotte1 = new NavyFleet();
		AircraftCarrier air = new AircraftCarrier("monAircraft", "J1", "J5");
		AircraftCarrier air1 = new AircraftCarrier("secAircraft", "J6", "J10");
		AircraftCarrier air2 = new AircraftCarrier("troiAircraft", "I1", "I5");
		AircraftCarrier air3 = new AircraftCarrier("quatreAircraft", "B5", "F5");
		Cruiser crui = new Cruiser("monCruiser", "H10", "F10");
		Cruiser crui1a = new Cruiser("monCruiser", "B1", "D1");
		Destroyer des = new Destroyer("monDestroyer", "A4", "A5");
		Submarine sub = new Submarine("monSubmarine", "B1");
		Battleship bat = new Battleship("battle", "D7", "D10");
		Destroyer des2 = new Destroyer("Destroyeur", "B8","B9");

		Submarine enTrop = new Submarine("enTrop", "J9");


		//System.out.println(flotte1.add(sub));
		flotte1.add(air);
		flotte1.add(air1);
		flotte1.add(air2);
		flotte1.add(air3);


		flotte1.afficher_matrice();
		flotte1.save_fleet("flotheo.txt");
		flotte1.charge_fichier("flotheo.txt");

		//System.out.println(flotte1.toString());

		NavyFleet flotte2 = new NavyFleet();
		flotte2.add(air2);
		flotte2.add(air3);
		flotte2.add(crui1a);
		flotte2.add(bat);
		flotte2.add(enTrop);
		flotte2.add(des2);


		//System.out.println(flotte1.getFileList());
		//flotte1.readFile("testHell.txt");
	




	}
}



