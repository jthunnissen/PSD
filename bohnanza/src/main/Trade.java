package main;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class Trade extends Action {

	public Trade(Game game, Player player) {
		super(game, player);
	}
	
	@Override
	public boolean handle(Object[] args) {
		throw new NotImplementedException();
	}

}
