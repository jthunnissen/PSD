package bohnanza.standard.actions;

import bohnanza.core.Action;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.Game;

public class NextPlayer extends Action {

	public NextPlayer(Game game, Player initiator) {
		super(game, initiator);
	}

	@Override
	public void handle() throws IllegalActionException {
		game.goToNextPlayer();
	}

}
