package bohnanza.standard.actions;

import bohnanza.core.Action;
import bohnanza.core.Card;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.StandardGame;

public class SetAsideCard extends Action<StandardGame> {

	private final Card card;

	public SetAsideCard(StandardGame game, Player initiator, Card card) {
		super(game, initiator);
		this.card = card;
	}

	@Override
	protected void innerHandle() throws IllegalActionException {
		initiator.setFaceUpCardaside(card);
	}

}
