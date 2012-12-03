package actions;
import main.*;

public class SetAsideCard extends ActionBase {

	public SetAsideCard(Game game, Player player) {
		super(game, player);
	}
	
	@Override
	public void handle(Object[] args) throws IllegalActionException {
		Card card = (Card)args[0];
		player.setFaceUpCardaside(card);
	}

}
