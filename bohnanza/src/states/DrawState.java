package states;
import actions.*;
import main.*;

public class DrawState extends TurnState {

	/**
	 * @param context
	 */
	public DrawState(Game context, Player activePlayer) {
		super(context,activePlayer);
	}

	public void buildStateMapping() {
		addActionState(new DrawCards(getContext()), new SetASideState(getContext(),activePlayer));
	}

}
