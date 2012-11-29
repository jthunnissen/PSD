package actions;

import main.Game;
import main.Player;


public class BuyThirdField extends ActionBase {

	public BuyThirdField(Game game, Player player) {
		super(game, player);
	}
	
	
	@Override
	/**
	 * Plants first card in a Player's hand in a specified field
	 * @param args[0] - Number of the field where card should be planted
	 */
	public boolean handle(Object[] args) {
		return player.buyThirdField();
	}

}
