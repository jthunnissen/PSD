package actions;

import java.util.List;

import main.Action;
import main.Game;
import main.Player;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class Harvest extends Action {

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
		int result = player.harvastField(fieldnr);
		return (result >= 0);
	}

}
