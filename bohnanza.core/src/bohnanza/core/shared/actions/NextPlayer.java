package bohnanza.core.shared.actions;

import bohnanza.core.Action;
import bohnanza.core.BaseGame;
import bohnanza.core.IllegalActionException;

public class NextPlayer extends Action<BaseGame> {

	public NextPlayer(BaseGame game) {
		super(game, game.getActivePlayer());
	}

	/** Advances the turn to the next player */
	@Override
	protected void innerHandle() throws IllegalActionException {
		game.goToNextPlayer();
	}

}
