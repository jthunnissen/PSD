package bohnanza.standard.states;

import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.Player;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.NextPhase;
import bohnanza.standard.actions.AcceptTrade;
import bohnanza.standard.actions.DeclineTrade;
import bohnanza.standard.actions.DrawFaceUpCards;
import bohnanza.standard.actions.ProposeTrade;
import bohnanza.standard.actions.SetAsideCard;
import bohnanza.standard.model.StandardGame;

public class TradeState extends TurnState<StandardGame> {

	private ProposeTrade proposition;

	public TradeState(StandardGame context) {
		super(context);
		addAction(DrawFaceUpCards.class);
	}

	@Override
	protected boolean handled(Action<? extends GameBase> action) {
		if(action instanceof DrawFaceUpCards) {
			removeAction(DrawFaceUpCards.class);
			addDefaultActions();
			startTrade();
		} else if(action instanceof NextPhase) {
			return true;
		} else if(action instanceof ProposeTrade) {
			proposition = (ProposeTrade) action;
			removeAction(SetAsideCard.class);
			for(Player player : context.getPlayers()) {
				removeAction(player, ProposeTrade.class);
			}
			addAction(proposition.getActivePlayer(), AcceptTrade.class);
			addAction(proposition.getActivePlayer(), DeclineTrade.class);
		} else if(action instanceof AcceptTrade || action instanceof DeclineTrade) {
			startTrade();

		}
		if(context.getActivePlayer().getFaceUpCards().isEmpty()) {
			addAction(NextPhase.class);
		}
		return false;
	}

	private void startTrade() {
		addAction(SetAsideCard.class);
		for(Player player : context.getPlayers()) {
			if(!player.equals(context.getActivePlayer())) {
				addAction(player, ProposeTrade.class);
			}
		}
	}
}
