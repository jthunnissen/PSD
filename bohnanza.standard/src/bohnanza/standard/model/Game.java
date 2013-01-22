/**
 * 
 */
package bohnanza.standard.model;

import bohnanza.core.GameBase;
import bohnanza.core.Player;

/**
 * @author Damiaan
 * @uml.dependency supplier="main.GameFactory" stereotypes="Standard::Call"
 */
public class Game extends GameBase {

	public Game() {
		super(GameFactory.getInstance());
	}

	@Override
	public void start() {
		for(Player player: getPlayers()){
			for(int i=0; i<5; i++) {
				player.addCardToHand(this.drawCard());
			}
		}
		setCurrentState(factory.buildTurnStatespace(this, this.getActivePlayer()));
		started  = true;
	}
	
}