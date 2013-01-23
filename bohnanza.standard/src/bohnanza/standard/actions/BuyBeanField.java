package bohnanza.standard.actions;

import bohnanza.core.Action;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.StandardGame;

public class BuyBeanField extends Action<StandardGame> {

	public BuyBeanField(StandardGame game, Player initiator) {
		super(game, initiator);
	}

	@Override
	/**
	 * Player buys a new bean field
	 */
	protected void innerHandle() throws IllegalActionException {
		initiator.buyField();
	}
}
