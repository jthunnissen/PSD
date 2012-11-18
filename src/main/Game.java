package main;
import java.util.List;


public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * @uml.property  name="nrOfPlayers"
	 */
	private String nrOfPlayers;

	/**
	 * Getter of the property <tt>nrOfPlayers</tt>
	 * @return  Returns the nrOfPlayers.
	 * @uml.property  name="nrOfPlayers"
	 */
	public String getNrOfPlayers() {
		return nrOfPlayers;
	}

	/**
	 * Setter of the property <tt>nrOfPlayers</tt>
	 * @param nrOfPlayers  The nrOfPlayers to set.
	 * @uml.property  name="nrOfPlayers"
	 */
	public void setNrOfPlayers(String nrOfPlayers) {
		this.nrOfPlayers = nrOfPlayers;
	}

	/**
	 * @uml.property  name="deck"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="game:main.Card"
	 */
	private List deck;

	/**
	 * Getter of the property <tt>deck</tt>
	 * @return  Returns the deck.
	 * @uml.property  name="deck"
	 */
	public List getDeck() {
		return deck;
	}

	/**
	 * Setter of the property <tt>deck</tt>
	 * @param deck  The deck to set.
	 * @uml.property  name="deck"
	 */
	public void setDeck(List deck) {
		this.deck = deck;
	}

	/**
	 * @uml.property  name="discardPile"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="game:main.Card"
	 */
	private List discardPile;

	/**
	 * Getter of the property <tt>discardPile</tt>
	 * @return  Returns the discardPile.
	 * @uml.property  name="discardPile"
	 */
	public List getDiscardPile() {
		return discardPile;
	}

	/**
	 * Setter of the property <tt>discardPile</tt>
	 * @param discardPile  The discardPile to set.
	 * @uml.property  name="discardPile"
	 */
	public void setDiscardPile(List discardPile) {
		this.discardPile = discardPile;
	}

	/** 
	 * @uml.property name="players"
	 * @uml.associationEnd readOnly="true" multiplicity="(0 -1)" dimension="1" ordering="true" inverse="game:main.Player"
	 */
	private Player[] players;

	/** 
	 * @uml.property name="turnState"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="context:main.TurnState"
	 */
	private TurnState turnState = new TurnState();

	/**
		 */
		public static Game getInstance(){
			return null;
		}

		/**
		 * Getter of the property <tt>players</tt>
		 * @return  Returns the players.
		 * @uml.property  name="players"
		 */
		public Player getPlayer(int i) {
			return players[i];
		}

}
