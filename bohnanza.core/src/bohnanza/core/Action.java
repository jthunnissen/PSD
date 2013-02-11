package bohnanza.core;

/**
 * This class respresent an Action in the Bohnanza game.
 * Actions are executed by players
 *
 * @param <Game> The Bohnanza game
 */
public abstract class Action<Game extends BaseGame> {

	/**
	 * The game
	 */
	protected final Game game;
	/**
	 * Players that executes the actions
	 */
	protected final Player initiator;

	public Action(Game game, Player initiator) {
		this.game = game;
		this.initiator = initiator;
	}

	/** 
	 * Check whether this action is a allowed and execute it 
	 * */
	public final void handle() throws IllegalActionException {
		if(!game.getPlayers().contains(initiator))
			throw new IllegalActionException("This player is not part of this game.");
		innerHandle();
	}

	/**
	 * Getter for the initiator
	 * @return Player that initiated this action
	 */
	public Player getInitiator() {
		return initiator;
	}

	/**
	 * Executes the action
	 * @throws IllegalActionException
	 */
	protected abstract void innerHandle() throws IllegalActionException;
}