package bohnanza.standard.core.actions;
import java.util.List;

import bohnanza.standard.core.Card;
import bohnanza.standard.core.Game;
import bohnanza.standard.core.IllegalActionException;
import bohnanza.standard.core.Player;


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
