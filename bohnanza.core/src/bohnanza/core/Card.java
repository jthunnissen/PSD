package bohnanza.core;

import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.Protocol;

/**
 * This class respresents a Card
 * @author Anne
 *
 */
public abstract class Card implements ToJSON {

	/**
	 * Name of the card 
	 * @uml.property name="name"
	 */
	private final String name;

	/**
	 * Number of cards that should be created in the gamedeck
	 *  @uml.property name="numberOfCards" 
	 */
	private final int numberOfCards;

	public Card(String name, int numberOfCards) {
		this.name = name;
		this.numberOfCards = numberOfCards;
	}

	/** Getter of the property <tt>name</tt>
	 * @return Returns the name.
	 * @uml.property name="name" */
	public String getName() {
		return name;
	}

	/** Getter of the property <tt>numberOfCards</tt>
	 * @return Returns the numberOfCards.
	 * @uml.property name="numberOfCards" */
	public int getNumberOfCards() {
		return numberOfCards;
	}

	/**
	 * Creates a JSON object for this card.
	 */
	public JSONObject toJSON(HashMap<Integer, Card> cardIndex) {
		JSONObject result = new JSONObject();
		try {
			result.put(Protocol.CARD_NAME, name);
			result.put(Protocol.CARD_SCORE, 0);
			result.put(Protocol.CARD_HASHCODE, this.hashCode());
		} catch(JSONException e) {
			e.printStackTrace();
		}
		cardIndex.put(hashCode(), this);
		return result;
	}

}
