import static org.junit.Assert.*;
import main.*;
import org.junit.Before;
import org.junit.Test;
import actions.PlantBean;

/**
 * Test class for the Player.
 * @author Anne van de Venis
 */
public class ActionPlantBeanCardTest {

	private static final String PLAYER_NAME = "Player";
	private Player player;
	private Game game;
	private PlantBean action;

	@Before 
	public void setUp() { 
		player = new Player(PLAYER_NAME);
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
