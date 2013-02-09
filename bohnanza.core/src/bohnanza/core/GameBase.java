package bohnanza.core;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class GameBase {
	/** @uml.property name="currentState" */
	protected TurnState<? extends GameBase> currentState;

	/** @uml.property name="players" */
	protected ArrayList<Player> players = new ArrayList<Player>();

	protected int activePlayerIndex = 0;

	/** @uml.property name="drawDeck" */
	protected ArrayList<Card> drawDeck = new ArrayList<Card>();

	/** @uml.property name="discardPile" */
	protected ArrayList<Card> discardPile = new ArrayList<Card>();

	protected boolean started = false;

	protected final AbstractFactory factory;

	public final GameRules RULES;

	protected GameBase(AbstractFactory facotry, GameRules rules) {
		this.factory = facotry;
		this.RULES = rules;
		drawDeck = factory.getGameDeck();
		shuffleCards();
	}

	public abstract void start();

	public boolean isStarted() {
		return started;
	}

	/**Adds player player to game. Should only be called BEFHORE this.start() */
	public Player addPlayer(Player player) throws IllegalActionException {
		for(Player p : players) {
			if(p.getName().equals(player.getClass()))
				throw new IllegalActionException("Username already taken.");
		}
		players.add(player);
		return player;
	}

	/** Randomly reorders the drawdeck */
	protected void shuffleCards() {
		Collections.shuffle(drawDeck);
	}

	/** Forwards to concrete state. Check whether action is a allowed and execute it */
	public void handle(Action<? extends GameBase> action) throws IllegalActionException {
		currentState.handle(action);
	}

	/** Forwards to concrete state. Returns list of allowed actions in current state */
	public Collection<Class<? extends Action<? extends GameBase>>> getActions(Player player) {
		return currentState.getActions(player);
	}

	/** Getter of the property <tt>currentState</tt>
	 * @uml.property name="currentState" */
	/* public TurnState getCurrentState() { return currentState; } */

	/**Sets the current game state to currentState 
	 * @param currentState the currentState to set */
	public void setCurrentState(TurnState<? extends GameBase> currentState) {
		this.currentState = currentState;
	}

	/** Getter of the property <tt>players</tt>
	 * @uml.property name="players" */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/** Puts card on top of discard pile
	 * @require !discardPile.contains(card) && This card instance is removed from the other collection it was part of */
	public boolean addCardToDiscardPile(Card card) {
		return this.discardPile.add(card);
	}

	/** Draw the top card from the draw deck
	 * @require !drawDeck.isEmpty() */
	public Card drawCard() {
		Card drawnCard = drawDeck.remove(0);
		if(drawDeck.isEmpty()) {
			drawDeck.addAll(discardPile);
			discardPile.clear();
			shuffleCards();
		}
		return drawnCard;
	}

	/** Returns the Player whose turn it is.	 */
	public Player getActivePlayer() {
		return players.get(activePlayerIndex);
	}

	/** Advances the turn to the next player */
	public int goToNextPlayer() {
		return goToNextPlayer(0);
	}

	/**Advances the turn to the next player, skipping the turn of skip number of players
	 * @requires skip >= 0
	 * @return the number of the new active player */
	public int goToNextPlayer(final int skip) {
		if(skip < 0)
			throw new IllegalArgumentException("skip must be >= 0");
		activePlayerIndex = (activePlayerIndex + 1 + skip) % players.size();
		System.out.println("ActivePlayer: " + activePlayerIndex);
		return activePlayerIndex;
	}

	/** Getter for the draw deck. READ ONLY! */
	public List<Card> getDrawDeck() {
		return Collections.unmodifiableList(drawDeck);
	}

	/** Getter for the discard pile. READ ONLY! */
	public List<Card> getDiscardPile() {
		return Collections.unmodifiableList(discardPile);
	}
}
