package bohnanza.standard.core.actions;
import bohnanza.standard.core.*;

public class NextPhase extends Action {

	public NextPhase(Game game, Player initiator) {
		super(game, initiator);
	}

	/**Doesn't do anything, just triggers state advance*/
	@Override
	public void handle() throws IllegalActionException {}

}
