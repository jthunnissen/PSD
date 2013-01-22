package bohnanza.core;

public abstract class Action {

	protected final GameBase game;
	protected final Player initiator;
	
	public Action(GameBase game, Player initiator) {
		this.game = game;
		this.initiator = initiator;
	}
	
	public abstract void handle() throws IllegalActionException;

	public Player getInitiator() {
		return initiator;
	}

}