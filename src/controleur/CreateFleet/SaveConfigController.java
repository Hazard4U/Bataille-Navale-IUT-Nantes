package controleur.CreateFleet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import info1.network.Player;
import vue.CreateFleet;
import vue.FenetrePrincipale;

public class SaveConfigController implements ActionListener {
	
	private CreateFleet vue;
	private Player p;
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");




	public SaveConfigController(CreateFleet vue, Player p) {
		super();
		this.p = p;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		String file_name = this.vue.getSaveConfigArea();
		file_name = this.makeSlug(file_name);
		
		int[] bateaux = new int[5];
		bateaux[0]=this.vue.getAircraftValue();
		bateaux[1]=this.vue.getBattleValue();
		bateaux[2]=this.vue.getCruiserValue();
		bateaux[3]=this.vue.getDestroyerValue();
		bateaux[4]=this.vue.getSubmarineValue();
		
		
		boolean alreadyname = false;
		 Scanner scanner;
		try {
			scanner = new Scanner(new File("saveconfigs/saveconfigs.txt"));
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

		PrintWriter writer;
		System.out.println("a"+this.vue.getSaveConfigArea().trim()+"a");
		System.out.println(this.vue.getSaveConfigArea().trim().equals(""));
		if(this.vue.getSaveConfigArea().trim().equals("")){
			this.vue.setSaveError("Le nom est invalide");
		} else if(alreadyname) {
			this.vue.setSaveError("Ce nom est déjà utilisé pour une autre configuration");
		} else if (this.vue.getValueTotal() == 20){
			try {
				writer = new PrintWriter("saveconfigs/"+file_name+".txt");
				for(int i=0;i<5;i++) {
					writer.println(bateaux[i]);
				}
				writer.close();
				Files.write(Paths.get("saveconfigs/saveconfigs.txt"), ("\n"+file_name).getBytes(), StandardOpenOption.APPEND);



				FenetrePrincipale principale = new FenetrePrincipale("Bataille navale",this.p, bateaux, this.vue.getIp());
				principale.setSize(1250, 700);
				principale.setResizable(false);
				principale.setVisible(true);
				this.vue.dispose();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(this.vue.getValueTotal() != 20){
			this.vue.setCapacityError("La place totale occupée par vos bateaux doit être égale à 20");
		}
	}
	
	


	  public String makeSlug(String input) {
	    String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
	    String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
	    String slug = NONLATIN.matcher(normalized).replaceAll("");
	    return slug.toLowerCase(Locale.ENGLISH);
	  }
		
	
	
	
	
	
}
