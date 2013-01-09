package bohnanza.standard.core.states;
import bohnanza.standard.core.*;
import bohnanza.standard.core.actions.Action;
import bohnanza.standard.core.actions.Harvest;
import bohnanza.standard.core.actions.NextPhase;
import bohnanza.standard.core.actions.PlantBean;

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
