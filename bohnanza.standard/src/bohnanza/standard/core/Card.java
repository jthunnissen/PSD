package bohnanza.standard.core;

import org.json.JSONException;
import org.json.JSONObject;

import bohnanza.standard.server.Protocol;

public abstract class Card implements ToJSON {

	/**
	 * @uml.property  name="name"
	 */
	private final String name;

	/**
	 * @uml.property  name="numberOfCards"
	 */
	private final int numberOfCards;

	public Card(String name, int numberOfCards) {
		this.name = name;
		this.numberOfCards = numberOfCards;
	}

	/**
	 * Getter of the property <tt>name</tt>
	 * @return  Returns the name.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter of the property <tt>numberOfCards</tt>
	 * @return  Returns the numberOfCards.
	 * @uml.property  name="numberOfCards"
	 */
	public int getNumberOfCards() {
		return numberOfCards;
	}
	
	public JSONObject getJSON(){
		JSONObject result = new JSONObject();
		try {
			result.put(Protocol.CARD_NAME, name);
			result.put(Protocol.CARD_SCORE, "0");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
