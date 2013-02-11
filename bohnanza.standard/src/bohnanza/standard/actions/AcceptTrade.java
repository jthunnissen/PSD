package bohnanza.standard.actions;
import java.util.List;

import bohnanza.core.Action;
import bohnanza.core.Card;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.StandardGame;

public class AcceptTrade extends Action<StandardGame> {

	private final Player otherPlayer;
	private final List<Card> request;
	private final List<Card> offer;

	/** @require !offer.isEmpty()
	 * @param request: if empty the proposed trade is a donation */
	public AcceptTrade(StandardGame game, Player player, Player otherPlayer, List<Card> request, List<Card> offer) {
		super(game, player);
		this.otherPlayer = otherPlayer;
		this.request = request;
		this.offer = offer;
	}

	/** Aprove the proposed trade and execute it
	 * @throws IllegalActionException if trade is not allowed */
	@Override
	protected void innerHandle() throws IllegalActionException {
		otherPlayer.trade(request, offer, false);
		initiator.trade(offer, request, true);
	}

}