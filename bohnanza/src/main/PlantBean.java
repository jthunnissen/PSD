package main;


public class PlantBean extends Action {

	/**
	 * @param game
	 * @param player
	 */
	public PlantBean(Game game, Player player) {
		super(game, player);
	}
	
	/* (non-Javadoc)
	 * @see main.Action#handle(main.Player, java.lang.Object[])
	 */
	@Override
	public boolean handle(Object[] args) {
		int fieldnr = (int) args[0];
		return player.plantBean(fieldnr);
	}

}
