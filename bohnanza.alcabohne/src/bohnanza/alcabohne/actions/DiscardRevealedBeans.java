package bohnanza.alcabohne.actions;
import java.util.List;

import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.core.Action;
import bohnanza.core.BeanCard;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class DiscardRevealedBeans extends Action<AlCabhoneGame> {

	public DiscardRevealedBeans(AlCabhoneGame game, Player initiator) {
		super(game, initiator);
	}

	/** Discard the revealed beans left over from previous turns */
	@Override
	protected void innerHandle() throws IllegalActionException {
		for(List<BeanCard> pile : game.getRevealedBeans()) {
			for(BeanCard bean : pile) {
				game.addCardToDiscardPile(bean);
			}
		}
		game.getRevealedBeans().clear();
	}

}
