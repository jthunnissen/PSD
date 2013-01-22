package bohnanza.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GameBase {
	/**
	 * @uml.property name="currentState"
	 * @uml.associationEnd inverse="context:main.TurnState"
	 */
	private TurnState currentState;

	/**
	 * @uml.property name="players"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Player"
	 */
	private ArrayList<Player> players = new ArrayList<Player>();
	
	private int activePlayerIndex = 0;
	
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

	protected boolean started = false;
	
	protected final AbstractFactory factory;

	public boolean isStarted(){
		return started;
	}
	
	public abstract void start();

	/**
		 */
	protected GameBase(AbstractFactory facotry) {
		this.factory = facotry;
		drawDeck = factory.getGameDeck();
		shuffleCards();
	}

	
	public Player addPlayer(Player player) throws IllegalActionException{
		for(Player p: players){
			if(p.getName().equals(player.getClass()))
				throw new IllegalActionException("Username already taken."); 
		}
		players.add(player);
		return player;
	}
	
	private void shuffleCards() {
		Collections.shuffle(drawDeck);
	}

	public void handle(Action action) throws IllegalActionException {
		currentState.handle(action);
	}
	
	public List<Class<? extends Action>> getActions(Player player) {
		return currentState.getActions(player);
	}
	
	/**
	 * Getter of the property <tt>currentState</tt>
	 * 
	 * @return Returns the currentState.
	 * @uml.property name="currentState"
	 */
	/*public TurnState getCurrentState() {
		return currentState;
	}*/

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
	
	public boolean addCardToDiscardPile(Card card){
		return this.discardPile.add(card);
	}

	/**
	 * Draw the top card from the drawdeck
	 * @require !drawDeck.isEmpty()
	 * @return the card that was drawn
	 */
	public Card drawCard() {
		//TODO: reshuffle deck if empty
		Card drawnCard = drawDeck.remove(0);
		if(drawDeck.isEmpty()) {
			drawDeck.addAll(discardPile);
			discardPile.clear();
			shuffleCards();
		}
		return drawnCard;
	}

	public Player getActivePlayer() {
		return players.get(activePlayerIndex);
	}
	
	public int goToNextPlayer() {
		return goToNextPlayer(0);
	}

	/**
	 * @param skip
	 * @requires skip >= 0
	 */
	public int goToNextPlayer(final int skip) {
		if (skip < 0)
			throw new IllegalArgumentException("skip must be >= 0");
		activePlayerIndex = (activePlayerIndex + 1 + skip) % players.size();
		System.out.println("ActivePlayer: "+activePlayerIndex);
		return activePlayerIndex;
	}
	
	public Player getNextPlayer() {
		return players.get(activePlayerIndex+1);
	}
}