package bohnanza.core.shared.states;
import bohnanza.core.Action;
import bohnanza.core.BaseGame;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.NextPlayer;

public class StartState extends TurnState<BaseGame> {

	public StartState(BaseGame context) {
		super(context);
		addAction(context.getActivePlayer(), NextPlayer.class);
	}

	@Override
	protected boolean handled(Action<? extends BaseGame> action) {
		return action instanceof NextPlayer;
	}
}
