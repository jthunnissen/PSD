package actions;
import main.*;

public class SetAsideCard extends ActionBase {

	public SetAsideCard(Game game) {
		super(game);
	}
	
	@Override
	public void handle(Player player, Object[] args) throws IllegalActionException {
		Card card = (Card)args[0];
		player.setFaceUpCardaside(card);
	}

}
