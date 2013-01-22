package bohnanza.standard.states;

import bohnanza.core.Action;
import bohnanza.core.TurnState;
import bohnanza.standard.actions.DrawCards;
import bohnanza.standard.model.Game;

public class DrawState extends TurnState {

	public DrawState(Game context) {
		super(context);
	}

	@Override
	protected boolean handled(Action action) {
		return true;
	}

	@Override
	protected void reset() {
		removeAllActions();
		addAction(DrawCards.class);
	}
}
