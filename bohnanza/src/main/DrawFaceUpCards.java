package main;


public class DrawFaceUpCards extends Action {

	public DrawFaceUpCards(Game game, Player player) {
		super(game, player);
	}
	
	@Override
	public boolean handle(Object[] args) {
		//game.drawFaceUpCards();
		return true;
	}

}
