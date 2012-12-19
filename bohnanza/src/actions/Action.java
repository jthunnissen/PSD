package actions;
import main.*;
import main.IllegalActionException;

public abstract class Action {

	protected final Game game;
	protected final Player player;
	
	public Action(Game game, Player player) {
		this.game = game;
		this.player = player;
	}
	
	public Action(Game game) {
		this(game, game.getCurrentPlayer());
	}
	
	public abstract void handle(Object[] args) throws IllegalActionException;

}