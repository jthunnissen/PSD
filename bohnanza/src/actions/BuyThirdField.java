package actions;

import exceptions.IllegalActionException;
import exceptions.NotEnoughMoneyException;
import main.Action;
import main.Game;
import main.Player;

public class BuyThirdField extends Action {

	public BuyThirdField(Game game, Player player) {
		super(game, player);
	}
	
	@Override
	/**
	 * Plants first card in a Player's hand in a specified field
	 */
	public boolean handle(Object[] args) {
		boolean result = true;
		try {
			result = player.buyThirdField();
		} catch (NotEnoughMoneyException e) {
			result = false;
		} catch (IllegalActionException e) {
			result = false;
		}
		return result;
	}

}
