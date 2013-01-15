package bohnanza.standard.core;

import java.util.ArrayList;


public abstract class Field {

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

}
