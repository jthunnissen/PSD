package bohnanza.core.shared.actions;

import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.IllegalActionException;

public class NextPhase extends Action<GameBase> {

	public NextPhase(GameBase game) {
		super(game, game.getActivePlayer());
	}

	/** Doesn't do anything, just triggers state advance */
	@Override
	protected void innerHandle() throws IllegalActionException {
	}

}
