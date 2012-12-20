package actions;
import main.*;

public class NextPhase extends Action {

	public NextPhase(Game game, Player initiator) {
		super(game, initiator);
	}

	/**Doesn't do anything, just triggers state advance*/
	@Override
	public void handle() throws IllegalActionException {}

}
