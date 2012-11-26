import static org.junit.Assert.*;

import java.util.ArrayList;

import main.BeanCard;
import main.BeanField;
import main.EBeanType;
import main.Field;
import main.Game;
import main.GameFactory;
import main.Player;

import org.junit.Test;

/**
 * 
 */

/**
 * @author Damiaan
 * 
 */
public class test1 {

	private static final String PLAYER_NAME = "Player";

	@Test
	public void testPlayerInitScore() {
		Player player = new Player(PLAYER_NAME);
		assertSame("Score is not 0", player.getScore(), 0);
	}

	@Test
	public void testPlayerInitName() {
		Player player = new Player(PLAYER_NAME);
		assertEquals("Player has incorrect name", player.getName(), PLAYER_NAME);
	}

	@Test
	public void testCardType() {
		BeanCard card = new BeanCard();
		card.setType(EBeanType.BLACKEYEDBEAN);
		assertSame("Card has incorrect type", card.getType(), EBeanType.BLACKEYEDBEAN);
	}

	@Test
	public void testFieldSetCard() {
		BeanField field = new BeanField();
		BeanCard card = new BeanCard();
		field.addCard(card);
		assertSame("Field has incorrect card", field.getCard().get(0), card);
	}

	@Test
	public void testGameInitPlayer() {
		Player player1 = new Player(PLAYER_NAME);
		Player player2 = new Player(PLAYER_NAME);
		Game game = new Game(2);
		game.addPlayer(player1);
		game.addPlayer(player2);
		assertEquals("Game has incorrect number of players", game.getPlayers().size(), 2);
		assertTrue("Player 1 must be in the game", game.getPlayers().contains(player1));
		assertTrue("Player 2 must be in the game", game.getPlayers().contains(player2));
	}
}
