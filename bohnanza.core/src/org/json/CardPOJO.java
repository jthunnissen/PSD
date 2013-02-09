package org.json;

import javafx.scene.image.Image;

/** This class is a data container for a card in the GUi 
*
* @author Anne van de Venis
* @version 1.0
*/
public class CardPOJO {

	/**
	 * Name of this card
	 */
	private String name;
	/**
	 * Score of this card
	 */
	private int score;
	/**
	 * Unique ID (hashcode) of the card
	 */
	private int hashcode;

	public CardPOJO(String name, int score, int hashcode) {
		this.name = name;
		this.score = score;
		this.hashcode = hashcode;
	}

	public CardPOJO(JSONObject jsonCard) {
		this.name = jsonCard.optString(Protocol.CARD_NAME);
		this.score = jsonCard.optInt(Protocol.CARD_SCORE);
		this.hashcode = jsonCard.optInt(Protocol.CARD_HASHCODE);

	}

	/**
	 * Returns name from this card
	 * @return Name from this card
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns score from this card
	 * @return Score from this cadr
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Returns unique ID (hashcode) from this card
	 * @return Hashcode from this card
	 */
	public int getHashcode() {
		return hashcode;
	}

	/**
	 * Returns image from this card
	 * @return Image from this card
	 */
	public Image getImage() {
		Image result = null;
		String filename = "res/cards/" + name + ".png";
		result = new Image(this.getClass().getClassLoader().getResourceAsStream(filename));
		return result;
	}
}
