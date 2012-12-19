package actions;
import main.*;

public class DrawCards extends Action {

	public DrawCards(Game game) {
		super(game);
	}
	
	
	@Override
	public void handle(Object[] args) throws IllegalActionException {
		for (int i = 0; i < 3; i++) {
			Card card = game.drawCard();
			player.addCardToHand(card);
		}
		game.goToNextPlayer();
	}

}
