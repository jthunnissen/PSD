package actions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.*;

public class DrawFaceUpCards extends ActionBase {

	public DrawFaceUpCards(Game game, Player player) {
		super(game, player);
	}
	
	@Override
	public void handle(Object[] args) throws IllegalActionException {
		Map<Boolean, Card> asideCards = new HashMap<Boolean, Card>();
		asideCards.put(false, game.drawCard());
		asideCards.put(false, game.drawCard());
		player.setFaceUpCards(asideCards);
	}

}
