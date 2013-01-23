package bohnanza.core.shared.actions;

import java.util.ArrayList;
import bohnanza.core.Action;
import bohnanza.core.Card;
import bohnanza.core.Field;
import bohnanza.core.GameBase;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class Harvest extends Action<GameBase> {

	private final Field<Card> field;

	public Harvest(GameBase game, Player initiator, Field<Card> field) {
		super(game, initiator);
		this.field = field;
	}

	@Override
	/**
	 * Harvest specified field from a Player
	 * @param args[0] - Number of the field of the to be harvested field
	 */
	protected void innerHandle() throws IllegalActionException {
		ArrayList<Card> discard = new ArrayList<Card>();
		discard = initiator.harvastField(field);
		for(Card card : discard) {
			game.addCardToDiscardPile(card);
		}
	}

}
