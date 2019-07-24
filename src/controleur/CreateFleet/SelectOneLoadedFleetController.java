package controleur.CreateFleet;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import vue.CreateFleet;

public class SelectOneLoadedFleetController implements ItemListener {
	private CreateFleet vue;

	public SelectOneLoadedFleetController(CreateFleet vue) {
		super();
		this.vue = vue;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		String file_name = (String) e.getItem();
		if(file_name.equalsIgnoreCase("Configuration Française")) {
			this.vue.setDestroyerTextField("2");
			this.vue.setAircraftTextField("1");
			this.vue.setCruiserTextField("2");
			this.vue.setBattleTextField("1");
			this.vue.setSubmarineTextField("1");
			this.vue.setValueTotal();
		} else if(file_name.equalsIgnoreCase("Configuration Belge")) {
			this.vue.setDestroyerTextField("3");
			this.vue.setCruiserTextField("2");
			this.vue.setBattleTextField("1");
			this.vue.setSubmarineTextField("4");
			this.vue.setAircraftTextField("0");
			this.vue.setValueTotal();
		} else {
			Scanner sc;
			try {
				sc = new Scanner(new File("saveconfigs/"+file_name+".txt"));
				Integer[] res = new Integer[5];
				int i=0;
				while(sc.hasNextInt()){
				res[i]= sc.nextInt();
				i++;
				}
				
				this.vue.setDestroyerTextField(res[3]+"");
				this.vue.setCruiserTextField(res[2]+"");
				this.vue.setBattleTextField(res[1]+"");
				this.vue.setSubmarineTextField(res[4]+"");
				this.vue.setAircraftTextField(res[0]+"");
				this.vue.setValueTotal();
				
			} catch (FileNotFoundException e1) {
				//
			}
			
		}
		
	}
	
	
}
