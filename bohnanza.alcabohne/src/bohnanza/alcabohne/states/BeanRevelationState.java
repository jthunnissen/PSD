package bohnanza.alcabohne.states;
import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.Player;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.Harvest;

public class BeanRevelationState extends TurnState<AlCabhoneGame> {

	public BeanRevelationState(AlCabhoneGame context) {
		super(context);
		for(Player player : context.getPlayers()) {
			removeAction(player, Harvest.class);
		}
	}

	@Override
	protected boolean handled(Action<? extends GameBase> action) {
		if(context.getRevealedBeans().size() == 3) {
			return true;
		}
		return false;
	}

}
