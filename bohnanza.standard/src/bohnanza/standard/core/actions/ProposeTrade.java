package bohnanza.standard.core.actions;
import java.util.List;

import bohnanza.standard.core.*;

public class ProposeTrade extends Action {

	private final Player activePlayer;
	private final List<Card> cards;
	private final List<Card> offer;
	
	public ProposeTrade(Game game, Player initiator, Player activePlayer, List<Card> cards, List<Card> offer) {
		super(game, initiator);
		this.activePlayer = activePlayer;
		this.cards = cards;
		this.offer = offer;
	}
	
	/**Trade or donate cards
	 * recieve can be null, these means the cards are donated
	 * @require give != null
	 */
	@Override
	public void handle() throws IllegalActionException {
		if(!(initiator == game.getActivePlayer() || activePlayer == game.getActivePlayer())) throw new IllegalActionException("Only trades with the active player are allowed");
		if(!initiator.isValidTrade(cards,offer,false) || !activePlayer.isValidTrade(offer, cards, true)) throw new IllegalActionException("No valid trade");
	}
	
	public List<Card> getGivenCards() {
		return offer;
	}
	
	public List<Card> getReceivedCards() {
		return cards;
	}
	
	public Player getActivePlayer() {
		return activePlayer;
	}
}
