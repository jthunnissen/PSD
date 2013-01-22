package bohnanza.standard.actions;

import bohnanza.core.Action;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.Game;

public class NextPhase extends Action {

	public NextPhase(Game game, Player initiator) {
		super(game, initiator);
	}

	/**Doesn't do anything, just triggers state advance*/
	@Override
	protected void innerHandle() throws IllegalActionException {}

}
