package bohnanza.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.Protocol;

public abstract class Field<CardType extends Card> implements ToJSON {

	/** @uml.property name="card"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true"
	 *                     inverse="field:main.Card" */
	protected ArrayList<CardType> cards = new ArrayList<CardType>();

	/** Getter of the property <tt>card</tt>
	 * @return Returns the cards.
	 * @uml.property name="card" */
	public ArrayList<CardType> getCards() {
		return cards;
	}

	/** Get the amount of cards in this field.
	 * @return amount of cards. */
	public int getSize() {
		return cards.size();
	}

	public void addCard(CardType card) throws IllegalActionException {
		if(!cards.add(card))
			throw new IllegalActionException("Card already in field");
	}

	public void addAllCards(Collection<CardType> cards) throws IllegalActionException {
		for(CardType card : cards) {
			if(cards.contains(card))
				throw new IllegalActionException("Card already in field");
		}
		cards.addAll(cards);
	}

	public CardType removeCard() throws IllegalActionException {
		try {
			return cards.remove(cards.size() - 1);
		} catch(IndexOutOfBoundsException e) {
			throw new IllegalActionException("Field is empty");
		}
	}

	/** Harvests this field. It clears this field and returns the cards that this
	 * field contained.
	 * @post this.getCards().size() == 0
	 * @return The cards that are harvested from this field. */
	public ArrayList<CardType> harvest() {
		ArrayList<CardType> result = cards;
		cards = new ArrayList<CardType>();
		return result;
	}

	/** @return whether the field contains cards */
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	public JSONObject toJSON(HashMap<Integer, Card> cardIndex) {
		JSONObject result = new JSONObject();

		try {
			if(cards.size() == 0) {
				result.put(Protocol.CARD_NAME, "");
				result.put(Protocol.CARD_SCORE, 0);
			} else {
				result.put(Protocol.CARD_NAME, cards.get(0).getName());
				result.put(Protocol.CARD_SCORE, cards.size());
			}
			result.put(Protocol.CARD_HASHCODE, hashCode());
		} catch(JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

}
