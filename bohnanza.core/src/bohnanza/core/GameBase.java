package bohnanza.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GameBase {
	/**
	 * @uml.property name="currentState"
	 * @uml.associationEnd inverse="context:main.TurnState"
	 */
	protected TurnState currentState;

	/**
	 * @uml.property name="players"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Player"
	 */
	protected ArrayList<Player> players = new ArrayList<Player>();
	
	protected int activePlayerIndex = 0;
	
	/**
	 * @uml.property name="drawDeck"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Card"
	 */
	protected ArrayList<BeanCard> drawDeck = new ArrayList<BeanCard>();

	/**
	 * @uml.property name="discardPile"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Card"
	 */
	protected ArrayList<BeanCard> discardPile = new ArrayList<BeanCard>();

	protected boolean started = false;
	
	protected final AbstractFactory factory;

	/**
		 */
	protected GameBase(AbstractFactory facotry) {
		this.factory = facotry;
		drawDeck = factory.getGameDeck();
		shuffleCards();
	}

	public abstract void start();

	public boolean isStarted(){
		return started;
	}
	
	public Player addPlayer(Player player) throws IllegalActionException{
		for(Player p: players){
			if(p.getName().equals(player.getClass()))
				throw new IllegalActionException("Username already taken."); 
		}
		players.add(player);
		return player;
	}
	
<<<<<<< HEAD
	protected void shuffleCards() {
		Collections.shuffle(drawDeck);
	}

=======
>>>>>>> 1f09a064eaa2d34888f51f3b286359bf459e4732
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
	
	public boolean addCardToDiscardPile(BeanCard card){
		return this.discardPile.add(card);
	}

	/**
	 * Draw the top card from the drawdeck
	 * @require !drawDeck.isEmpty()
	 * @return the card that was drawn
	 */
<<<<<<< HEAD
	public BeanCard drawCard() {
		BeanCard drawnCard = drawDeck.remove(0);
=======
	public Card drawCard() {
		Card drawnCard = drawDeck.remove(0);
>>>>>>> 1f09a064eaa2d34888f51f3b286359bf459e4732
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
	
	public List<Card> getDrawDeck() {
		return Collections.unmodifiableList(drawDeck);
	}
	
	public List<Card> getDiscardPile() {
		return Collections.unmodifiableList(discardPile);
	}

	private void shuffleCards() {
		Collections.shuffle(drawDeck);
	}
}
