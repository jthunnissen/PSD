package bohnanza.alcabohne.states;
import bohnanza.alcabohne.actions.CultivateRevealedBeanType;
import bohnanza.alcabohne.actions.GiftBeanToMobb;
import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.Harvest;
import bohnanza.core.shared.actions.NextPhase;

public class CultivationState extends TurnState<AlCabhoneGame> {

	public CultivationState(AlCabhoneGame context) {
		super(context);
		addAction(GiftBeanToMobb.class);
		addAction(Harvest.class);
		addAction(CultivateRevealedBeanType.class);
	}

	@Override
	protected boolean handled(Action<? extends GameBase> action) {
		if(action instanceof CultivateRevealedBeanType
				&& context.getRevealedBeans().isEmpty()) {
			removeAction(CultivateRevealedBeanType.class);
			addAction(NextPhase.class);
		}
		return action instanceof NextPhase;
	}

}
