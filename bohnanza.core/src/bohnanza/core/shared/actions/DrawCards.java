package bohnanza.core.shared.actions;

import bohnanza.core.Action;
import bohnanza.core.BaseGame;
import bohnanza.core.Card;
import bohnanza.core.Player;

public class DrawCards extends Action<BaseGame> {

	public DrawCards(BaseGame game, Player initiator) {
		super(game, initiator);
	}

	/** Draws game.RULES.NrOfCardsDrawn cards from the draw deck and puts them in order at the back of the players hand */
	@Override
	protected void innerHandle()  {
		for(int i = 0; i < game.RULES.NrOfCardsDrawn; i++) {
			Card card = game.drawCard();
			initiator.addCardToHand(card);
		}
	}

}
