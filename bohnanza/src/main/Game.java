/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Damiaan
 * @uml.dependency supplier="main.GameFactory" stereotypes="Standard::Call"
 */
public class Game {

	/**
	 * @uml.property name="currentState"
	 * @uml.associationEnd inverse="context:main.TurnState"
	 */
	private TurnState currentState = null;

	/**
	 * @uml.property name="players"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Player"
	 */
	private ArrayList<Player> players = new ArrayList<Player>();

	/**
	 * @uml.property name="drawDeck"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Card"
	 */
	private ArrayList<Card> drawDesk = new ArrayList<Card>();

	/**
	 * @uml.property name="discardPile"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Card"
	 */
	private ArrayList<Card> discardPile = new ArrayList<Card>();

	/**
	 * @uml.property name="faceUpCards"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Card"
	 */
	private FaceUpCard[] faceUpCards = new FaceUpCard[2];

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
		 */
	public Game(int players) {
		GameFactory.getInstance().createGameStates(this);
	}

	/**
	 * Getter of the property <tt>currentState</tt>
	 * 
	 * @return Returns the currentState.
	 * @uml.property name="currentState"
	 */
	public TurnState getCurrentState() {
		return currentState;
	}

	/**
	 * Getter of the property <tt>players</tt>
	 * 
	 * @return Returns the players.
	 * @uml.property name="players"
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public boolean addPlayer(Player player) {
		return this.players.add(player);
	}

	class FaceUpCard {
		boolean setAside = false;
		Card card = null;

		public FaceUpCard(Card card) {
			this.card = card;
		}
	}

	public void drawFaceUpCards() {
		Card card = drawCard();
		faceUpCards[0] = new FaceUpCard(card);
		Card morecard = drawCard();
		faceUpCards[1] = new FaceUpCard(morecard);
	}

	public Card drawCard() {
		return null;
	}

	public boolean setAsideFaceUpCard(int i) {
		if (faceUpCards == null)
			return false;
		if (faceUpCards[i].setAside)
			return false;
		faceUpCards[i].setAside = true;
		return true;
	}
}
