package bohnanza.standard.core.states;
import bohnanza.standard.core.*;
import bohnanza.standard.core.actions.*;

public class AcceptTradeState extends TurnState {

	public AcceptTradeState(Game context) {
		super(context);
	}

	@Override
	protected boolean handled(Action action) {
		// TODO Auto-generated method stub
		
//		TurnState nextState;
//		if (getCurrentPlayer().getFaceUpCards().size() > 0) {
//			nextState = new TradeOrDonateState(getContext(),activePlayer);
//		} else {
//			nextState = new CurrentPlayerPlantState(getContext(),activePlayer);
//		}
//		addActionState(new AcceptTrade(getContext(), getCurrentPlayer()), nextState);
//		addActionState(new DeclineTrade(getContext(), getCurrentPlayer()), nextState);
		
		return false;
	}

}
