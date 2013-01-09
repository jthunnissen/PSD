package bohnanza.standard.core.states;
import bohnanza.standard.core.*;
import bohnanza.standard.core.actions.*;

public class DrawState extends TurnState {

	public DrawState(Game context) {
		super(context);
	}

	@Override
	protected boolean handled(Action action) {
		// TODO Auto-generated method stub
		
		//addActionState(new DrawCards(getContext()), new SetASideState(getContext(),activePlayer));
		
		return false;
	}

}
