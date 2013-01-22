package bohnanza.core.shared.states;

import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.NextPlayer;

public class StartState extends TurnState {
	
	public StartState(GameBase context) {
		super(context);
	}

	@Override
	protected boolean handled(Action action) {
		return action instanceof NextPlayer;
	}

	@Override
	protected void reset() {
		removeAllActions();
		addAction(context.getActivePlayer(), NextPlayer.class);
	}

}
