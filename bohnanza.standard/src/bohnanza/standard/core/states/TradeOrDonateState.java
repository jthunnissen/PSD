package bohnanza.standard.core.states;
import bohnanza.standard.core.*;
import bohnanza.standard.core.actions.*;

public class TradeOrDonateState extends TurnState {

	private ProposeTradeOrDonation proposition;
	
	public TradeOrDonateState(Game context) {
		super(context);
		addAction(DrawFaceUpCards.class);
	}

	@Override
	protected boolean handled(Action action) {
		if(action instanceof DrawFaceUpCards) {
			removeAction(DrawFaceUpCards.class);
			startTrade();			
		} else if(action instanceof NextPhase) {
			return true;
		} else if(action instanceof ProposeTradeOrDonation){
			proposition = (ProposeTradeOrDonation)action;
			removeAction(SetAsideCard.class);
			for(Player player: context.getPlayers()) {
				removeAction(player, ProposeTradeOrDonation.class);
			}
			addAction(proposition.getOtherPlayer(), AcceptTrade.class);
			addAction(proposition.getOtherPlayer(), DeclineTrade.class);
		} else if(action instanceof AcceptTrade || action instanceof DeclineTrade) {
			startTrade();
			if(context.getActivePlayer().getFaceUpCards().isEmpty()) {
				addAction(NextPhase.class);
			}
		}
		return false;
	}
	
	private void startTrade() {
		addAction(SetAsideCard.class);
		for(Player player: context.getPlayers()) {
			addAction(player, ProposeTradeOrDonation.class);
		}
	}
}
