package actions;

import main.Game;
import main.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class SetAsideCard extends ActionBase {

	public SetAsideCard(Game game, Player player) {
		super(game, player);
	}
	
	@Override
	public boolean handle(Object[] args) {
		throw new NotImplementedException();
	}

}
