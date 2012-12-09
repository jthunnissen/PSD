package actions;
import main.*;

public class BuyBeanField extends ActionBase {


	public BuyBeanField(Game game, Player player) {
		super(game);
	}
	
	@Override
	/**
	 * Plants first card in a Player's hand in a specified field
	 */
	public void handle(Player player, Object[] args) throws IllegalActionException {
		player.buyField();
	}

}
