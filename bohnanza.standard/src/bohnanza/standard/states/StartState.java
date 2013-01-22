package bohnanza.standard.states;
import bohnanza.core.Action;
import bohnanza.core.TurnState;
import bohnanza.standard.actions.NextPlayer;
import bohnanza.standard.model.Game;

public class StartState extends TurnState {
	
	public StartState(Game context) {
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
