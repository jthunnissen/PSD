package bohnanza.alcabohne.states;
import bohnanza.alcabohne.actions.GiftBeanToMobb;
import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.Harvest;
import bohnanza.core.shared.actions.NextPhase;

public class GiftMobState extends TurnState<AlCabhoneGame> {

	public GiftMobState(AlCabhoneGame context) {
		super(context);
		if(needsToPay()) {
			addAction(GiftBeanToMobb.class);
			addAction(Harvest.class);
		} else {
			addAction(NextPhase.class);
		}
	}

	@Override
	protected boolean handled(Action<? extends GameBase> action) {
		if(action instanceof GiftBeanToMobb || action instanceof NextPhase) {
			return true;
		} else if(action instanceof Harvest && !needsToPay()) {
			removeAllActions();
			addAction(NextPhase.class);
		}
		return false;
	}

	private boolean needsToPay() {
		// TODO return true if mafia collects same beans as player
		return false;
	}
}
