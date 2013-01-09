package bohnanza.standard.core.actions;
import bohnanza.standard.core.*;

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
