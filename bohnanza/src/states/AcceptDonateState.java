<<<<<<< HEAD
/**
 * 
 */
package states;

import actions.AcceptDonation;
import actions.DeclineDonation;
import main.Game;

/**
 * @author Damiaan
 *
 */
public class AcceptDonateState extends TurnState {

	/**
	 * @param context
	 */
	public AcceptDonateState(Game context) {
		super(context);
		// TODO Auto-generated constructor stub
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
		addActionState(new AcceptDonation(getContext(), getCurrentPlayer()), nextState);
		addActionState(new DeclineDonation(getContext(), getCurrentPlayer()), nextState);
	}

}
=======
/**
 * 
 */
package states;

import actions.AcceptDonation;
import actions.DeclineDonation;
import main.Game;

/**
 * @author Damiaan
 *
 */
public class AcceptDonateState extends TurnState {

	/**
	 * @param context
	 */
	public AcceptDonateState(Game context) {
		super(context);
		// TODO Auto-generated constructor stub
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
		addActionState(new AcceptDonation(getContext(), getCurrentPlayer()), nextState);
		addActionState(new DeclineDonation(getContext(), getCurrentPlayer()), nextState);
	}

}
>>>>>>> master
