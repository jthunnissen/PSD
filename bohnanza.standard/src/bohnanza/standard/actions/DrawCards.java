package bohnanza.standard.actions;

import bohnanza.core.Action;
import bohnanza.core.Card;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.Game;

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
		//game.goToNextPlayer();
	}

}
