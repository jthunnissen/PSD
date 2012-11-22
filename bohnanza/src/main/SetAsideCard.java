package main;


public class SetAsideCard extends Action {

	@Override
	public boolean handle(Player player, Object[] args) {
		int i = (int) args[0];
		return Game.thisGame.setAsideFaceUpCard(i);
	}

}
