package states;
import actions.*;
import main.*;

public class TradeOrDonateState extends TurnState {

	public TradeOrDonateState(Game context, Player activePlayer) {
		super(context, activePlayer, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean handled(Action action, Object[] args) {
		// TODO Auto-generated method stub
		return false;
	}
}
