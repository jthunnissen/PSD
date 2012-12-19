package states;
import actions.*;
import main.*;

public class DrawState extends TurnState {

	public DrawState(Game context, Player activePlayer) {
		super(context, activePlayer, null);
	}

	@Override
	protected boolean handled(Action action, Object[] args) {
		// TODO Auto-generated method stub
		
		//addActionState(new DrawCards(getContext()), new SetASideState(getContext(),activePlayer));
		
		return false;
	}

}
