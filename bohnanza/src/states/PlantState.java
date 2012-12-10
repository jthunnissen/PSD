<<<<<<< HEAD
/**
 * 
 */
package states;

import actions.Harvest;
import actions.PlantBean;
import main.Game;

/**
 * @author Damiaan
 *
 */
public class PlantState extends TurnState {

	private boolean isFirstPlant = true;
	
	/**
	 * @param context
	 * @param name
	 */
	public PlantState(Game context) {	
		super(context);
	}
	
	/* (non-Javadoc)
	 * @see states.TurnState#buildStateMapping()
	 */
	@Override
	public void buildStateMapping() {
		if (isFirstPlant) {
			isFirstPlant = false;
			addActionState(new PlantBean(getContext(), getContext().getCurrentPlayer()), this);
		} else {
			addActionState(new PlantBean(getContext(), getContext().getCurrentPlayer()), new DrawState(getContext()));
		}
		if (getContext().getCurrentPlayer().getFields().size() > 0) {
			addActionState(new Harvest(getContext(), getContext().getCurrentPlayer()), this);
		}
	}
	
	
}
=======
/**
 * 
 */
package states;

import actions.Harvest;
import actions.PlantBean;
import main.Game;

/**
 * @author Damiaan
 *
 */
public class PlantState extends TurnState {

	private boolean isFirstPlant = true;
	
	/**
	 * @param context
	 * @param name
	 */
	public PlantState(Game context) {	
		super(context);
	}
	
	/* (non-Javadoc)
	 * @see states.TurnState#buildStateMapping()
	 */
	@Override
	public void buildStateMapping() {
		if (isFirstPlant) {
			isFirstPlant = false;
			addActionState(new PlantBean(getContext(), getContext().getCurrentPlayer()), this);
		} else {
			addActionState(new PlantBean(getContext(), getContext().getCurrentPlayer()), new DrawState(getContext()));
		}
		if (getContext().getCurrentPlayer().getFields().size() > 0) {
			addActionState(new Harvest(getContext(), getContext().getCurrentPlayer()), this);
		}
	}
	
	
}
>>>>>>> master
