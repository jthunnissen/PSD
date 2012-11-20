package main;

import java.util.ArrayList;
import java.util.Collection;


public class Player {

	/**
	 * @uml.property  name="fields"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="player:main.Field"
	 */
	private ArrayList fields;

	/**
	 * Getter of the property <tt>fields</tt>
	 * @return  Returns the fields.
	 * @uml.property  name="fields"
	 */
	public ArrayList getFields() {
		return fields;
	}

	/**
	 * Setter of the property <tt>fields</tt>
	 * @param fields  The fields to set.
	 * @uml.property  name="fields"
	 */
	public void setFields(ArrayList fields) {
		this.fields = fields;
	}

	/**
	 * @uml.property  name="hand"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="player:main.Card"
	 */
	private ArrayList<Card> hand;

	/**
	 * Getter of the property <tt>hand</tt>
	 * @return  Returns the hand.
	 * @uml.property  name="hand"
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}

	/**
	 * Setter of the property <tt>hand</tt>
	 * @param hand  The hand to set.
	 * @uml.property  name="hand"
	 */
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	/**
	 * @uml.property  name="score"
	 */
	private int score = 0;

	/**
	 * Getter of the property <tt>score</tt>
	 * @return  Returns the score.
	 * @uml.property  name="score"
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Setter of the property <tt>score</tt>
	 * @param score  The score to set.
	 * @uml.property  name="score"
	 */
	public void setScore(int score) {
		this.score = score;
	}

}
