package actions;
import java.util.ArrayList;
import java.util.List;
import main.*;

public class DrawFaceUpCards extends ActionBase {

	public DrawFaceUpCards(Game game) {
		super(game);
	}
	
	@Override
	public void handle(Object[] args) throws IllegalActionException {
		List<Card> asideCards = new ArrayList<Card>();
		asideCards.add(game.drawCard());
		asideCards.add(game.drawCard());
		player.setFaceUpCards(asideCards);
	}

}
