package bohnanza.core.states;
import bohnanza.core.*;
import bohnanza.core.actions.Action;
import bohnanza.core.actions.Harvest;
import bohnanza.core.actions.NextPhase;
import bohnanza.core.actions.PlantBean;

public class PlantState extends TurnState {

	private int beansPlanted;
	
	public PlantState(Game context) {	
		super(context);
	}

	@Override
	protected boolean handled(Action action) {
		if(action instanceof NextPhase) return true;
		if(action instanceof PlantBean) {
			beansPlanted++;
			if(beansPlanted==1) addAction(NextPhase.class);
			if(beansPlanted==2) {
				removeAction(PlantBean.class);
				removeAction(Harvest.class);
			}
		}
		return false;
	}

	@Override
	protected void reset() {
		removeAllActions();
		beansPlanted = 0;
		if(context.getActivePlayer().getHand().isEmpty()) {
			addAction(NextPhase.class);
		} else {
			addAction(Harvest.class);
			addAction(PlantBean.class);
		}
	}
}
