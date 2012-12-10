/**
 * 
 */
package states;

import actions.DrawCards;
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
		addActionState(new DrawCards(getContext()), new SetASideState(getContext()));
	}

}
