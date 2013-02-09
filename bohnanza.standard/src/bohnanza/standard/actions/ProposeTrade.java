package bohnanza.standard.actions;

import java.util.List;
import bohnanza.core.Action;
import bohnanza.core.Card;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.StandardGame;

public class ProposeTrade extends Action<StandardGame> {

	private final Player activePlayer;
	private final List<Card> request;
	private final List<Card> offer;

	/** @require !offer.isEmpty()
	 * @param request: if empty the proposed trade is a donation */
	public ProposeTrade(StandardGame game, Player initiator, Player activePlayer, List<Card> request, List<Card> offer) {
		super(game, initiator);
		this.activePlayer = activePlayer;
		this.request = request;
		this.offer = offer;
	}

	/** Propose this trade or donation.
	 * @throws IllegalActionException if this is not an allowed trade/donation */
	@Override
	protected void innerHandle() throws IllegalActionException {
		if(!(initiator == game.getActivePlayer() || activePlayer == game.getActivePlayer()))
			throw new IllegalActionException("Only trades with the active player are allowed");
		if(!initiator.isValidTrade(request, offer, false) || !activePlayer.isValidTrade(offer, request, true))
			throw new IllegalActionException("No valid trade");
	}

	/** Getters of offered and requested cards, the active player */
	
	public List<Card> getGivenCards() {
		return offer;
	}

	public List<Card> getReceivedCards() {
		return request;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}
}
