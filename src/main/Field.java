package main;

import java.util.ArrayList;


public class Field<Card> {

	/** 
	 * @uml.property name="card"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="field:main.Card"
	 */
	private ArrayList cards;

	/** 
	 * Getter of the property <tt>card</tt>
	 * @return  Returns the cards.
	 * @uml.property  name="card"
	 */
	public ArrayList getCard() {
		return cards;
	}

	/** 
	 * Setter of the property <tt>card</tt>
	 * @param card  The cards to set.
	 * @uml.property  name="card"
	 */
	public void setCard(ArrayList card) {
		cards = card;
	}

}
