package bohnanza.standard.core.states;
import bohnanza.standard.core.*;
import bohnanza.standard.core.actions.*;

public class AcceptDonateState extends TurnState {

	public AcceptDonateState(Game context) {
		super(context);
	}

	@Override
	protected boolean handled(Action action) {
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
