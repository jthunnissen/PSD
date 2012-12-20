package actions;
import java.util.ArrayList;
import main.*;


public class Harvest extends Action {

	private final Field field;
	
	public Harvest(Game game, Player initiator, Field field) {
		super(game, initiator);
		this.field = field;
	}
	
	
	@Override
	/**
	 * Harvest specified field from a Player
	 * @param args[0] - Number of the field of the to be harvested field
	 */
	public void handle() throws IllegalActionException {
		ArrayList<Card> discard = new ArrayList<Card>();
		discard = initiator.harvastField(field);
		for(Card card: discard){
			game.addCardToDiscardPile(card);
		}
	}

}
