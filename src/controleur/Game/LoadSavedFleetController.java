package controleur.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vue.CreateFleet;
import vue.FenetrePrincipale;

public class LoadSavedFleetController {
	private FenetrePrincipale vue;

	public LoadSavedFleetController(FenetrePrincipale vue) {
		super();
		this.vue = vue;
	}
	
	
	public void setFleets() throws FileNotFoundException{
		System.out.println("atteint");
		List<String> nom_flottes = new ArrayList<String>();
		 Scanner scanner = new Scanner(new File("savefleets/savefleets.txt"));
	        while(scanner.hasNext()){
	        	String ligne = scanner.next();
	        	nom_flottes.add(ligne);
	        	
	        }
	        scanner.close();
	        this.vue.setFleetsList(nom_flottes);
	}
	
	
}
