package bohnanza.core.shared.actions;
import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class BuyBeanField extends Action<GameBase> {

	public BuyBeanField(GameBase game, Player initiator) {
		super(game, initiator);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void innerHandle() throws IllegalActionException {
		initiator.buyField();
	}

}