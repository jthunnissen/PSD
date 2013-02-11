package bohnanza.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.Protocol;

/**
 * This class represents a field
 * @author Anne
 *
 * @param <CardType>
 */
public abstract class Field<CardType extends Card> implements ToJSON {

	/** 
	 * Cards that are added in this field
	 * @uml.property name="card"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true"
	 *                     inverse="field:main.Card" */
	protected List<CardType> cards = new ArrayList<CardType>();

	/** Getter of the property <tt>card</tt>
	 * @return Returns the cards.
	 * @uml.property name="card" */
	public List<CardType> getCards() {
		return Collections.unmodifiableList(cards);
	}

	/** Get the amount of cards in this field.
	 * @return amount of cards. */
	public int getSize() {
		return cards.size();
	}

	/**
	 * Adds a card to this fields. If this field already contains a card, the new card should be of the same type.
	 * @param card Card that should be added
	 * @throws IllegalActionException
	 */
	public void addCard(CardType card) throws IllegalActionException {
		if(cards.size() > 0) {
			if (!cards.get(0).equals(card)) {
				throw new IllegalActionException("Card must be of type " + cards.get(0).getName());
			}
		}
		cards.add(card);
	}

	/**
	 * Adds a list of cards to this field.
	 * @param cards Cards that should be added to this field
	 * @throws IllegalActionException
	 */
	public void addAllCards(Collection<CardType> cards) throws IllegalActionException {
		for(CardType card : cards) {
			addCard(card);
		}
	}

	/**
	 * Removes the newest card from this field 
	 * @return The card that is removed
	 * @throws IllegalActionException
	 */
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
	public List<CardType> harvest() {
		List<CardType> result = cards;
		cards = new ArrayList<CardType>();
		return result;
	}

	/** @return whether the field contains cards */
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	/**
	 * Number of cards in this field
	 * @return
	 */
	public int size() {
		return cards.size();
	}
	
	/**
	 * Get the first card present in the field.
	 * @return The first card, if not present it is null.
	 */
	public CardType getFirstCard() {
		if(isEmpty()) return null;
		return cards.get(0);
	}

	/**
	 * Creates a JSON Object for this field.
	 */
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
