package bohnanza.core.shared.states;
import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.NextPlayer;

public class StartState extends TurnState<GameBase> {

	public StartState(GameBase context) {
		super(context);
		addAction(context.getActivePlayer(), NextPlayer.class);
	}

	@Override
	protected boolean handled(Action<? extends GameBase> action) {
		return action instanceof NextPlayer;
	}
}
