package actions;
import main.*;


public class PlantBean extends ActionBase {

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
	public void handle(Object[] args) throws IllegalActionException {
		int fieldnr = (int) args[0];
		player.plantBean(fieldnr);
	}

}
