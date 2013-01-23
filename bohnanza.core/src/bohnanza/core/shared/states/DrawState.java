package bohnanza.core.shared.states;

import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.DrawCards;

public class DrawState extends TurnState<GameBase> {

	public DrawState(GameBase context) {
		super(context);
		addAction(DrawCards.class);
	}

	@Override
	protected boolean handled(Action<? extends GameBase> action) {
		return true;
	}
}
