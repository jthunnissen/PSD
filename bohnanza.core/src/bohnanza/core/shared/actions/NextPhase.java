package bohnanza.core.shared.actions;

import bohnanza.core.Action;
import bohnanza.core.BaseGame;
import bohnanza.core.IllegalActionException;

public class NextPhase extends Action<BaseGame> {

	public NextPhase(BaseGame game) {
		super(game, game.getActivePlayer());
	}

	/** Doesn't do anything, just triggers state advance */
	@Override
	protected void innerHandle() throws IllegalActionException {
	}

}
