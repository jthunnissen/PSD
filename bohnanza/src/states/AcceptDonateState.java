package states;
import actions.*;
import main.*;

/**
 * @author Damiaan
 *
 */
public class AcceptDonateState extends TurnState {

	/**
	 * @param context
	 */
	public AcceptDonateState(Game context, Player activePlayer) {
		super(context, activePlayer);
		// TODO Auto-generated constructor stub
	}

	public void buildStateMapping() {
		TurnState nextState;
		if (getCurrentPlayer().getFaceUpCards().size() > 0) {
			nextState = new TradeOrDonateState(getContext(),activePlayer);
		} else {
			nextState = new CurrentPlayerPlantState(getContext(),activePlayer);
		}
		addActionState(new AcceptDonation(getContext(), getCurrentPlayer()), nextState);
		addActionState(new DeclineDonation(getContext(), getCurrentPlayer()), nextState);
	}

}
