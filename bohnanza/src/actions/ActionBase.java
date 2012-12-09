package actions;
import main.*;
import main.IllegalActionException;

public abstract class ActionBase {

	protected final Game game;
	
	/**
	 * @require game != null
	 */
	public ActionBase(Game game) {
		assert game != null : "@require game != null";
		this.game = game;
	}
	
	public abstract void handle(Player player, Object[] args) throws IllegalActionException;

}
