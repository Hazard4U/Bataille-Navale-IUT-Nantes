package controleur.Game;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

import info1.network.Game;
import info1.network.Network;
import vue.FenetrePrincipale;

public class SelectGameController implements ActionListener {
	private FenetrePrincipale vue;
	private Network net;

	public SelectGameController(FenetrePrincipale vue) {
		super();
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Right click controler");
		List<Game> games_list = new ArrayList<Game>();
		try {
			System.out.println("show me");
			games_list = Network.listInitializedGames("http://"+this.vue.getIp()+":8888/api/v0");
			System.out.println(games_list);
			if(games_list.size() == 0) {
				this.vue.enableJoinGameButton(false);
			} else {
				this.vue.enableJoinGameButton(true);
				this.vue.setSearchGame(games_list);
			}

		} catch (UnirestException e1) {
			e1.printStackTrace();
		}
	}
}
