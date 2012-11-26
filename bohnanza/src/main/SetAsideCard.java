package main;


public class SetAsideCard extends Action {

	public SetAsideCard(Game game, Player player) {
		super(game, player);
	}
	
	@Override
	public boolean handle(Object[] args) {
		int i = (int) args[0];
		return game.setAsideFaceUpCard(i);
	}

}
