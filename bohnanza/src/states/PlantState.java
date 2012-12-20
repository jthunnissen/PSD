package states;
import actions.Action;
import actions.Harvest;
import actions.NextPhase;
import actions.PlantBean;
import main.*;

public class PlantState extends TurnState {

	private boolean isFirstPlant = true;
	
	/**
	 * @param context
	 * @param name
	 */
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
