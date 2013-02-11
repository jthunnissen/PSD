package bohnanza.core.shared.actions;
import java.text.MessageFormat;

import bohnanza.core.Action;
import bohnanza.core.BaseGame;
import bohnanza.core.BeanCard;
import bohnanza.core.BeanField;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class PlantBean extends Action<BaseGame> {

	private final BeanCard card;
	private final BeanField field;

	public PlantBean(BaseGame game, Player initiator, BeanCard card, BeanField field) {
		super(game, initiator);
		this.card = card;
		this.field = field;
	}

	/** Plants the first bean in the players hand to specified field
	 * @throws IllegalActionException if action is not allowed */
	@Override
	protected void innerHandle() throws IllegalActionException {
		if(!(initiator.getHand().indexOf(card) == 0))
			throw new IllegalActionException(MessageFormat.format("Can only plant first card in hand ({0})", initiator.getHand().get(0).getName()));

		initiator.plantBean(card, field);
	}
}
