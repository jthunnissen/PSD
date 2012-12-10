package actions;
import main.*;

public class BuyBeanField extends ActionBase {


	public BuyBeanField(Game game) {
		super(game);
	}
	
	@Override
	/**
	 * Plants first card in a Player's hand in a specified field
	 */
	public void handle(Object[] args) throws IllegalActionException {
		player.buyField();
	}
}
