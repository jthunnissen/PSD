/**
 * 
 */
package states;

import actions.Donate;
import actions.SetAsideCard;
import actions.SkipAction;
import actions.Trade;
import main.Game;

/**
 * @author Damiaan
 *
 */
public class SetASideState extends TurnState {

	/**
	 * @param context
	 */
	public SetASideState(Game context) {
		super(context);
	}

	@Override
	public void buildStateMapping() {
		if (getCurrentPlayer().getFaceUpCards().size() > 0) {
			addActionState(new SetAsideCard(getContext()), this);
		}
		addActionState(new Trade(getContext()), new AcceptTradeState(getContext()));
		addActionState(new Donate(getContext()), new AcceptDonateState(getContext()));
		addActionState(new SkipAction(getContext()), new CurrentPlayerPlantState(getContext()));
	}

}
