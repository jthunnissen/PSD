package states;
import actions.*;
import main.*;

public class SetASideState extends TurnState {

	public SetASideState(Game context, Player activePlayer) {
		super(context, activePlayer);
	}

	public void buildStateMapping() {
		if (getCurrentPlayer().getFaceUpCards().size() > 0) {
			addActionState(new SetAsideCard(getContext()), this);
		}
		addActionState(new Trade(getContext()), new AcceptTradeState(getContext(),activePlayer));
		addActionState(new Donate(getContext()), new AcceptDonateState(getContext(),activePlayer));
		addActionState(new EndPhase(getContext()), new CurrentPlayerPlantState(getContext(),activePlayer));
	}

}
