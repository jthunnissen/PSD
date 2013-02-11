package bohnanza.alcabohne.states;
import bohnanza.alcabohne.actions.PayProtectionBeans;
import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.core.Action;
import bohnanza.core.BaseGame;
import bohnanza.core.TurnState;

public class ProtectionBeansPaymentState extends TurnState<AlCabhoneGame> {

	public ProtectionBeansPaymentState(AlCabhoneGame context) {
		super(context);
		addAction(PayProtectionBeans.class);
		addDefaultActions();
	}

	@Override
	protected boolean handled(Action<? extends BaseGame> action) {
		return action instanceof PayProtectionBeans;
	}
}
