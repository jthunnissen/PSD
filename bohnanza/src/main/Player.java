package main;

import java.util.ArrayList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Player {

	/**
	 * Represents the fields this Player has.
	 * 
	 * @uml.property name="fields"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true"
	 *                     inverse="player:main.Field"
	 */
	private ArrayList<Field> fields;

	/**
	 * @uml.property name="hand"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="player:main.Card"
	 */
	private ArrayList<Card> hand;

	/**
	 * @uml.property name="coins"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="player:main.Card"
	 */
	private ArrayList<Card> coins;

	/**
	 * @uml.property name="name"
	 */
	private String name;

	private ArrayList<Card> aSideCards = new ArrayList<Card>();

	private ArrayList<Card> drawnCards = new ArrayList<Card>();

	/**
	 * Getter of the property <tt>name</tt>
	 * 
	 * @return Returns the name.
	 * @uml.property name="name"
	 */
	public String getName() {
		return name;
	}

	public Player(String name) {
		this.name = name;
	}

	public int calcScore() {
		return -1;
	}

	public boolean plantBean(int fieldnr) {
		try {
			BeanCard bean = takeBean();
			Field<Card> field = fields.get(fieldnr);
			return field.addCard(bean);
		} catch (ArrayIndexOutOfBoundsException ex) {
			return false;
		}
	}

	public boolean addASideCard(Card card) {
		return aSideCards.add(card);
	}

	public boolean addDrawnCard(Card card) {
		return drawnCards.add(card);
	}

	private BeanCard takeBean() throws ArrayIndexOutOfBoundsException {
		return (BeanCard) hand.get(0);
	}

	public int getScore() {
		throw new NotImplementedException();
	}

}
