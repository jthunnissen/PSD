package bohnanza.standard.model;
import bohnanza.core.BaseGame;
import bohnanza.core.Player;

public class StandardGame extends BaseGame {

	public StandardGame() {
		super(GameFactory.getInstance(), new StandardGameRules());
	}

	@Override
	protected void setupGame() {
		for(Player player : getPlayers()) {
			for(int i = 0; i < 5; i++) {
				player.addCardToHand(this.drawCard());
			}
		}
	}

}