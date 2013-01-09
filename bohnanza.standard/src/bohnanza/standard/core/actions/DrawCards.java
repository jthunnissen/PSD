package bohnanza.standard.core.actions;
import bohnanza.standard.core.*;

public class DrawCards extends Action {

	public DrawCards(Game game, Player initiator) {
		super(game, initiator);
	}
	
	@Override
	public void handle() throws IllegalActionException {
		for (int i = 0; i < 3; i++) {
			Card card = game.drawCard();
			initiator.addCardToHand(card);
		}
		game.goToNextPlayer();
	}

}
