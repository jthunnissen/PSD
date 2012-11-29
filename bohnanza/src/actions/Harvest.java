package actions;

import java.util.ArrayList;

import main.Card;
import main.Game;
import main.Player;


public class Harvest extends ActionBase {

	public Harvest(Game game, Player player) {
		super(game, player);
	}
	
	
	@Override
	/**
	 * Harvest specified field from a Player
	 * @param args[0] - Number of the field of the to be harvested field
	 */
	public boolean handle(Object[] args) {
		int fieldnr = (int) args[0];
		ArrayList<Card> discard = player.harvastField(fieldnr);
		for(Card card: discard){
			game.addCardToDiscardPile(card);
		}
		return true;
	}

}
