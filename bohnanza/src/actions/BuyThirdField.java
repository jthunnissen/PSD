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
<<<<<<< HEAD
	public void handle(Object[] args) throws IllegalActionException {
		player.buyThirdField();
=======
	public boolean handle(Object[] args) {
		boolean result = true;
		try {
			result = player.buyThirdField();
		} catch (IllegalActionException e) {
			result = false;
		}
		return result;
>>>>>>> branch 'master' of ssh://git@github.com/q41/PSD.git
	}

}
