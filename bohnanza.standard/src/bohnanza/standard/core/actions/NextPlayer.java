package bohnanza.standard.core.actions;

import bohnanza.standard.core.Game;
import bohnanza.standard.core.IllegalActionException;
import bohnanza.standard.core.Player;

public class NextPlayer extends Action {

	public NextPlayer(Game game, Player initiator) {
		super(game, initiator);
	}

	@Override
	public void handle() throws IllegalActionException {
		game.goToNextPlayer();
	}

}
