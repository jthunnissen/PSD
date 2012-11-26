package main;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Player {

	/**
	 * Represents the fields this Player has.
	 * @uml.property  name="fields"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="player:main.Field"
	 */
	private ArrayList<Field> fields;

	/**
	 * Represents the cards in the hand of this Player.
	 * @uml.property  name="hand"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="player:main.Card"
	 */
	private ArrayList<Card> hand;

	/**
	 * Represents the treasury of this Player
	 * @uml.property  name="coins"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="player:main.Card"
	 */
	private ArrayList<Card> coins;

	private ArrayList<Card> aSideCards = new ArrayList<Card>();

	private ArrayList<Card> drawnCards = new ArrayList<Card>();

	/**
	 * The name from this Player.
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

	public Player(String name) {
		this.name = name;
	}

	/**
	 * Calculates the score, all the cards in this Players' treasury.
	 * @return score
	 */
	public int calcScore() {
		return -1;
	}


	/**
	 * Adds a card to the players hand.
	 * @param card Card that will be added the Players' hand
	 * @return true if the Card is successfully added to the Player's hand.
	 */
	public boolean addCardToHand(Card card){
		return hand.add(card);
	}

	/**
	 * Harvast the Players' field.
	 * @param fieldnr Number of the field that will be harvasted.
	 * @return Point that will be added to the players treasury.
	 */
	public int harvastField(int fieldnr){
		//TODO
		return 0;
	}

	/**
	 * The first BeanCard in the Players' hand will be planted on a Players' field.
	 * @param fieldnr Number of the field where the card will be planted.
	 * @return true if the BeanCard is successfully planted in the Players' field.
	 */
	public boolean plantBean(int fieldnr) {
		try {
			BeanCard bean = takeBean();
			Field<Card> field = fields.get(fieldnr);
			return field.addCard(bean);
		} catch (ArrayIndexOutOfBoundsException ex) {
			return false;
		}
	}

	/**
	 * Returns the first BeanCard in the Players' hand.
	 * @return The first (newest) BeanCard in the Players' hand.
	 * @throws ArrayIndexOutOfBoundsException
	 */
	private BeanCard takeBean() throws ArrayIndexOutOfBoundsException {
		return (BeanCard) hand.get(0);
	}

	/**
	 * Getter of the Players' hand
	 * @return The hand of this Player.
	 */
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	/**
	 * Getter of the Players' fields
	 * @return The fields of this Player.
	 */
	public ArrayList<Field> getFields(){
		return fields;
	}

	/**
	 * Perform a trade or donation
	 * @param receive
	 * @param give
	 * @throws IllegalOperationException if not isValidTrade(receive,give)
	 */
	public void trade(List<Card> receive, List<Card> give, boolean active) throws IllegalOperationException {
		if(!isValidTrade(receive, give, active)) throw new IllegalOperationException();
		hand.removeAll(give);
		if(active) drawnCards.removeAll(give);
	}

	public boolean isValidTrade(List<Card> receive, List<Card> give, boolean active) {
		boolean valid = true;
		for(Card take: give) {
			if (!hand.contains(take) && !(active && drawnCards.contains(take))) valid = false;
		}
		for(Card take: receive) {
			if (hand.contains(take)) valid = false;
		}
		return valid;
	}

}
