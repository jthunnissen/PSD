<<<<<<< HEAD
/**
 * 
 */
package states;

import actions.Draw2Cards;
import main.Game;

/**
 * @author Damiaan
 *
 */
public class DrawState extends TurnState {

	/**
	 * @param context
	 */
	public DrawState(Game context) {
		super(context);
	}

	@Override
	public void buildStateMapping() {
		addActionState(new Draw2Cards(getContext(), getCurrentPlayer()), new SetASideState(getContext()));
	}

}
=======
/**
 * 
 */
package states;

import actions.Draw2Cards;
import main.Game;

/**
 * @author Damiaan
 *
 */
public class DrawState extends TurnState {

	/**
	 * @param context
	 */
	public DrawState(Game context) {
		super(context);
	}

	@Override
	public void buildStateMapping() {
		addActionState(new Draw2Cards(getContext(), getCurrentPlayer()), new SetASideState(getContext()));
	}

}
>>>>>>> master
