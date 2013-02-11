package bohnanza.core.shared.states;

import bohnanza.core.Action;
import bohnanza.core.BaseGame;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.Harvest;
import bohnanza.core.shared.actions.NextPhase;
import bohnanza.core.shared.actions.PlantBean;

public class PlantState extends TurnState<BaseGame> {

	private int beansPlanted = 0;

	public PlantState(BaseGame context) {
		super(context);
		if(context.getActivePlayer().getHand().isEmpty()) {
			addAction(NextPhase.class);
		} else {
			addDefaultActions();
			addAction(PlantBean.class);
		}
	}

	@Override
	protected boolean handled(Action<? extends BaseGame> action) {
		if(action instanceof NextPhase)
			return true;
		if(action instanceof PlantBean) {
			beansPlanted++;
			if(beansPlanted == 1)
				addAction(NextPhase.class);
			if(beansPlanted == 2) {
				removeAction(PlantBean.class);
				removeAction(Harvest.class);
			}
		}
		return false;
	}
}
