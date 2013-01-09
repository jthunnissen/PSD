package bohnanza.standard.core.states;
import bohnanza.standard.core.*;
import bohnanza.standard.core.actions.Action;
import bohnanza.standard.core.actions.Harvest;
import bohnanza.standard.core.actions.NextPhase;
import bohnanza.standard.core.actions.PlantBean;

public class PlantState extends TurnState {

	private boolean isFirstPlant = true;
	
	public PlantState(Game context) {	
		super(context);
		addAction(Harvest.class);
		addAction(PlantBean.class);
	}

	@Override
	protected boolean handled(Action action) {
		if (isFirstPlant && action instanceof PlantBean) {
			isFirstPlant = false;
			addAction(NextPhase.class);
		}
		return true;
	}
}
