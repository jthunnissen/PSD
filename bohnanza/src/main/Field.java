package main;

import java.util.ArrayList;


public abstract class Field<T extends Card> {

	/** 
	 * @uml.property name="card"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="field:main.Card"
	 */
	protected ArrayList<T> cards = new ArrayList<T>();

	/** 
	 * Getter of the property <tt>card</tt>
	 * @return  Returns the cards.
	 * @uml.property  name="card"
	 */
	public ArrayList<T> getCard() {
		return cards;
	}

	public boolean addCard(T card) {
		return cards.add(card);
	}

}
