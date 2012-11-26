import static org.junit.Assert.*;

import main.Game;
import main.Player;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class GameTest {
	
	public Game game;
	public final String PLAYER1_NAME = "player1";
	public final String PLAYER2_NAME = "player2";
	public final String PLAYER3_NAME = "player3";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new Game();
	}

	@Test
	public void defaultTest() {
		assertNotNull("Players must not be null", game.getPlayers());
		assertEquals("Players must be empty on creation", 0, game.getPlayers().size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void playerTest() {
		Player player1 = new Player(PLAYER1_NAME);
		Player player2 = new Player(PLAYER2_NAME);
		Player player3 = new Player(PLAYER3_NAME);
		
		game.addPlayer(player1);
		assertEquals("Player size must be 1", 1, game.getPlayers().size());
		
		game.addPlayer(player2);
		assertEquals("Player size must be 2", 2, game.getPlayers().size());
		assertSame("Default player must be the first player added (player1)", player1, game.getCurrentPlayer());
		
		game.goToNextPlayer(0);
		assertSame("The (next) current player must be player2", player2, game.getCurrentPlayer());
		
		game.addPlayer(player3);
		assertEquals("Player size must be 3", 3, game.getPlayers().size());
		assertSame("The current player shouldn't change by adding a player", player2, game.getCurrentPlayer());
		
		game.goToNextPlayer(1);
		assertSame("player 3 must be skipped so current playter must be 1", player1, game.getCurrentPlayer());
		
		game.goToNextPlayer(-1);
	}

}
