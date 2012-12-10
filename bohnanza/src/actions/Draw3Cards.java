package actions;
import main.*;

public class Draw3Cards extends ActionBase {

	public Draw3Cards(Game game, Player player) {
		super(game, player);
	}
	
	
	@Override
	/**
	 * Plants first card in a Player's hand in a specified field
	 * @param args[0] - Number of the field where card should be planted
	 */
	public void handle(Object[] args) throws IllegalActionException {
		for (int i = 0; i < 3; i++) {
			player.addCardToHand(game.drawCard());
		}
		game.goToNextPlayer();
	}

}

