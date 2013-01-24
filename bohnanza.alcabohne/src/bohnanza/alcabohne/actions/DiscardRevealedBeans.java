package bohnanza.alcabohne.actions;
import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.core.Action;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class DiscardRevealedBeans extends Action<AlCabhoneGame> {

	public DiscardRevealedBeans(AlCabhoneGame game, Player initiator) {
		super(game, initiator);
	}

	@Override
	protected void innerHandle() throws IllegalActionException {
		game.getRevealedBeans().clear();
	}

}
