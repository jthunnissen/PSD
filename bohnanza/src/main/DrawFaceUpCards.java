package main;


public class DrawFaceUpCards extends Action {

	@Override
	public boolean handle(Player player, Object[] args) {
		Game.thisGame.drawFaceUpCards();
		return true;
	}

}
