package bohnanza.standard.actions;

import bohnanza.core.Action;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.Game;

public class BuyBeanField extends Action {


	public BuyBeanField(Game game, Player initiator) {
		super(game, initiator);
	}
	
	@Override
	/**
	 * Player buys a new bean field
	 */
	public void handle() throws IllegalActionException {
		initiator.buyField();
	}
}
