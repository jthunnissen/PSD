package tests;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import mocks.EBeanTypeMock;
import mocks.GameMock;
import mocks.PlayerMock;

import org.junit.Before;
import org.junit.Test;

import bohnanza.core.BeanCard;
import bohnanza.core.BeanField;
import bohnanza.core.Card;
import bohnanza.core.IllegalActionException;
import bohnanza.core.shared.actions.PlantBean;

/** Test class for the Player.
 * @author Anne van de Venis & Damiaan van der Kruk */
public class ActionPlantBeanCardTest {

	private static final String PLAYER_NAME = "Player";
	private PlayerMock player;
	private GameMock game;
	private BeanCard card1;
	private BeanCard card2;
	private BeanField field;

	@Before
	public void setUp() throws IllegalActionException {
		player = new PlayerMock(PLAYER_NAME);
		game = new GameMock();
		game.addPlayer(player);
		card1 = new BeanCard(EBeanTypeMock.BLACKEYEDBEAN);
		card2 = new BeanCard(EBeanTypeMock.CHILIBEAN);
		field = player.getBeanFields().get(0);
	}

	@Test
	public void testPlant() throws IllegalActionException {
		player.addCardToHand(card1);
		player.addCardToHand(card2);
		new PlantBean(game, player, card1, field).handle();
		assertThat("Card wasn't removed from the hand", player.getHand(), not(hasItem((Card) card1)));
		assertThat("Card wasn't planted in the players field", field.getCards(), hasItem(card1));
	}

	@Test(expected = IllegalActionException.class)
	public void testPlantNoCardsInHands() throws IllegalActionException {
		player.addCardToHand(card1);
		new PlantBean(game, player, card2, field).handle();
	}

	@Test(expected = IllegalActionException.class)
	public void testPlantInNotMatchingField() throws IllegalActionException {
		player.addCardToHand(card1);
		player.addCardToHand(card2);
		new PlantBean(game, player, card1, field).handle();
		new PlantBean(game, player, card2, field).handle();
	}
}
