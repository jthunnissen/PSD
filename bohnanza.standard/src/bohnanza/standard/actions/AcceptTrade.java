package bohnanza.standard.actions;
import java.util.List;
import bohnanza.core.Action;
import bohnanza.core.Card;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.StandardGame;

public class AcceptTrade extends Action<StandardGame> {

	private final Player otherPlayer;
	private final List<Card> cards;
	private final List<Card> offer;

	public AcceptTrade(StandardGame game, Player player, Player otherPlayer,
			List<Card> cards, List<Card> offer) {
		super(game, player);
		this.otherPlayer = otherPlayer;
		this.cards = cards;
		this.offer = offer;
	}

	@Override
	protected void innerHandle() throws IllegalActionException {
		otherPlayer.trade(cards, offer, false);
		initiator.trade(offer, cards, true);
	}

}