package bohnanza.standard.core;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import bohnanza.standard.server.Protocol;


public abstract class Field implements ToJSON {

	/** 
	 * @uml.property name="card"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="field:main.Card"
	 */
	protected ArrayList<Card> cards = new ArrayList<Card>();

	/** 
	 * Getter of the property <tt>card</tt>
	 * @return  Returns the cards.
	 * @uml.property  name="card"
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	/**
	 * Get the amount of cards in this field.
	 * @return amount of cards.
	 */
	public int getSize() {
		return cards.size();
	}

	public void addCard(Card card) throws IllegalActionException {
		if(!cards.add(card)) throw new IllegalActionException("Card already in field");
	}
	
	/**
	 * Harvests this field. It clears this field and returns the cards that this field contained.
	 * @post this.getCards().size() == 0
	 * @return The cards that are harvested from this field.
	 */
	public ArrayList<Card> harvest(){
		ArrayList<Card> result = cards;
		cards = new ArrayList<Card>();
		return result; 
	}
	
	public JSONObject toJSON(){
		JSONObject result = new JSONObject();

		try {
			if(cards.get(0) == null){
				result.put(Protocol.CARD_NAME, "");
				result.put(Protocol.CARD_SCORE, "0");
			} else {
				result.put(Protocol.CARD_NAME, cards.get(0).getName());
				result.put(Protocol.CARD_SCORE, ""+cards.size());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

}
