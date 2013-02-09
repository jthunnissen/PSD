package bohnanza.core.shared.actions;

import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class NextPlayer extends Action<GameBase> {

	public NextPlayer(GameBase game, Player initiator) {
		super(game, initiator);
	}

	/** Advances the turn to the next player */
	@Override
	protected void innerHandle() throws IllegalActionException {
		game.goToNextPlayer();
	}

}
