package actions;
import main.*;

public abstract class Action {

	protected final Game game;
	protected final Player initiator;
	
	public Action(Game game, Player initiator) {
		this.game = game;
		this.initiator = initiator;
	}
	
	public abstract void handle() throws IllegalActionException;

	public Player getInitiator() {
		return initiator;
	}

}