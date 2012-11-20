package main;

import java.util.ArrayList;


public abstract class Field<T extends Card> {

	/** 
	 * @uml.property name="card"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="field:main.Card"
	 */
	private ArrayList<T> cards;

	/** 
	 * Getter of the property <tt>card</tt>
	 * @return  Returns the cards.
	 * @uml.property  name="card"
	 */
	public ArrayList<T> getCard() {
		return cards;
	}

	/** 
	 * Setter of the property <tt>card</tt>
	 * @param card  The cards to set.
	 * @uml.property  name="card"
	 */
	public void setCard(ArrayList<T> card) {
		cards = card;
	}

	/**
	 * @uml.property  name="name"
	 */
	private String name;

	/**
	 * Getter of the property <tt>name</tt>
	 * @return  Returns the name.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of the property <tt>name</tt>
	 * @param name  The name to set.
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

}
