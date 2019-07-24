package controleur.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import info1.ships.ICoord;
import info1.ships.IShip;
import info1.ships.NavyFleet;
import vue.FenetrePrincipale;

public class SaveFleetController implements ActionListener {
	private FenetrePrincipale vue;
	private NavyFleet flotte;
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

	public SaveFleetController(FenetrePrincipale vue, NavyFleet flotte) {
		super();
		this.vue = vue;
		this.flotte = flotte;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		String file_name = this.vue.getTextArea();
		file_name = this.makeSlug(file_name);
		boolean alreadyname = false;
		 Scanner scanner;
		try {
			scanner = new Scanner(new File("savefleets/savefleets.txt"));
			while(scanner.hasNext()){
				String ligne = scanner.next();
				if(ligne.equalsIgnoreCase(file_name)) {
					alreadyname = true;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		
		
		if(!this.flotte.isComplete()) {
			this.vue.setErrorSave("Flotte incomplète", Color.RED);
		} else if(this.vue.getTextArea().trim().equals("")) {
			this.vue.setErrorSave("Nom invalide", Color.RED);
		} else if(alreadyname) {
			this.vue.setErrorSave("Nom déjà utilisé", Color.RED);
		}else {
			
				String[][] save = new String[10][10];
				for(int i=0;i<10;i++) {
				for(int y=0;y<10;y++) {
				save[i][y] = "-";
				}
				}

				for(IShip boat: this.flotte.getShips()) {
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
				file_name = this.vue.getTextArea();
				file_name = this.makeSlug(file_name);
				Path file =Paths.get("savefleets/"+file_name+".txt");

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
				this.vue.setErrorSave("Flotte sauvegardée", Color.GREEN);
				Files.write(Paths.get("savefleets/savefleets.txt"), ("\n"+file_name).getBytes(), StandardOpenOption.APPEND);
				} catch (IOException e1) {
					this.vue.setErrorSave("Erreur lors de l'enregistrement", Color.RED);
					e1.printStackTrace();
				}
		}
	}
	
	public String makeSlug(String input) {
	    String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
	    String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
	    String slug = NONLATIN.matcher(normalized).replaceAll("");
	    return slug.toLowerCase(Locale.ENGLISH);
	  }
	
	
}
