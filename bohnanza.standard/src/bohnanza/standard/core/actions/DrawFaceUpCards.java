package bohnanza.standard.core.actions;
import java.util.ArrayList;
import java.util.List;

import bohnanza.standard.core.*;

public class DrawFaceUpCards extends Action {

	public DrawFaceUpCards(Game game, Player initiator) {
		super(game, initiator);
	}
	
	@Override
	public void handle() throws IllegalActionException {
		List<Card> asideCards = new ArrayList<Card>();
		asideCards.add(game.drawCard());
		asideCards.add(game.drawCard());
		initiator.setFaceUpCards(asideCards);
	}

}
