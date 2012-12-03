package actions;
import main.*;

public class BuyThirdField extends ActionBase {


	public BuyThirdField(Game game, Player player) {
		super(game, player);
	}
	
	@Override
	/**
	 * Plants first card in a Player's hand in a specified field
	 */
	public void handle(Object[] args) throws IllegalActionException {
		player.buyThirdField();
	}

}
