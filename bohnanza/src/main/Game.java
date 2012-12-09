/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Collections;
import states.TurnState;

/**
 * @author Damiaan
 * @uml.dependency supplier="main.GameFactory" stereotypes="Standard::Call"
 */
public class Game {

	/**
	 * @uml.property name="currentState"
	 * @uml.associationEnd inverse="context:main.TurnState"
	 */
	private TurnState currentState;

	/**
	 * @uml.property name="currentPlayer"
	 * @uml.associationEnd inverse="turnState:main.Player"
	 */
	private Player activePlayer;
	
	/**
	 * @uml.property name="players"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Player"
	 */
	private ArrayList<Player> players = new ArrayList<Player>();

	/**
	 * @uml.property name="drawDeck"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Card"
	 */
	private ArrayList<Card> drawDeck = new ArrayList<Card>();

	/**
	 * @uml.property name="discardPile"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Card"
	 */
	private ArrayList<Card> discardPile = new ArrayList<Card>();

	private int currentPlayerIndex = 0;
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

	/**
		 */
	public Game() {
		GameFactory factory = GameFactory.getInstance();
		drawDeck = factory.getGameDeck();
		shuffleCards();
		//GameFactory.getInstance().createGameStates(this);
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
	 * @param currentState the currentState to set
	 */
	public void setCurrentState(TurnState currentState) {
		this.currentState = currentState;
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
	
	public boolean addCardToDiscardPile(Card card){
		return this.discardPile.add(card);
	}

	/**Draws one card from the draw deck and shuffles the deck it if this depletes it.
	 * @require !drawDeck.isEmpty()
	 */
	public Card drawCard() {
		Card drawnCard = drawDeck.remove(0);
		if(drawDeck.isEmpty()) {
			drawDeck.addAll(discardPile);
			Collections.shuffle(drawDeck);
		}
		return drawnCard;
	}
	
	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}
	
	public int goToNextPlayer() {
		return goToNextPlayer(0);
	}

	/**
	 * 
	 * @param skip
	 * @requires skip >= 0
	 */
	public int goToNextPlayer(final int skip) {
		if (skip < 0)
			throw new IllegalArgumentException("skip must be >= 0");
		
		return currentPlayerIndex = (currentPlayerIndex + 1 + skip) % players.size();
	}
	
	private void shuffleCards() {
		Collections.shuffle(drawDeck);
	}
	
	public int getDrawDeskSize() {
		return drawDeck.size();
	}
	
	public int getDiscardPileSize() {
		return discardPile.size();
	}
}
