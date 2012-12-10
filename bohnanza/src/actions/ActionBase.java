package actions;
import main.*;
import main.IllegalActionException;

public abstract class ActionBase {

	protected final Game game;
	protected final Player player;
	
	public ActionBase(Game game, Player player) {
		this.game = game;
		this.player = player;
	}
	
	public ActionBase(Game game) {
		this(game, game.getCurrentPlayer());
	}
	
	public abstract void handle(Object[] args) throws IllegalActionException;

}