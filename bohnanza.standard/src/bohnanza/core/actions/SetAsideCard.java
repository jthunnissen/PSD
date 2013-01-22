package bohnanza.core.actions;
import bohnanza.core.*;

public class SetAsideCard extends Action {

	private final Card card;
	
	public SetAsideCard(Game game, Player initiator, Card card) {
		super(game, initiator);
		this.card = card;
	}
	
	@Override
	public void handle() throws IllegalActionException {
		initiator.setFaceUpCardaside(card);
	}

}
