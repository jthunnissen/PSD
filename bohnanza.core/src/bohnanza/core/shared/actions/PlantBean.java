package bohnanza.core.shared.actions;
import java.text.MessageFormat;
import bohnanza.core.Action;
import bohnanza.core.BeanCard;
import bohnanza.core.BeanField;
import bohnanza.core.GameBase;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class PlantBean extends Action<GameBase> {

	private final BeanCard card;
	private final BeanField field;

	public PlantBean(GameBase game, Player initiator, BeanCard card,
			BeanField field) {
		super(game, initiator);
		this.card = card;
		this.field = field;
	}

	@Override
	protected void innerHandle() throws IllegalActionException {
		if(!(initiator.getHand().indexOf(card) == 0))
			throw new IllegalActionException(MessageFormat.format(
					"Can only plant first card in hand ({0})", initiator
							.getHand().get(0).getName()));

		initiator.plantBean(card, field);
	}
}
