package bohnanza.standard.core.states;
import bohnanza.standard.core.*;
import bohnanza.standard.core.actions.*;

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
