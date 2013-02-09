package bohnanza.core.shared.actions;

import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

/** This action doesn't do anything, but should be interpreted by the gui */
public class ShowHand extends Action<GameBase> {

	public ShowHand(GameBase game, Player initiator) {
		super(game, initiator);
	}

	/** Doesn't do anything; only represents the possibility of a player looking at another players hand, which should be handled in the GUI */
	@Override
	protected void innerHandle() throws IllegalActionException {}

}
