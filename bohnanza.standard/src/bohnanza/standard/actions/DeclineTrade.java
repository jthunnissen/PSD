package bohnanza.standard.actions;

import bohnanza.core.Action;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.StandardGame;

public class DeclineTrade extends Action<StandardGame> {

	public DeclineTrade(StandardGame game, Player player) {
		super(game, player);
	}

	@Override
	protected void innerHandle() throws IllegalActionException {
	}
}
