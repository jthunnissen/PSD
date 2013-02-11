package bohnanza.alcabohne.states;
import bohnanza.alcabohne.actions.CultivateRevealedBeanType;
import bohnanza.alcabohne.actions.DiscardRevealedBeans;
import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.core.Action;
import bohnanza.core.BaseGame;
import bohnanza.core.TurnState;

public class UseLeftoverBeansState extends TurnState<AlCabhoneGame> {

	public UseLeftoverBeansState(AlCabhoneGame context) {
		super(context);
		addAction(CultivateRevealedBeanType.class);
		addAction(DiscardRevealedBeans.class);
	}

	@Override
	protected boolean handled(Action<? extends BaseGame> action) {
		return action instanceof DiscardRevealedBeans || context.getRevealedBeans().isEmpty();
	}

}
