package states;
import actions.*;
import main.*;

public class AcceptDonateState extends TurnState {

	public AcceptDonateState(Game context, Player activePlayer) {
		super(context, activePlayer, null);
	}

	@Override
	protected boolean handled(Action action, Object[] args) {
		//TODO: implement
		
//		TurnState nextState;
//		if (getCurrentPlayer().getFaceUpCards().size() > 0) {
//			nextState = new TradeOrDonateState(getContext(),activePlayer);
//		} else {
//			nextState = new CurrentPlayerPlantState(getContext(),activePlayer);
//		}
//		addActionState(new AcceptDonation(getContext(), getCurrentPlayer()), nextState);
//		addActionState(new DeclineDonation(getContext(), getCurrentPlayer()), nextState);
		
		return false;
	}

}
