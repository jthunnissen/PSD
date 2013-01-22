package bohnanza.standard.actions;

import bohnanza.core.Action;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.Game;

public class DeclineTrade extends Action {

	public DeclineTrade(Game game, Player player) {
		super(game, player);
	}

	@Override
	protected void innerHandle() throws IllegalActionException {}
}
