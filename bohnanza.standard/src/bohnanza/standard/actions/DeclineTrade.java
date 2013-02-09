package bohnanza.standard.actions;

import bohnanza.core.Action;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.StandardGame;

public class DeclineTrade extends Action<StandardGame> {

	public DeclineTrade(StandardGame game, Player player) {
		super(game, player);
	}

	/** Doesn't do anything; just represents the possibility of a player to decline a proposed trade */
	@Override
	protected void innerHandle() throws IllegalActionException {
	}
}
