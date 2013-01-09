package bohnanza.standard.core.states;
import bohnanza.standard.core.*;
import bohnanza.standard.core.actions.*;

public class DrawState extends TurnState {

	public DrawState(Game context) {
		super(context);
		addAction(DrawCards.class);
	}

	@Override
	protected boolean handled(Action action) {
		return action instanceof DrawCards ? true : false;
	}
	
	public static void jan() {
		
	}

}
