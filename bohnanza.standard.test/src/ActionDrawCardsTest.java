import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import bohnanza.core.Card;
import bohnanza.core.IllegalActionException;
import bohnanza.core.shared.actions.DrawCards;
import bohnanza.standard.model.BohnanzaPlayer;
import bohnanza.standard.model.StandardGame;

/** Test class for the Player.
 * @author Anne van de Venis & Damiaan van der Kruk */
public class ActionDrawCardsTest {

	private static final String PLAYER_NAME = "Player";
	private BohnanzaPlayer player;
	private StandardGame game;

	@Before
	public void setUp() throws IllegalActionException {
		player = new BohnanzaPlayer(PLAYER_NAME);
		game = new StandardGame();
		game.addPlayer(player);
	}

	@Test
	public void testDraw2Cards() {
		player.addCardToHand(game.drawCard());
		player.addCardToHand(game.drawCard());
		assertThat("Player could not draw two cards", player.getHand().size(), is(2));
	}

	@Test
	public void testDrawAllCards() {
		Card card;
		for(int i = 0; i < 2; i++) {
			for(int cards = 0; cards < GameFactoryTest.STANDARDDECKSIZE; cards++) {
				card = game.drawCard();
				game.addCardToDiscardPile(card);
			}
		}
		assertThat("Stack is not reshuffled", game.getDrawDeck().size() + game.getDiscardPile().size(), is(GameFactoryTest.STANDARDDECKSIZE));
	}

	@Test
	public void testDrawTooManyCards() {
		boolean result = true;
		try {
			for(int i = 0; i < 2; i++) {
				for(int cards = 0; cards < 154; cards++) {

					game.drawCard();

				}
			}
			game.drawCard(); // Game should have been ended?
		} catch(Exception e) {
			result = false;
		}
		assertEquals("Game does not finish", result, false);
	}

	@Test
	public void TestActionHandle() throws IllegalActionException {
		new DrawCards(game, player).handle();
		assertThat("Player hasn't drawn three cards.", player.getHand().size(), is(3));
		assertThat("There where not three cards drawn from the deck", game.getDrawDeck().size(), is(GameFactoryTest.STANDARDDECKSIZE - 3));
	}

}
