package org.json;

import javafx.scene.image.Image;

public class CardPOJO {

	private String name;
	private int score;
	private int hashcode;
	
	public CardPOJO(String name, int score, int hashcode){
		this.name = name;
		this.score = score;
		this.hashcode = hashcode;
	}
	
	public CardPOJO(JSONObject jsonCard){
		this.name = jsonCard.optString(Protocol.CARD_NAME);
		this.score = jsonCard.optInt(Protocol.CARD_SCORE);
		this.hashcode = jsonCard.optInt(Protocol.CARD_HASHCODE);
		
	}
	
	public String getName(){
		return name;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getHashcode(){
		return hashcode;
	}
	
	public Image getImage(){
		Image result = null;
		String filename = "res/cards/"+name+".png";		
		result = new Image(this.getClass().getClassLoader().getResourceAsStream(filename));
		return result;
	}
}
