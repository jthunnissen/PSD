package bohnanza.core;

public abstract class Action {

	protected final GameBase game;
	protected final Player initiator;
	
	public Action(GameBase game, Player initiator) {
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