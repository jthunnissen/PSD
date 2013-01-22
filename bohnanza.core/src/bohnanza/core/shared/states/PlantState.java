package bohnanza.core.shared.states;

import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.NextPhase;
import bohnanza.core.shared.actions.PlantBean;

public class PlantState extends TurnState {

	private int beansPlanted;
	
	public PlantState(GameBase context) {	
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