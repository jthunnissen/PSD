package org.json;

import javafx.scene.image.Image;

public class CardPOJO {

	private String name;
	private String score;
	
	public CardPOJO(String name, String score){
		this.name = name;
		this.score = score;
	}
	
	public CardPOJO(JSONObject jsonCard){
		try {
			this.name = jsonCard.getString(Protocol.CARD_NAME);
			this.score = jsonCard.getString(Protocol.CARD_SCORE);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getName(){
		return name;
	}
	
	public String getScore(){
		return score;
	}
	
	public Image getImage(){
		Image result = null;
		String filename = "res/cards/"+name+".png";		
		result = new Image(this.getClass().getClassLoader().getResourceAsStream(filename));
		return result;
	}
}
