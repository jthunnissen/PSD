package states;
import actions.*;
import main.*;

public class TradeOrDonateState extends TurnState {

	public TradeOrDonateState(Game context) {
		super(context);
		addAction(DrawFaceUpCards.class);
	}

	@Override
	protected boolean handled(Action action) {
		if(action instanceof DrawFaceUpCards) {
			removeAction(DrawFaceUpCards.class);
			addAction(SetAsideCard.class);
			addAction(InitTradeOrDonation.class);
		} else if(action instanceof NextPhase) {
			return true;
		} else {
			//TODO: add trade logic
			
			if(context.getActivePlayer().getFaceUpCards().isEmpty()) {
				addAction(NextPhase.class);
			}
		}
		return false;
	}
}
