package bohnanza.core.shared.actions;

import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class NextPhase extends Action<GameBase> {

	public NextPhase(GameBase game, Player initiator) {
		super(game, initiator);
	}

	/** Doesn't do anything, just triggers state advance */
	@Override
	protected void innerHandle() throws IllegalActionException {
	}

}
