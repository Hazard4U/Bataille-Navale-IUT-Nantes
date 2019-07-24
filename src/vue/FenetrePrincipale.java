package vue;


import controleur.Game.*;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.ICoord;
import info1.ships.IShip;
import info1.ships.NavyFleet;

import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Dictionary;

import java.util.List;


public class FenetrePrincipale extends JFrame{

    //Modèle
    private Network network;
    private Player player;
    private NavyFleet flotte;
    private Game currentGame;

    //Swing
    private JPanel principal;
    private JPanel[][] myGrille = new JPanel[10][10];
    private JPanel[][] guestGrille = new JPanel[10][10];

    private  JLabel nameLabel2;

    private JLabel aircraftValue;
    private JLabel destroyerValue;
    private JLabel battleValue;
    private JLabel cruiserValue;
    private JLabel submarineValue;

    private JRadioButton aircraft;
    private JRadioButton battle;
    private JRadioButton cruiser;
    private JRadioButton destroyer;
    private JRadioButton submarine;

    private ButtonGroup radioGroup;

    private JTextField saveFleetTextField;

    private JButton btRejoindrePartie;
    private JButton createGameButton;
    private JButton replayButton;

    private JPanel guestGrillePanel;
    private JPanel myGrillePanel;
    private JPanel chooseShipPanel;
    private JComboBox<Game> listePartie;

    private JLabel errorCreate;
    private JLabel errorJoin;
    private JLabel errorSave;
    private JLabel endGameMessage;

    private JComboBox<String> maListeFlottes;
    private String ip;

    private JPanel rightPanelLobby;
    private JPanel rightPanelGame;

    private JLabel textBateauCouleValue;
    private JLabel textCaseRestanteValue;

    //Autre
    private int[] bateaux;

    public FenetrePrincipale(String titre,Player currentPlayer, int[] bateaux, String ip) {

        super(titre);

        //Initialise modèle
        this.flotte = new NavyFleet();
        this.network = network;
        this.player = currentPlayer;
        this.bateaux = bateaux;
        this.ip = ip;

        //Initialise la liste de bateau
        if(bateaux == null) {
            this.bateaux = new int[5];
            for(int i=0;i<5;i++) {
                this.bateaux[i] = 0;
            }
        }
        File saveconfigs = new File("savefleets/savefleets.txt");
        if(!saveconfigs.exists()) {
        	try {
        		new File("savefleets").mkdir();
				PrintWriter writer = new PrintWriter("savefleets/savefleets.txt");
			} catch (FileNotFoundException e) {
			}
        }

        //Controleur placement jeu
        SetShipControler setShipControler = new SetShipControler(this,flotte);

        //Panel principal
        principal = new JPanel();
        principal.setLayout(new GridBagLayout());

        //Config GridBagLayout
        GridBagConstraints leftConstraint = new GridBagConstraints();
        leftConstraint.fill = GridBagConstraints.BOTH;
        leftConstraint.gridx = 0;
        leftConstraint.gridy = 0;
        leftConstraint.gridwidth = 4;
        leftConstraint.gridheight = 1;
        leftConstraint.weightx = 0.75;
        leftConstraint.weighty = 1;

        GridBagConstraints rightConstraint = new GridBagConstraints();
        rightConstraint.fill = GridBagConstraints.BOTH;
        rightConstraint.gridx = 4;
        rightConstraint.gridy = 0;
        rightConstraint.gridwidth = 4;
        rightConstraint.gridheight = 1;
        rightConstraint.weightx = 0.25;
        rightConstraint.weighty = 1;


        //Panel de gauche
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        principal.add(leftPanel, leftConstraint);

        //Config GridBagLayout
        GridBagConstraints titleConstraint = new GridBagConstraints();
        titleConstraint.fill = GridBagConstraints.BOTH;
        titleConstraint.gridx = 0;
        titleConstraint.gridy = 0;
        titleConstraint.gridwidth = 1;
        titleConstraint.gridheight = 2;
        titleConstraint.weightx = 1;
        titleConstraint.weighty = 0.2;

        GridBagConstraints jeuConstraint = new GridBagConstraints();
        jeuConstraint.fill = GridBagConstraints.BOTH;
        jeuConstraint.gridx = 0;
        jeuConstraint.gridy = 2;
        jeuConstraint.gridheight = 6;
        jeuConstraint.weighty = 0.80;

        //Panel Titre
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(3,1,0,10));
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        headerPanel.setPreferredSize(new Dimension(300,100));

        //Config Panel Titre
        JLabel batailleNavale = new JLabel("Bataille Navale");
        batailleNavale.setFont(new Font("Serif",Font.PLAIN, 40));

        JPanel userConfigPanel = new JPanel();
        userConfigPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel("Connecté : ");
        label.setFont(new Font("Serif",Font.PLAIN, 15));

        JLabel nameLabel = new JLabel(this.player.getName());
        nameLabel.setFont(new Font("Serif",Font.PLAIN, 15));

        userConfigPanel.add(label);
        userConfigPanel.add(nameLabel);

        JPanel userConfigPanel2 = new JPanel();
        userConfigPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel label2 = new JLabel("Adversaire : ");
        label2.setFont(new Font("Serif",Font.PLAIN, 15));

        nameLabel2 = new JLabel();
        nameLabel2.setFont(new Font("Serif",Font.PLAIN, 15));

        userConfigPanel2.add(label2);
        userConfigPanel2.add(nameLabel2);

        headerPanel.add(batailleNavale);
        headerPanel.add(userConfigPanel);
        headerPanel.add(userConfigPanel2);

        //Panel gamePanel
        JPanel gamePanel = new JPanel();
        gamePanel.getWidth();
        gamePanel.setLayout(new BoxLayout(gamePanel,BoxLayout.X_AXIS));

        //Panel guestGrille
        guestGrillePanel = new JPanel();
        guestGrillePanel.setPreferredSize(new Dimension(350,350));
        guestGrillePanel.setLayout(new GridLayout(11, 11));
        guestGrillePanel.setVisible(false);

        //Panel myGrillePanel
        myGrillePanel = new JPanel();
        myGrillePanel.setPreferredSize(new Dimension(350,350));
        myGrillePanel.setLayout(new GridLayout(11, 11));

        //Panel chooseShipPanel
        chooseShipPanel = new JPanel();
        chooseShipPanel.setPreferredSize(new Dimension(350,350));
        chooseShipPanel.setLayout(new GridLayout(9,1));
        chooseShipPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        //Config chooseShipPanel
        JLabel explanation = new JLabel("Utilisez 'R' pour pivoter le navire");
        JPanel aircraftText = new JPanel();
        aircraftText.setLayout(new FlowLayout(FlowLayout.LEFT));

        aircraft = new JRadioButton("AIRCRAFT_CARRIER : ");
        aircraft.setActionCommand("AIRCRAFT_CARRIER");
        aircraftValue = new JLabel(this.bateaux[0]+"");
        aircraftText.add(aircraft);
        aircraftText.add(aircraftValue);

        JPanel battleText = new JPanel();
        battleText.setLayout(new FlowLayout(FlowLayout.LEFT));
        battle = new JRadioButton("BATTLESHIP : ");
        battle.setActionCommand("BATTLESHIP");
        battleValue = new JLabel(this.bateaux[1]+"");
        battleText.add(battle);
        battleText.add(battleValue);

        JPanel cruiserText = new JPanel();
        cruiserText.setLayout(new FlowLayout(FlowLayout.LEFT));
        cruiser = new JRadioButton("CRUISER : ");
        cruiser.setActionCommand("CRUISER");
        cruiserValue = new JLabel(this.bateaux[2]+"");
        cruiserText.add(cruiser);
        cruiserText.add(cruiserValue);

        JPanel destroyerText = new JPanel();
        destroyerText.setLayout(new FlowLayout(FlowLayout.LEFT));
        destroyer = new JRadioButton("DESTROYER : ");
        destroyer.setActionCommand("DESTROYER");
        destroyerValue = new JLabel(this.bateaux[3]+"");
        destroyerText.add(destroyer);
        destroyerText.add(destroyerValue);

        JPanel submarineText = new JPanel();
        submarineText.setLayout(new FlowLayout(FlowLayout.LEFT));
        submarine = new JRadioButton("SUBMARINE : ");
        submarine.setActionCommand("SUBMARINE");
        submarineValue = new JLabel(this.bateaux[4]+"");
        submarineText.add(submarine);
        submarineText.add(submarineValue);

        JPanel saveFleetPanel = new JPanel();
        saveFleetPanel.setLayout(new GridLayout(2,1,0,0));

        JPanel saveFleetLabelPanel = new JPanel();
        saveFleetLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel saveFleetLabel = new JLabel("Sauvegarder la flotte :");
        saveFleetLabelPanel.add(saveFleetLabel);

        JPanel groupButtonTextField = new JPanel();
        groupButtonTextField.setLayout(new FlowLayout(FlowLayout.LEFT));

        saveFleetTextField = new JTextField();
        saveFleetTextField.setPreferredSize(new Dimension(150,20));
        JButton saveFleetButton = new JButton("Sauvegarder");
        saveFleetButton.addActionListener(new SaveFleetController(this, this.flotte));

        groupButtonTextField.add(saveFleetTextField);
        groupButtonTextField.add(saveFleetButton);
        
        JPanel errorPanel = new JPanel();
        errorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        errorSave = new JLabel("");
        errorSave.setForeground(Color.red);
        errorPanel.add(errorSave);
        
        saveFleetPanel.add(saveFleetLabelPanel);
        saveFleetPanel.add(groupButtonTextField);

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(new BackController(this,currentPlayer));

        //ButtonGroup
        radioGroup = new ButtonGroup();
        radioGroup.add(aircraft);
        radioGroup.add(battle);
        radioGroup.add(cruiser);
        radioGroup.add(destroyer);
        radioGroup.add(submarine);

        //Attache le key listener sur les radioButtons
        aircraft.setFocusable(true);
        aircraft.requestFocusInWindow();
        aircraft.addKeyListener(setShipControler);

        cruiser.setFocusable(true);
        cruiser.addKeyListener(setShipControler);

        battle.setFocusable(true);
        battle.addKeyListener(setShipControler);

        destroyer.setFocusable(true);
        destroyer.addKeyListener(setShipControler);

        // Ajout des différents sous panel au leftPanel
        leftPanel.add(headerPanel, titleConstraint);
        leftPanel.add(gamePanel , jeuConstraint);

        //Gestion de l'affichage du gamePanel (Les différents bateaux
        // ou la possibilité de créer sa flotte)
        if(this.sumBateaux() > 0) {
            //Select le radio porte avion par défaut
            aircraft.setSelected(true);
        	 //Ajout des sous panels de chooseShipPanel à chooseShipPanel
        	chooseShipPanel.add(explanation);
            chooseShipPanel.add(aircraftText);
            chooseShipPanel.add(battleText);
            chooseShipPanel.add(cruiserText);
            chooseShipPanel.add(destroyerText);
            chooseShipPanel.add(submarineText);
            chooseShipPanel.add(saveFleetPanel);
            chooseShipPanel.add(errorPanel);
            chooseShipPanel.add(backButton);
        } else {
            JPanel labelNoFlottPanel = new JPanel();
            labelNoFlottPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        	JLabel labelnoflotte = new JLabel("Veuillez charger une flotte ou en créer une nouvelle.");
        	labelNoFlottPanel.add(labelnoflotte);

        	JPanel fleetList = new JPanel();
        	fleetList.setLayout(new FlowLayout(FlowLayout.CENTER));

        	JPanel vosFlottesPanel = new JPanel();
        	vosFlottesPanel.setLayout(new GridLayout(2,1));
            fleetList.add(vosFlottesPanel);

            JLabel vosFlottes = new JLabel("Vos flottes");
            vosFlottesPanel.add(vosFlottes);
            this.maListeFlottes=new JComboBox<String>();
            this.maListeFlottes.setPreferredSize(new Dimension(300,20));
            vosFlottesPanel.add(this.maListeFlottes);


        	JPanel createFleetPanel = new JPanel();
            createFleetPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton createFleetButton = new JButton("Créer une nouvelle flotte") ;
            createFleetPanel.add(createFleetButton);
            createFleetButton.addActionListener(new NewFleetController(this, this.network, this.player));
            createFleetButton.setPreferredSize(new Dimension(300,50));

            //this.maListeFlottes.addItemListener(new SelectOneLoadedFleetController(this));
            LoadSavedFleetController loadfleets = new LoadSavedFleetController(this);
            try {
    			loadfleets.setFleets();
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}

            JPanel loadFleetPanel = new JPanel();
            loadFleetPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton loadFleetButton = new JButton("Charger la flotte");
            loadFleetPanel.add(loadFleetButton);
            loadFleetButton.addActionListener(new LoadFleetController(this));


            chooseShipPanel.add(labelNoFlottPanel);
            chooseShipPanel.add(createFleetPanel);
            chooseShipPanel.add(fleetList);
            chooseShipPanel.add(loadFleetPanel);
        }

        gamePanel.add(chooseShipPanel);
        GridBagConstraints grilleConstraint = new GridBagConstraints();
        grilleConstraint.fill = GridBagConstraints.BOTH;

        grilleConstraint.gridx = 0;
        grilleConstraint.gridy = 0;
        grilleConstraint.gridheight = 1;
        grilleConstraint.gridwidth = 1;

        gamePanel.add(guestGrillePanel,grilleConstraint);
        gamePanel.add(myGrillePanel,grilleConstraint);
        for (int i = 0; i < 11; i++){
            if(i == 0){
                JPanel guestGrilleLegend = new JPanel();
                JPanel myGrilleLegend = new JPanel();
                myGrillePanel.add(myGrilleLegend);
                guestGrillePanel.add(guestGrilleLegend);
            }else{
                JPanel guestGrilleLegend = new JPanel();
                guestGrilleLegend.setOpaque(true);

                JLabel textLegend1 = new JLabel((i)+"");

                JPanel myGrilleLegend = new JPanel();
                myGrilleLegend.setOpaque(true);

                JLabel textLegend2 = new JLabel((i)+"");

                guestGrilleLegend.add(textLegend1);
                myGrilleLegend.add(textLegend2);

                myGrillePanel.add(myGrilleLegend);
                guestGrillePanel.add(guestGrilleLegend);
            }

        }

        //remplissage des matrices
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                //Get alphaX
                String alphaX = getAlphaX(row+1);

                //Setup myGrillePanel
                this.myGrille[row][col] = new JPanel();
                this.myGrille[row][col].setBackground(new Color(0,200,255));
                this.myGrille[row][col].setOpaque(true);
                this.myGrille[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                //Controleur du panel MyGrille
                this.myGrille[row][col].addMouseListener(setShipControler);

                //Setup guestGrille
                this.guestGrille[row][col] = new JPanel();
                this.guestGrille[row][col].setBackground(Color.LIGHT_GRAY);
                this.guestGrille[row][col].setOpaque(true);
                this.guestGrille[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                //Controleur du panel GuestGrile
                ShotShipControler shotShipControler = new ShotShipControler(this,network,player);
                this.guestGrille[row][col].addMouseListener(shotShipControler);

                //Ajout du label contenant les coordonnées aux panels courants
                JLabel text = new JLabel(alphaX+(col+1));
                text.setVisible(false);
                this.myGrille[row][col].add(text);

                JLabel text2 = new JLabel(alphaX+(col+1));
                text2.setVisible(false);
                this.guestGrille[row][col].add(text2);

                if(col == 0){
                    JPanel guestGrilleLegend = new JPanel();
                    guestGrilleLegend.setOpaque(true);

                    JLabel textLegend1 = new JLabel(alphaX);

                    JPanel myGrilleLegend = new JPanel();
                    myGrilleLegend.setOpaque(true);

                    JLabel textLegend2 = new JLabel(alphaX);

                    guestGrilleLegend.add(textLegend1);
                    myGrilleLegend.add(textLegend2);

                    myGrillePanel.add(myGrilleLegend);
                    guestGrillePanel.add(guestGrilleLegend);
                }
                //Ajout des matrices à leur panel respectifs
                myGrillePanel.add(this.myGrille[row][col]);
                guestGrillePanel.add(this.guestGrille[row][col]);
            }
        }





        //Panel rightPanelLobby
        rightPanelLobby = new JPanel();
        rightPanelLobby.setLayout(new BorderLayout());

        //Config rightPanelLobby
        JPanel createGameButtonPanel = new JPanel();
        createGameButton = new JButton ("Créer une partie");
        createGameButton.addActionListener(new CreateGameController(this, this.player));
        createGameButtonPanel.add(createGameButton);

        JPanel errorCreatePanel = new JPanel();
        this.errorCreate = new JLabel("");
        this.errorCreate.setForeground(Color.RED);
        errorCreatePanel.add(errorCreate);


        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel,BoxLayout.Y_AXIS));
        northPanel.add(createGameButtonPanel);
        createGameButton.setPreferredSize(new Dimension(200,50));
        northPanel.add(errorCreatePanel);

        rightPanelLobby.add(northPanel, BorderLayout.NORTH);

        JPanel btRejoindrePartiePanel = new JPanel();
        btRejoindrePartiePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btRejoindrePartie = new JButton("Rejoindre une partie");
        btRejoindrePartie.setEnabled(false);
        btRejoindrePartie.addActionListener(new JoinGameController(this, this.player));
        btRejoindrePartiePanel.add(btRejoindrePartie);

        JPanel listePartiePanel = new JPanel();
        listePartiePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.listePartie = new JComboBox<>();
        listePartiePanel.add(listePartie);

        JPanel updateGameListButtonPanel = new JPanel();
        updateGameListButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton updateGameListButton = new JButton("Mettre à jour les parties");
        updateGameListButton.addActionListener(new SelectGameController(this));
        updateGameListButtonPanel.add(updateGameListButton);

        JPanel errorJoinPanel = new JPanel();
        errorJoinPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.errorJoin = new JLabel("");
        this.errorJoin.setForeground(Color.RED);
        errorJoinPanel.add(errorJoin);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel,BoxLayout.Y_AXIS));

        southPanel.add(updateGameListButtonPanel);
        updateGameListButton.setPreferredSize(new Dimension(200,50));
        southPanel.add(listePartiePanel);
        listePartie.setPreferredSize(new Dimension(200,50));
        southPanel.add(btRejoindrePartiePanel);
        btRejoindrePartie.setPreferredSize(new Dimension(200,50));
        southPanel.add(errorJoinPanel);

        rightPanelLobby.add(southPanel, BorderLayout.SOUTH);

        principal.add(rightPanelLobby, rightConstraint);


        //Panel rightPanelGame
        rightPanelGame = new JPanel();
        rightPanelGame.setLayout(new BorderLayout());
        rightPanelGame.setVisible(false);

        //Config rightPanelGame
        JPanel northRightPanel = new JPanel();
        northRightPanel.setLayout(new BoxLayout(northRightPanel,BoxLayout.Y_AXIS));

        JPanel textCaseRestantPanel = new JPanel();
        textCaseRestantPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel textCaseRestante = new JLabel("Nombre de coup restant avant victoire : ");
        textCaseRestanteValue = new JLabel("20");
        textCaseRestantPanel.add(textCaseRestante);
        textCaseRestantPanel.add(textCaseRestanteValue);

        JPanel textBateauCoulePanel = new JPanel();
        textBateauCoulePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel textBateauCoule = new JLabel("Nombre de bateaux adverse coulés : ");
        textBateauCouleValue = new JLabel("0");
        textBateauCoulePanel.add(textBateauCoule);
        textBateauCoulePanel.add(textBateauCouleValue);

        northRightPanel.add(textCaseRestantPanel);
        northRightPanel.add(textBateauCoulePanel);

        JPanel southRightPanel = new JPanel();
        southRightPanel.setLayout(new BoxLayout(southRightPanel,BoxLayout.Y_AXIS));

        JPanel replayButtonPanel = new JPanel();
        replayButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        replayButton = new JButton("Rejouer");
        replayButton.setPreferredSize(new Dimension(100,50));
        replayButton.setEnabled(false);
        replayButton.addActionListener(new ReplayGameController(this,currentPlayer));
        replayButtonPanel.add(replayButton);

        JPanel endGameMessagePanel = new JPanel();
        endGameMessagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.endGameMessage = new JLabel("");
        endGameMessagePanel.add(endGameMessage);

        southRightPanel.add(replayButtonPanel);
        southRightPanel.add(endGameMessagePanel);

        rightPanelGame.add(northRightPanel, BorderLayout.NORTH);
        rightPanelGame.add(southRightPanel, BorderLayout.SOUTH);

        principal.add(rightPanelGame, rightConstraint);

        this.setContentPane(principal);
    }

    public String getAlphaX(int entier){
       switch (entier){
           case 1 : return "A";
           case 2 : return "B";
           case 3 : return "C";
           case 4 : return "D";
           case 5 : return "E";
           case 6 : return "F";
           case 7 : return "G";
           case 8 : return "H";
           case 9 : return "I";
           case 10 : return "J";
       }
       return "A";
    }

    public void setUnSelectedRadio(){
        radioGroup.clearSelection();
    }

    public String getShipName(){
        return radioGroup.getSelection().getActionCommand();
    }

    public int getSelectedShip(){
        try{
            switch (getShipName()){
                case "AIRCRAFT_CARRIER" : return 5;
                case "BATTLESHIP" : return 4;
                case "CRUISER" : return 3;
                case "DESTROYER" : return 2;
                case "SUBMARINE" : return 1;
                default: return -1;
            }
        }catch (NullPointerException erreur){
            //Catch si pas de selection
            return -1;
        }
    }

    public int getSelectedShipValue(){
        switch (getShipName()){
            case "AIRCRAFT_CARRIER" : return Integer.valueOf(aircraftValue.getText());
            case "BATTLESHIP" : return Integer.valueOf(battleValue.getText());
            case "CRUISER" : return Integer.valueOf(cruiserValue.getText());
            case "DESTROYER" : return Integer.valueOf(destroyerValue.getText());
            case "SUBMARINE" : return Integer.valueOf(submarineValue.getText());
        }
        return -1;
    }

    public void setSelectedShipValue(String str){
        switch (getShipName()){
            case "AIRCRAFT_CARRIER" : aircraftValue.setText(str);
            break;
            case "BATTLESHIP" : battleValue.setText(str);
            break;
            case "CRUISER" : cruiserValue.setText(str);
            break;
            case "DESTROYER" : destroyerValue.setText(str);
            break;
            case "SUBMARINE" : submarineValue.setText(str);
            break;
        }
    }

    public void setPanelBackgroundColor(int possible, List<ICoord> coordList, boolean showOurFleet,boolean myGrille,boolean clicked){
        if(possible == 3){
            for (ICoord coord : coordList){
                int x = coord.getX()-1;
                int y = coord.getY()-1;
                this.myGrille[x][y].setBackground(new Color(0, 200, 255));
            }
        }

        if(showOurFleet){
        	for(int i=0;i<10;i++) {
        		for(int y=0;y<10;y++) {
        			this.myGrille[i][y].setBackground(new Color(0,200,255));
        		}
        	}
            for (IShip bateau : this.flotte.getShips()){
                for(ICoord coord : bateau.getCoords()){
                    int x = coord.getX()-1;
                    int y = coord.getY()-1;
                    if(myGrille) {
                        this.myGrille[x][y].setBackground(Color.LIGHT_GRAY);
                    }else {
                        this.guestGrille[x][y].setBackground(Color.LIGHT_GRAY);
                    }
                }

            }
        }


        for (ICoord coord : coordList){
            int x = coord.getX()-1;
            int y = coord.getY()-1;
            if(possible == 1){
                if(myGrille) {
                    this.myGrille[x][y].setBackground(Color.GREEN);
                }else {
                    this.guestGrille[x][y].setBackground(Color.GREEN);
                    if (clicked){
                        ((JLabel)this.guestGrille[x][y].getComponent(0)).setText("1");
                    }
                }
            }else if(possible == 2){
                if(myGrille) {
                    this.myGrille[x][y].setBackground(Color.RED);
                }
                else {
                    this.guestGrille[x][y].setBackground(Color.RED);
                    if(clicked){
                        ((JLabel)this.guestGrille[x][y].getComponent(0)).setText("2");
                    }
                }
            }else if(possible == 4){
                switch (((JLabel)this.guestGrille[x][y].getComponent(0)).getText()){
                    case "1" : this.guestGrille[x][y].setBackground(Color.GREEN);
                        break;
                    case "2" : this.guestGrille[x][y].setBackground(Color.RED);
                    default: this.guestGrille[x][y].setBackground(Color.LIGHT_GRAY);
                }
            }
        }

        /*if(!myGrille && clicked){
            for (int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    switch (((JLabel)this.guestGrille[i][j].getComponent(0)).getText()){
                        case "1" : this.guestGrille[i][j].setBackground(Color.GREEN);
                        break;
                        case "~" : this.guestGrille[i][j].setBackground(Color.RED);
                        default: this.guestGrille[i][j].setBackground(Color.LIGHT_GRAY);
                    }
                }

            }
        }*/
    }

    public int sumBateaux() {
        int sum = 0;
        for(int i=0;i<5;i++) {
            sum+= this.bateaux[i];
        }
        return sum;
    }

    public int getTextBateauCouleValue(){
        return Integer.valueOf(textBateauCouleValue.getText());
    }

    public void setTextBateauCouleValue(String str){
        textBateauCouleValue.setText(str);
    }

    public int getTextCaseRestanteValue(){
        return Integer.valueOf(textCaseRestanteValue.getText());
    }

    public void setTextCaseRestanteValue(String str){
        textCaseRestanteValue.setText(str);
    }

    public void enableCreateGameButton(boolean enable){
        createGameButton.setEnabled(enable);
    }

    public void enableJoinGameButton(boolean enable){
        btRejoindrePartie.setEnabled(enable);
    }

    public void enableReplayButton(boolean enable){
        replayButton.setEnabled(enable);
    }

    public void setVisibleChooseShipPanel(boolean enable){
        chooseShipPanel.setVisible(enable);
    }

    public void setVisibleGuestGrillePanel(boolean enable){
        guestGrillePanel.setVisible(enable);
    }

    public void setVisibleRightPanelLobby(boolean enable){
        rightPanelLobby.setVisible(enable);
    }

    public void setVisibleRightPanelGame(boolean enable){
        rightPanelGame.setVisible(enable);
    }

    public void setSearchGame(List<Game> games_list) {
        this.listePartie.removeAllItems();
    	for(Game each: games_list) {
    		this.listePartie.addItem(each);
    	}
        System.out.println("Recharge"+(Game)this.listePartie.getSelectedItem());
    }


    public void setFleetsList(List<String> fleets_list) {
    	this.maListeFlottes.removeAllItems();
    	this.maListeFlottes.addItem("Charger une flotte existante...");
    	for(String each: fleets_list) {
    		this.maListeFlottes.addItem(each);
    	}
    }


    public Game getSelectedGame(){
        return (Game)this.listePartie.getSelectedItem();
    }

    public String getSaveFleetTextField(){
        return this.saveFleetTextField.getText();
    }
    public void setGame(Game game){
        currentGame = game;
    }

    public Game getGame(){
        return currentGame;
    }


    public String getIp() {
    	return this.ip;
    }

    public void setErrorCreate(String str){
        this.errorCreate.setText(str);
    }

    public void setErrorJoin(String str){
        this.errorJoin.setText(str);
    }

    public void setErrorSave(String str, Color color){
        this.errorSave.setText(str);
        this.errorSave.setForeground(color);
    }

    public void setEndGameMessage(String str,Color color){
        this.endGameMessage.setText(str);
        this.endGameMessage.setForeground(color);
    }
    
    
    public String getTextArea() {
    	return this.saveFleetTextField.getText();
    }
    



    public void myTurnToPlay(boolean myTurn){
        if(myTurn){
            myGrillePanel.setBorder(BorderFactory.createTitledBorder(     ""));
            guestGrillePanel.setBorder(BorderFactory.createTitledBorder("A mon tour"));

        }else{
            guestGrillePanel.setBorder(BorderFactory.createTitledBorder("    "));
            myGrillePanel.setBorder(BorderFactory.createTitledBorder("Au tour adverse"));
        }

    }
    
    public void setFlotte(NavyFleet flotte) {
    	this.flotte = flotte;
    }

    public NavyFleet getFlotte() {
        return this.flotte;
    }

    public void setAdversaireName(String str){
        nameLabel2.setText(str);
    }
    
    public String getSelectedFleet() {
    	return this.maListeFlottes.getSelectedItem().toString();
    }

    public void playSound(int sound) {
        final int BUFFER_SIZE = 128000;
        File soundFile;
        AudioInputStream audioStream;
        AudioFormat audioFormat;
        SourceDataLine sourceLine;
        String strFilename = "";

        switch (sound) {
            case 1:
                strFilename = "sound/miss.wav";
                break;
            case 2:
                strFilename = "sound/torpedo.wav";
                break;
            case 3:
                strFilename = "sound/win.wav";
                break;
            case 4:
                strFilename = "sound/join.wav";
                break;
        }

        try {
            soundFile = new File(strFilename);
            try {
                audioStream = AudioSystem.getAudioInputStream(soundFile);
                audioFormat = audioStream.getFormat();

                DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                try {
                    sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                    sourceLine.open(audioFormat);

                    sourceLine.start();

                    int nBytesRead = 0;
                    byte[] abData = new byte[BUFFER_SIZE];
                    while (nBytesRead != -1) {
                        try {
                            nBytesRead = audioStream.read(abData, 0, abData.length);
                            if (nBytesRead >= 0) {
                                @SuppressWarnings("unused")
                                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    sourceLine.drain();
                    sourceLine.close();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                    System.exit(1);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    }



