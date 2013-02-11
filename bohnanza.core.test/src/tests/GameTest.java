package tests;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import java.util.List;

import mocks.GameMock;
import mocks.PlayerMock;

import org.junit.Before;
import org.junit.Test;

import bohnanza.core.Card;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class GameTest {

	public final String PLAYER1_NAME = "player1";
	public final String PLAYER2_NAME = "player2";
	public final String PLAYER3_NAME = "player3";

	public GameMock game;

	@Before
	public void setUp() throws Exception {
		game = new GameMock();
	}

	@Test
	public void defaultTest() {
		assertNotNull("Players must not be null", game.getPlayers());
		assertEquals("Players must be empty on creation", 0, game.getPlayers().size());
	}

	@Test
	public void playerTest() throws IllegalActionException {
		Player player1 = new PlayerMock(PLAYER1_NAME);
		Player player2 = new PlayerMock(PLAYER2_NAME);
		Player player3 = new PlayerMock(PLAYER3_NAME);

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

	@Test(expected = IllegalArgumentException.class)
	public void goToNextPlayerInvalidArgsTest() {
		game.goToNextPlayer(-1);
	}

	@Test
	public void shuffleDeck() {
		List<Card> game1DrawDesk = game.getDrawDeck();
		List<Card> game2DrawDesk = new GameMock().getDrawDeck();

		assertThat(game1DrawDesk.size(), is(game2DrawDesk.size()));
		assertThat(game1DrawDesk, not(equalTo(game2DrawDesk)));
	}

	@Test
	public void drawCardsTest() {
		int drawDeskSize = game.getDrawDeck().size();

		assertThat(game.drawCard(), instanceOf(Card.class));
		assertThat(game.getDrawDeck().size(), is(drawDeskSize - 1));
		assertThat(game.getDiscardPile().size(), is(0));
	}
}
