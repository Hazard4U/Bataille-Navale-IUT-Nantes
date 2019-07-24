package vue;

import controleur.CreateFleet.*;
import info1.network.Network;
import info1.network.Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CreateFleet extends JFrame {

    private JPanel principal;

    private JButton aircraftButtonPlus;
    private JButton aircraftButtonMoins;

    private JButton battleButtonPlus;
    private JButton battleButtonMoins;

    private JButton cruiserButtonPlus;
    private JButton cruiserButtonMoins;

    private JButton destroyerButtonPlus;
    private JButton destroyerButtonMoins;

    private JButton submarineButtonPlus;
    private JButton submarineButtonMoins;



    private JTextField aircraftTextField;
    private JTextField battleTextField;
    private JTextField cruiserTextField;
    private JTextField destroyerTextField;
    private JTextField submarineTextField;

    private FenetrePrincipale vue;
    private Network network;
    private Player player;

    private JLabel valueTotalPanel;
    private JLabel capacityError;
    private JLabel saveError;
    private JComboBox<String> flottes_links;
    private JButton saveConfigButton;
    private JTextField saveConfigArea;
    private String ip;

    public CreateFleet(String title, FenetrePrincipale vue, Network network, Player player, String ip){
    	super(title);
    	this.vue = vue;
    	this.network = network;
    	this.player = player;
    	this.ip = ip;
    	
    	File saveconfigs = new File("saveconfigs/saveconfigs.txt");
        if(!saveconfigs.exists()) {
        	try {
        		new File("saveconfigs").mkdir();
				PrintWriter writer = new PrintWriter("saveconfigs/saveconfigs.txt");
			} catch (FileNotFoundException e) {
			}
        }

        principal = new JPanel();
        principal.setLayout(new GridLayout(4,1));

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(5,4));

        JLabel aircraftLabel = new JLabel("AIRCRAFT_CARRIER");
        JLabel battleLabel = new JLabel("BATTLESHIP");
        JLabel cruiserLabel = new JLabel("CRUISER");
        JLabel destroyerLabel = new JLabel("DESTROYER");
        JLabel submarineLabel = new JLabel("SUBMARINE");

        aircraftButtonMoins = new JButton("-");
        aircraftButtonPlus = new JButton("+");
        aircraftTextField = new JTextField();
        aircraftTextField.setEnabled(false);
        aircraftTextField.setText("0");

        battleButtonMoins = new JButton("-");
        battleButtonPlus = new JButton("+");
        battleTextField = new JTextField();
        battleTextField.setEnabled(false);
        battleTextField.setText("0");

        cruiserButtonMoins = new JButton("-");
        cruiserButtonPlus = new JButton("+");
        cruiserTextField = new JTextField();
        cruiserTextField.setEnabled(false);
        cruiserTextField.setText("0");

        destroyerButtonMoins = new JButton("-");
        destroyerButtonPlus = new JButton("+");
        destroyerTextField = new JTextField();
        destroyerTextField.setEnabled(false);
        destroyerTextField.setText("0");

        submarineButtonMoins = new JButton("-");
        submarineButtonPlus = new JButton("+");
        submarineTextField = new JTextField();
        submarineTextField.setEnabled(false);
        submarineTextField.setText("0");
        gridPanel.add(aircraftLabel);
        gridPanel.add(aircraftTextField);
        gridPanel.add(aircraftButtonPlus);
        gridPanel.add(aircraftButtonMoins);

        gridPanel.add(battleLabel);
        gridPanel.add(battleTextField);
        gridPanel.add(battleButtonPlus);
        gridPanel.add(battleButtonMoins);

        gridPanel.add(cruiserLabel);
        gridPanel.add(cruiserTextField);
        gridPanel.add(cruiserButtonPlus);
        gridPanel.add(cruiserButtonMoins);

        gridPanel.add(destroyerLabel);
        gridPanel.add(destroyerTextField);
        gridPanel.add(destroyerButtonPlus);
        gridPanel.add(destroyerButtonMoins);

        gridPanel.add(submarineLabel);
        gridPanel.add(submarineTextField);
        gridPanel.add(submarineButtonPlus);
        gridPanel.add(submarineButtonMoins);

        //Création information Panel
        //JPanel

        //Création du panel point total
        JPanel groupErrorTotalPanel = new JPanel();
        groupErrorTotalPanel.setLayout(new BoxLayout(groupErrorTotalPanel,BoxLayout.Y_AXIS));

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel textTotalPanel = new JLabel("Capacité de la flotte : ");
        valueTotalPanel = new JLabel("0");
        JLabel text2TotalPanel = new JLabel("/20");

        totalPanel.add(textTotalPanel);
        totalPanel.add(valueTotalPanel);
        totalPanel.add(text2TotalPanel);

        JPanel errorPanel = new JPanel();
        errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.capacityError = new JLabel("");
        //this.capacityError.setVisible(false);
        this.capacityError.setForeground(Color.RED);
        errorPanel.add(this.capacityError);

        JPanel useButtonPanel = new JPanel();
        useButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton useButton = new JButton("Utiliser");
        useButton.addActionListener(new SetShipsNumberController(this, this.player));
        useButtonPanel.add(useButton);

        groupErrorTotalPanel.add(errorPanel);
        groupErrorTotalPanel.add(totalPanel);
        groupErrorTotalPanel.add(useButtonPanel);


        //Création du panel utiliser flotte
        JPanel loadConfigPanel = new JPanel();
        loadConfigPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.flottes_links = new JComboBox<>();
        this.flottes_links.addItemListener(new SelectOneLoadedFleetController(this));

        JButton loadButton = new JButton("Charger");
        loadButton.addActionListener(new SetShipsNumberController(this, this.player));

        loadConfigPanel.add(flottes_links);
        loadConfigPanel.add(loadButton);

        LoadSavedConfigController loadgame = new LoadSavedConfigController(this);
        try {
			loadgame.setFleets();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        JPanel saveConfigPanel = new JPanel();
        saveConfigPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel groupComponent = new JPanel();
        groupComponent.setLayout(new GridLayout(4,1));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel saveConfigLabel = new JLabel("Enregistrer cette configuration");
        panel1.add(saveConfigLabel);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        saveConfigArea = new JTextField();
        saveConfigArea.setPreferredSize(new Dimension(150,20));
        panel2.add(saveConfigArea);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.saveConfigButton = new JButton("Sauvegarder");
        panel3.add(saveConfigButton);
        this.saveConfigButton.addActionListener(new SaveConfigController(this,player));

        JPanel error2Panel = new JPanel();
        error2Panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.saveError = new JLabel("");
        //this.saveError.setVisible(false);
        this.saveError.setForeground(Color.RED);
        error2Panel.add(this.saveError);

        groupComponent.add(panel1);
        groupComponent.add(panel2);
        groupComponent.add(panel3);
        groupComponent.add(error2Panel);

        saveConfigPanel.add(groupComponent);
        

        //Ajout de la grille au panel principal
        principal.add(gridPanel);
        principal.add(groupErrorTotalPanel);
        principal.add(loadConfigPanel);
        principal.add(saveConfigPanel);

        //Mise en place des controleurs
        AircraftPlusButtonControleur aircraftPlusControleur = new AircraftPlusButtonControleur(this);
        AircraftMoinsButtonControleur aircraftMoinsControleur = new AircraftMoinsButtonControleur(this);

        aircraftButtonPlus.addActionListener(aircraftPlusControleur);
        aircraftButtonMoins.addActionListener(aircraftMoinsControleur);

        BattlePlusButtonControleur battlePlusControleur = new BattlePlusButtonControleur(this);
        BattleMoinsButtonControleur battleMoinsControleur = new BattleMoinsButtonControleur(this);

        battleButtonPlus.addActionListener(battlePlusControleur);
        battleButtonMoins.addActionListener(battleMoinsControleur);

        CruiserPlusButtonControleur cruiserPlusControleur = new CruiserPlusButtonControleur(this);
        CruiserMoinsButtonControleur cruiserMoinsControleur = new CruiserMoinsButtonControleur(this);

        cruiserButtonPlus.addActionListener(cruiserPlusControleur);
        cruiserButtonMoins.addActionListener(cruiserMoinsControleur);

        DestroyerPlusButtonControleur destroyerPlusControleur = new DestroyerPlusButtonControleur(this);
        DestroyerMoinsButtonControleur destroyerMoinsControleur = new DestroyerMoinsButtonControleur(this);

        destroyerButtonPlus.addActionListener(destroyerPlusControleur);
        destroyerButtonMoins.addActionListener(destroyerMoinsControleur);

        SubmarinePlusButtonControleur submarinePlusControleur = new SubmarinePlusButtonControleur(this);
        SubmarineMoinsButtonControleur submarineMoinsControleur = new SubmarineMoinsButtonControleur(this);

        submarineButtonPlus.addActionListener(submarinePlusControleur);
        submarineButtonMoins.addActionListener(submarineMoinsControleur);

        //Config la frame
        this.setContentPane(principal);
        this.setPreferredSize(new Dimension(700,500));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //Getter Setter Aircraft
    public void setAircraftTextField(String str){
        aircraftTextField.setText(str);
    }

    public int getAircraftValue(){
        return Integer.valueOf(aircraftTextField.getText());
    }

    //Getter Setter Battle
    public void setBattleTextField(String str){
        battleTextField.setText(str);
    }

    public int getBattleValue(){
        return Integer.valueOf(battleTextField.getText());
    }

    //Getter Setter Cruiser
    public void setCruiserTextField(String str){
        cruiserTextField.setText(str);
    }

    public int getCruiserValue(){
        return Integer.valueOf(cruiserTextField.getText());
    }

    //Getter Setter Destroyer
    public void setDestroyerTextField(String str){
        destroyerTextField.setText(str);
    }

    public int getDestroyerValue(){
        return Integer.valueOf(destroyerTextField.getText());
    }

    //Getter Setter Submarine
    public void setSubmarineTextField(String str){
        submarineTextField.setText(str);
    }

    public int getSubmarineValue(){
        return Integer.valueOf(submarineTextField.getText());
    }

    //Getter Setter Total
    public void setValueTotal() {
        int total = getValueTotal();
        if(total < 20){
            valueTotalPanel.setForeground(Color.ORANGE);
        }else if(total > 20){
            valueTotalPanel.setForeground(Color.RED);
        }else{
            valueTotalPanel.setForeground(Color.GREEN);
        }

        valueTotalPanel.setText( total+ "");
    }

    public int getValueTotal(){
        int aircraftValue = getAircraftValue()*5;
        int battleValue = getBattleValue()*4;
        int cruiserValue = getCruiserValue()*3;
        int destroyerValue = getDestroyerValue()*2;
        int submarineValue = getSubmarineValue()*1;

        return aircraftValue + battleValue + cruiserValue + destroyerValue + submarineValue;
    }
    
    
    public void setCapacityError(String error) {
    	this.capacityError.setText(error);
    	this.capacityError.setVisible(true);
        this.saveError.setVisible(false);
    }
    public void setSaveError(String error) {
        this.saveError.setText(error);
        this.saveError.setVisible(true);
        this.capacityError.setVisible(false);
    }
    public void setFleetsList(List<String> fleets_list) {
    	this.flottes_links.removeAllItems();
    	this.flottes_links.addItem("Charger une configuration existante...");
    	this.flottes_links.addItem("Configuration Française");
    	this.flottes_links.addItem("Configuration Belge");
    	for(String each: fleets_list) {
    		this.flottes_links.addItem(each);
    	}
    }
    
    
    public String getSaveConfigArea() {
    	return this.saveConfigArea.getText();
    }
    
    public String getIp() {
    	return this.ip;
    }

}
