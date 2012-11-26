import static org.junit.Assert.*;
import main.BeanCard;
import main.Card;
import main.EBeanType;
import main.Player;
import org.junit.Test;

/**
 * 
 */

/**
 * Test class for the Player.
 * @author Anne van de Venis
 */
public class TestPlayer {
	
	private static final String PLAYER_NAME = "Player";


	@Test
	public void testPlayerInitScore(){
		Player player = new Player(PLAYER_NAME);
		assertSame("Score is not 0", player.calcScore(), 0);
	}
	
	@Test
	public void testPlayerInitFields(){
		Player player = new Player(PLAYER_NAME);
		assertSame("Player has not two fields", player.getFields().size(), 2);
	}
	
	@Test
	public void testPlayerInitName(){
		Player player = new Player(PLAYER_NAME);
		assertEquals("Player has incorrect name", player.getName(), PLAYER_NAME);
	}

	@Test
	public void testPlayerPlant(){
		Player player = new Player(PLAYER_NAME);
		Card card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		player.addCardToHand(card);
		int cardsInHand = player.getHand().size();
		player.plantBean(1);
		assertEquals("Card has not been removed from hand", cardsInHand - 1,player.getHand().size() );
	}
	
	@Test
	public void testPlayerScore(){
		Player player = new Player(PLAYER_NAME);
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		player.addCardToHand(card);
		player.plantBean(1);
		int score = player.harvastField(1);
		assertEquals("Score is incorrect",player.calcScore(), score);
	}
}
