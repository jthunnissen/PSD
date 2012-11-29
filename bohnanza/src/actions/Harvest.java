package actions;

import java.util.ArrayList;
import exceptions.IllegalActionException;
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
		ArrayList<Card> discard = new ArrayList<Card>();
		try {
			discard = player.harvastField(fieldnr);
		} catch (IllegalActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Card card: discard){
			game.addCardToDiscardPile(card);
		}
		return true;
	}

}
