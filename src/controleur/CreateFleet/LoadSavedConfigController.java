package controleur.CreateFleet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vue.CreateFleet;

public class LoadSavedConfigController {
	private CreateFleet vue;

	public LoadSavedConfigController(CreateFleet vue) {
		super();
		this.vue = vue;
	}
	
	
	public void setFleets() throws FileNotFoundException{
		List<String> nom_flottes = new ArrayList<String>();
		 Scanner scanner = new Scanner(new File("saveconfigs/saveconfigs.txt"));
		while(scanner.hasNext()){
			String ligne = scanner.next();
			nom_flottes.add(ligne);

		}
		scanner.close();
		this.vue.setFleetsList(nom_flottes);
	}
	
	
}
