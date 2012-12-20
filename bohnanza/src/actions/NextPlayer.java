package actions;

import main.Game;
import main.IllegalActionException;
import main.Player;

public class NextPlayer extends Action {

	public NextPlayer(Game game, Player initiator) {
		super(game, initiator);
	}

	@Override
	public void handle() throws IllegalActionException {
		game.goToNextPlayer();
	}

}
