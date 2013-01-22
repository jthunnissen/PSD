package bohnanza.alcabohne.states;
import bohnanza.alcabohne.model.Game;
import bohnanza.core.Action;
import bohnanza.core.TurnState;

public class BeanRevelationState extends TurnState {
	
	public BeanRevelationState(Game context) {
		super(context);
	}

	@Override
	protected void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean handled(Action action) {
		if(((Game)context).getRevealedBeans().size()==3) {
			return true;
		}
		return false;
	}

}
