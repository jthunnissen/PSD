package states;
import actions.*;
import main.*;

public class AcceptTradeState extends TurnState {

	public AcceptTradeState(Game context, Player activePlayer) {
		super(context, activePlayer, null);
	}

	@Override
	protected boolean handled(Action action, Object[] args) {
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
