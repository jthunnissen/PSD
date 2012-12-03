package actions;
import main.*;

public class Draw2Cards extends ActionBase {

	public Draw2Cards(Game game, Player player) {
		super(game, player);
	}
	
	
	@Override
	/**
	 * Plants first card in a Player's hand in a specified field
	 * @param args[0] - Number of the field where card should be planted
	 */
	public void handle(Object[] args) throws IllegalActionException {
		int fieldnr = (int) args[0];
		player.plantBean(fieldnr);
	}

}
