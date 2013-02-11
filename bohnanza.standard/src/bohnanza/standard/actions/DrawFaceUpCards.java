package bohnanza.standard.actions;
import java.util.ArrayList;
import java.util.List;

import bohnanza.core.Action;
import bohnanza.core.Card;
import bohnanza.core.Player;
import bohnanza.standard.model.StandardGame;

public class DrawFaceUpCards extends Action<StandardGame> {

	public DrawFaceUpCards(StandardGame game, Player initiator) {
		super(game, initiator);
	}

	/** Draw 2 cards from the draw deck and put them face up */
	@Override
	protected void innerHandle() {
		List<Card> faceupCards = new ArrayList<Card>();
		faceupCards.add(game.drawCard());
		faceupCards.add(game.drawCard());
		initiator.setFaceUpCards(faceupCards);
	}

}
