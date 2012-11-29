package actions;

import main.Game;
import main.Player;


public class DrawFaceUpCards extends ActionBase {

	public DrawFaceUpCards(Game game, Player player) {
		super(game, player);
	}
	
	@Override
	public boolean handle(Object[] args) {
		//game.drawFaceUpCards();
		return true;
	}

}
