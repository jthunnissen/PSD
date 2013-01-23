package bohnanza.alcabohne.states;
import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.TurnState;

public class BeanRevelationState extends TurnState<AlCabhoneGame> {

	public BeanRevelationState(AlCabhoneGame context) {
		super(context);
	}

	@Override
	protected boolean handled(Action<? extends GameBase> action) {
		if(context.getRevealedBeans().size() == 3) {
			return true;
		}
		return false;
	}

}
