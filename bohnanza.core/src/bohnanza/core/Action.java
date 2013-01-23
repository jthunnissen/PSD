package bohnanza.core;

public abstract class Action<Game extends GameBase> {

	protected final Game game;
	protected final Player initiator;

	public Action(Game game, Player initiator) {
		this.game = game;
		this.initiator = initiator;
	}

	public void handle() throws IllegalActionException {
		if(!game.getPlayers().contains(initiator))
			throw new IllegalActionException("This player is not part of this game.");
		innerHandle();
	}

	public Player getInitiator() {
		return initiator;
	}

	protected abstract void innerHandle() throws IllegalActionException;
}