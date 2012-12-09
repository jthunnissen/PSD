package actions;

import java.util.ArrayList;
import main.*;


public class Harvest extends ActionBase {

	public Harvest(Game game) {
		super(game);
	}
	
	
	@Override
	/**
	 * Harvest specified field from a Player
	 * @param args[0] - Number of the field of the to be harvested field
	 */
	public void handle(Player player, Object[] args) throws IllegalActionException {
		Field field = (Field) args[0];
		ArrayList<Card> discard = new ArrayList<Card>();
		discard = player.harvastField(field);
		for(Card card: discard){
			game.addCardToDiscardPile(card);
		}
	}

}
