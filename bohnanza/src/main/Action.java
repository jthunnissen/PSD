package main;

public abstract class Action {

	protected final Game game;
	protected final Player player;
	
	/**
	 * @require game != null
	 * @require player != null
	 * @require game.getPlayers().contains(player)
	 */
	public Action(Game game, Player player) {
		assert game != null : "@require game != null";
		assert player != null : "@require player != null";
		assert game.getPlayers().contains(player) : "@require game.getPlayers().contains(player)";
		
		this.game = game;
		this.player = player;
	}
	
	public abstract boolean handle(Object[] args);

}
