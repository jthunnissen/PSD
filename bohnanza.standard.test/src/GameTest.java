import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import bohnanza.core.Card;
import bohnanza.core.Player;
import bohnanza.standard.model.BohnanzaPlayer;
import bohnanza.standard.model.Game;

public class GameTest {
	
	public final String PLAYER1_NAME = "player1";
	public final String PLAYER2_NAME = "player2";
	public final String PLAYER3_NAME = "player3";
	
	public Game game;

	@Before
	public void setUp() throws Exception {
		game = new Game();
	}

	@Test
	public void defaultTest() {
		assertNotNull("Players must not be null", game.getPlayers());
		assertEquals("Players must be empty on creation", 0, game.getPlayers().size());
	}
	
	@Test
	public void playerTest() {
		Player player1 = new BohnanzaPlayer(PLAYER1_NAME);
		Player player2 = new BohnanzaPlayer(PLAYER2_NAME);
		Player player3 = new BohnanzaPlayer(PLAYER3_NAME);
		
		game.addPlayer(player1);
		assertEquals("Player size must be 1", 1, game.getPlayers().size());
		
		game.addPlayer(player2);
		assertEquals("Player size must be 2", 2, game.getPlayers().size());
		assertSame("Default player must be the first player added (player1)", player1, game.getActivePlayer());
		
		game.goToNextPlayer();
		assertSame("The (next) current player must be player2", player2, game.getActivePlayer());
		
		game.addPlayer(player3);
		assertEquals("Player size must be 3", 3, game.getPlayers().size());
		assertSame("The current player shouldn't change by adding a player", player2, game.getActivePlayer());
		
		game.goToNextPlayer(1);
		assertSame("player 3 must be skipped so current playter must be 1", player1, game.getActivePlayer());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void goToNextPlayerInvalidArgsTest() {
		game.goToNextPlayer(-1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shuffleDeck() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field drawDeskField = Game.class.getDeclaredField("drawDesk");
		drawDeskField.setAccessible(true);
		ArrayList<Card> game1DrawDesk = (ArrayList<Card>) drawDeskField.get(game);
		ArrayList<Card> game2DrawDesk = (ArrayList<Card>) drawDeskField.get(new Game());
		
		assertThat(game1DrawDesk.size(), is(game2DrawDesk.size()));
		assertThat(game1DrawDesk, is(not(equalTo(game2DrawDesk))));
	}
	
	@Test
	public void drawCardsTest() {
		int drawDeskSize = game.getDrawDeskSize();
		
		assertThat("discrad pile size must be 0 by default", game.getDiscardPileSize(), is(0));
		assertThat(game.drawCard(), instanceOf(Card.class));
		assertThat(game.getDrawDeskSize(), is(drawDeskSize-1));
		assertThat(game.getDiscardPileSize(), is(0));
	}
}
