package main;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class SetAsideCard extends Action {

	public SetAsideCard(Game game, Player player) {
		super(game, player);
	}
	
	@Override
	public boolean handle(Object[] args) {
		throw new NotImplementedException();
	}

}
