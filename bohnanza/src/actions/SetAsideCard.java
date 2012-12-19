package actions;
import main.*;

public class SetAsideCard extends Action {

	public SetAsideCard(Game game) {
		super(game);
	}
	
	@Override
	public void handle(Object[] args) throws IllegalActionException {
		Card card = (Card)args[0];
		player.setFaceUpCardaside(card);
	}

}
