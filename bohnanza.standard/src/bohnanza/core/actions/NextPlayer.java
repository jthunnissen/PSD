package bohnanza.core.actions;

import bohnanza.core.Game;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class NextPlayer extends Action {

	public NextPlayer(Game game, Player initiator) {
		super(game, initiator);
	}

	@Override
	public void handle() throws IllegalActionException {
		game.goToNextPlayer();
	}

}
