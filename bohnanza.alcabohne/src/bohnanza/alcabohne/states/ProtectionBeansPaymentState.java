package bohnanza.alcabohne.states;
import bohnanza.alcabohne.actions.PayProtectionBeans;
import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.Harvest;

public class ProtectionBeansPaymentState extends TurnState<AlCabhoneGame> {

	public ProtectionBeansPaymentState(AlCabhoneGame context) {
		super(context);
		addAction(PayProtectionBeans.class);
		addAction(Harvest.class);
	}

	@Override
	protected boolean handled(Action<? extends GameBase> action) {
		return action instanceof PayProtectionBeans;
	}
}
