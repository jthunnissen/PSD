package org.json;

import java.util.ArrayList;

public class PlayerPOJO {

	private String name;
	private String score;
	private ArrayList<CardPOJO> hand;
	private ArrayList<CardPOJO> fields;
	
	
	public PlayerPOJO(String name, String score, ArrayList<CardPOJO> hand, ArrayList<CardPOJO> fields){
		this.name = name;
		this.score = score;
		this.hand = hand;
		this.fields = fields;
	}
	
	public String getName(){
		return name;
	}
	
	public String getScore(){
		return score;
	}
	
	public ArrayList<CardPOJO> getHand(){
		return hand;
	}
	
	public ArrayList<CardPOJO> getFields(){
		return fields;
	}
	
	
	
}