package actions;

import exceptions.IllegalActionException;
import exceptions.NotEnoughMoneyException;

import main.Game;
import main.Player;



public class BuyThirdField extends ActionBase {


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
		} catch (IllegalActionException e) {
			result = false;
		}
		return result;
	}

}
