package actions;

import java.util.ArrayList;
import java.util.List;

import main.Action;
import main.Card;
import main.Game;
import main.GameFactory;
import main.Player;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class PlantBean extends Action {

	public PlantBean(Game game, Player player) {
		super(game, player);
	}
	
	
	@Override
	/**
	 * Plants first card in a Player's hand in a specified field
	 * @param args[0] - Number of the field where card should be planted
	 */
	public boolean handle(Object[] args) {
		int fieldnr = (int) args[0];
		return player.plantBean(fieldnr);
	}

}
