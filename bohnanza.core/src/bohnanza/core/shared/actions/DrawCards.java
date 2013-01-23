package bohnanza.core.shared.actions;

import bohnanza.core.Action;
import bohnanza.core.Card;
import bohnanza.core.GameBase;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class DrawCards extends Action<GameBase> {

	public DrawCards(GameBase game, Player initiator) {
		super(game, initiator);
	}

	@Override
	protected void innerHandle() throws IllegalActionException {
		for(int i = 0; i < 3; i++) {
			Card card = game.drawCard();
			initiator.addCardToHand(card);
		}
	}

}
