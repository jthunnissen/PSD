package org.json;

import java.util.ArrayList;

public class GamePOJO {

	private PlayerPOJO currentPlayer;
	private ArrayList<PlayerPOJO> players;
	private ArrayList<CardPOJO> faceup;

	private PlayerPOJO thisPlayer;
	private ArrayList<String> actions;

	public GamePOJO(PlayerPOJO currentPlayer, PlayerPOJO thisPlayer,
			ArrayList<PlayerPOJO> players) {
		this.currentPlayer = currentPlayer;
		this.thisPlayer = thisPlayer;
		this.players = players;
	}

	public PlayerPOJO getCurrentPlayer() {
		return currentPlayer;
	}

	public PlayerPOJO getThisPlayer() {
		return thisPlayer;
	}

	public ArrayList<PlayerPOJO> getPlayers() {
		return players;
	}

	public ArrayList<CardPOJO> getFaceup() {
		return faceup;
	}

	public ArrayList<String> getActions() {
		return actions;
	}

}
