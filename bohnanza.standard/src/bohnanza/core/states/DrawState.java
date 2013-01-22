package bohnanza.core.states;
import bohnanza.core.*;
import bohnanza.core.actions.*;

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
