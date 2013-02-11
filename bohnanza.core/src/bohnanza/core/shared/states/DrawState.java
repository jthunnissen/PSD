package bohnanza.core.shared.states;

import bohnanza.core.Action;
import bohnanza.core.BaseGame;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.DrawCards;

public class DrawState extends TurnState<BaseGame> {

	public DrawState(BaseGame context) {
		super(context);
		addAction(DrawCards.class);
	}

	@Override
	protected boolean handled(Action<? extends BaseGame> action) {
		return true;
	}
}
