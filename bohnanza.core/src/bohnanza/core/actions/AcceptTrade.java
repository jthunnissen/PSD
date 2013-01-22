package bohnanza.core.actions;
import java.util.List;

import bohnanza.core.Card;
import bohnanza.core.Game;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;


public class AcceptTrade extends Action {

	private final Player otherPlayer;
	private final List<Card> cards;
	private final List<Card> offer;
	
	public AcceptTrade(Game game, Player player, Player otherPlayer, List<Card> cards, List<Card> offer) {
		super(game, player);
		this.otherPlayer = otherPlayer;
		this.cards = cards;
		this.offer = offer;
	}

	@Override
	public void handle() throws IllegalActionException {
		otherPlayer.trade(cards, offer, false);
		initiator.trade(offer, cards, true);
	}

}