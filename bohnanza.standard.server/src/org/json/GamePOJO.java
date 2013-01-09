package org.json;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class GamePOJO {

	private String currentPlayer;
	private ArrayList<PlayerPOJO> players;
	private ArrayList<CardPOJO> faceup;
	
	private ArrayList<String> actions;
	
	public GamePOJO(String currentPlayer, ArrayList<PlayerPOJO> players, ArrayList<CardPOJO> faceup, ArrayList<String> actions){
		this.currentPlayer = currentPlayer;
		this.players = players;
		this.faceup = faceup;
		this.actions = actions;
	}
	
	public String getCurrentPlayer(){
		return currentPlayer;
	}
	
	public ArrayList<PlayerPOJO> getPlayers(){
		return players;
	}
	
	public ArrayList<CardPOJO> getFaceup(){
		return faceup;
	}
	
	public ArrayList<String> getActions(){
		return actions;
	}
	
}
