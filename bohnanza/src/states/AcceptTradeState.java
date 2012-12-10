<<<<<<< HEAD
/**
 * 
 */
package states;

import actions.AcceptTrade;
import actions.DeclineTrade;
import main.Game;

/**
 * @author Damiaan
 *
 */
public class AcceptTradeState extends TurnState {

	/**
	 * @param context
	 */
	public AcceptTradeState(Game context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see states.TurnState#buildStateMapping()
	 */
	@Override
	public void buildStateMapping() {
		TurnState nextState;
		if (getCurrentPlayer().getFaceUpCards().size() > 0) {
			nextState = new TradeOrDonateState(getContext());
		} else {
			nextState = new CurrentPlayerPlantState(getContext());
		}
		addActionState(new AcceptTrade(getContext(), getCurrentPlayer()), nextState);
		addActionState(new DeclineTrade(getContext(), getCurrentPlayer()), nextState);
	}

}
=======
/**
 * 
 */
package states;

import actions.AcceptTrade;
import actions.DeclineTrade;
import main.Game;

/**
 * @author Damiaan
 *
 */
public class AcceptTradeState extends TurnState {

	/**
	 * @param context
	 */
	public AcceptTradeState(Game context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see states.TurnState#buildStateMapping()
	 */
	@Override
	public void buildStateMapping() {
		TurnState nextState;
		if (getCurrentPlayer().getFaceUpCards().size() > 0) {
			nextState = new TradeOrDonateState(getContext());
		} else {
			nextState = new CurrentPlayerPlantState(getContext());
		}
		addActionState(new AcceptTrade(getContext(), getCurrentPlayer()), nextState);
		addActionState(new DeclineTrade(getContext(), getCurrentPlayer()), nextState);
	}

}
>>>>>>> master
