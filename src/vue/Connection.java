package vue;

import controleur.Connection.ConnectButtonControleur;
import info1.network.Network;

import javax.swing.*;


import java.awt.*;

public class Connection extends JFrame {

    private JPanel principal;
    private JTextField name;
    private JTextField ip;
    private Network net;
    private JLabel errorPseudo;
    private JLabel errorIp;

    public Connection(String titre){
        super(titre);

    	Network net = new Network();
        //Setup le panel principal
        principal = new JPanel();
        principal.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Setup du panel pour aligner verticalement
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));

        //Création des JPanels et des composants
        //Première ligne
        JPanel labelPseudoPanel = new JPanel();
        labelPseudoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel label = new JLabel("Choisissez un pseudonyme");

        labelPseudoPanel.add(label);

        //Deuxième ligne
        JPanel textFieldPseudoPanel = new JPanel();
        textFieldPseudoPanel.setLayout(new GridLayout(2,1));

        name = new JTextField();
        name.setPreferredSize(new Dimension(100,20));
        name.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.errorPseudo = new JLabel("");
        this.errorPseudo.setVisible(false);
        this.errorPseudo.setForeground(Color.RED);
        this.errorPseudo.setLayout(new FlowLayout(FlowLayout.CENTER));

        textFieldPseudoPanel.add(name);
        textFieldPseudoPanel.add(this.errorPseudo);


        //Troisième ligne
        JPanel labelIpPanel = new JPanel();
            labelIpPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel labelIp = new JLabel("Adresse IP du serveur");
        labelIpPanel.add(labelIp);

        //Quatrième ligne
        JPanel textFieldIpPanel = new JPanel();
        textFieldIpPanel.setLayout(new GridLayout(2,1));

        ip = new JTextField();
        ip.setPreferredSize(new Dimension(100,20));
        ip.setLayout(new FlowLayout(FlowLayout.CENTER));
        ip.setText("localhost");

        this.errorIp = new JLabel("");
        this.errorIp.setVisible(false);
        this.errorIp.setForeground(Color.RED);
        this.errorIp.setLayout(new FlowLayout(FlowLayout.CENTER));

        textFieldIpPanel.add(ip);
        textFieldIpPanel.add(this.errorIp);


        //Cinquième ligne
        JPanel connectButtonPanel = new JPanel();
        connectButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton connectButton = new JButton("Connexion");
        connectButtonPanel.add(connectButton);

        //Controleur
        ConnectButtonControleur buttonControleur = new ConnectButtonControleur(this, net);
        connectButton.addActionListener(buttonControleur);

        //Ajout des panels au panel central
        centerPanel.add(labelPseudoPanel);
        centerPanel.add(textFieldPseudoPanel);
        centerPanel.add(labelIpPanel);
        centerPanel.add(textFieldIpPanel);
        centerPanel.add(connectButtonPanel);

        //Ajout du panel central au principal
        principal.add(centerPanel);

        //Config la frame
        this.setContentPane(principal);
        this.setPreferredSize(new Dimension(300,300));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    
    public String getName() {
    	return this.name.getText();
    }
    
    public String getIp() {
    	return this.ip.getText();
    }
    
    public void showErrorPseudo(String error) {
    	this.errorPseudo.setText(error);
    	this.errorPseudo.setVisible(true);
    }
    public void showErrorIP(String error) {
        this.errorIp.setText(error);
        this.errorIp.setVisible(true);
    }
    public static void main(String[] args) {
    	Connection appli = new Connection("Bataille navale");
		appli.setLocation(100,100);
        appli.pack();
        appli.setVisible(true);

	}
    

}
