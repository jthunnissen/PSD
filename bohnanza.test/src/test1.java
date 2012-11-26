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
}
