import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import bohnanza.standard.core.*;
import bohnanza.standard.core.actions.*;

/**
 * Test class for the Player.
 * @author Anne van de Venis
 */
public class ActionDrawCardsTest {

	private static final String PLAYER_NAME = "Player";
	private Player player;
	private Game game;
	private DrawFaceUpCards action;

	@Before 
	public void setUp() { 
		player = new Player(PLAYER_NAME);
		game = new Game();
		action = new DrawFaceUpCards(game);
	}

	@Test
	public void testDraw2Cards(){
		boolean result = false;
		if(player.addCardToHand(game.drawCard()) &&
				player.addCardToHand(game.drawCard())){
			result = true;
		}

		assertEquals("Player could not draw two cards", result, true);		
	}

	@Test
	public void testDrawAllCards(){
		boolean result = true;
		for(int i = 0; i<2; i++){
			for(int cards=0; cards<154; cards++) {
				try{
					game.drawCard();
				} catch(Exception e) {
					result = false;
				}
			}
		}
		assertEquals("Stack is not reshuffled", result, true);
	}

	@Test
	public void testDrawTooManyCards(){
		boolean result = true;
		try{
			for(int i = 0; i<2; i++){
				for(int cards=0; cards<154; cards++) {

					game.drawCard();

				}
			}
			game.drawCard(); // Game should have been ended?
		} catch(Exception e) {
			result = false;
		}
		assertEquals("Game does not finish", result, false);
	}


}
