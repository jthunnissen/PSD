import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import bohnanza.standard.actions.PlantAsideBean;
import bohnanza.standard.core.*;
import bohnanza.standard.core.actions.PlantBean;
import bohnanza.standard.model.BohnanzaPlayer;
import bohnanza.standard.model.Game;

/**
 * Test class for the Player.
 * @author Anne van de Venis
 */
public class ActionPlantBeanCardTest {

	private static final String PLAYER_NAME = "Player";
	private BohnanzaPlayer player;
	private Game game;
	private PlantAsideBean action;

	@Before 
	public void setUp() { 
		player = new BohnanzaPlayer(PLAYER_NAME);
		game = new Game();
		action = new PlantBean(game);
	}
	
	@Test
	public void testPlant() throws IllegalActionException {
		Object args[] = {1};
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		player.addCardToHand(card);
		player.addCardToHand(card);
		action.handle(player, args);		
	}
	
	@Test(expected=IllegalActionException.class)
	public void testPlantNoCardsInHands() throws IllegalActionException {
		Object args[] = {1};
		action.handle(player, args);	
	}
	
	@Test(expected=IllegalActionException.class)
	public void testPlantInNotMatchingField() throws IllegalActionException {
		BeanCard card1 = new BeanCard(EBeanType.BLACKEYEDBEAN);
		BeanCard card2 = new BeanCard(EBeanType.BLUEBEAN);
		player.addCardToHand(card1);
		player.addCardToHand(card2);
		Object args[] = {1};
		action.handle(player, args);
		action.handle(player, args);
	}
}
