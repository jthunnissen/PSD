package actions;

import main.Game;
import main.Player;

public abstract class ActionBase {

	protected final Game game;
	protected final Player player;
	
	/**
	 * @require game != null
	 * @require player != null
	 * @require game.getPlayers().contains(player)
	 */
	public ActionBase(Game game, Player player) {
		assert game != null : "@require game != null";
		assert player != null : "@require player != null";
		assert game.getPlayers().contains(player) : "@require game.getPlayers().contains(player)";
		
		this.game = game;
		this.player = player;
	}
	
	public abstract boolean handle(Object[] args);

}
