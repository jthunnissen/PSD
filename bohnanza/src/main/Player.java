package main;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author      Anne van de Venis
 * @version     1.0                  
 * @since       2012-11-22
 */
public class Player {

	/**
	 * Represents the fields this Player has.
	 * @uml.property  name="fields"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="player:main.Field"
	 */
	private ArrayList<Field> fields;

	/**
	 * The score of this player.
	 * @uml.property  name="score"
	 */
	private int score = 0;

	
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the fields this Player has.
	 * @return Fields that belongs to this Player.
	 * @return  Returns the fields.
	 * @uml.property  name="fields"
	 */
	public ArrayList<Field> getFields() {
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
