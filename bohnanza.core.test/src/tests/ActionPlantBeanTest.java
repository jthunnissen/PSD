package tests;
import static org.junit.Assert.assertEquals;
import mocks.EBeanTypeMock;
import mocks.GameMock;
import mocks.PlayerMock;

import org.junit.Before;
import org.junit.Test;

import bohnanza.core.BeanCard;
import bohnanza.core.BeanField;
import bohnanza.core.IllegalActionException;
import bohnanza.core.shared.actions.PlantBean;

/**
 * 
 */

/** Test class for the Player.
 * @author Anne van de Venis */
public class ActionPlantBeanTest {

	private static final String PLAYER_NAME = "Player";
	private PlayerMock player;
	private GameMock game;

	@Before
	public void setUp() throws IllegalActionException {
		player = new PlayerMock(PLAYER_NAME);
		game = new GameMock();
		game.addPlayer(player);
	}

	@Test
	public void testDraw2Cards() {
		boolean result = false;
		if(player.addCardToHand(game.drawCard()) && player.addCardToHand(game.drawCard())) {
			result = true;
		}

		assertEquals("Player could not draw two cards", result, true);
	}

	@Test
	public void testDrawAllCards() {
		boolean result = true;
		try {
			for(int i = 0; i < 2; i++) {
				for(int cards = 0; cards < 154; cards++) {
					game.addCardToDiscardPile(game.drawCard());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			result = false;
		}
		assertEquals("Stack is not reshuffled", result, true);
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
	public void testPlantBeanAction() throws IllegalActionException {
		BeanCard card1 = new BeanCard(EBeanTypeMock.CHILIBEAN);
		BeanField field = player.getFirstEmptyField();
		player.addCardToHand(card1);
		player.addCardToHand(card1);
		player.addCardToHand(card1);
		player.addCardToHand(card1);
		new PlantBean(game, player, card1, field).handle();
		assertEquals("Card was not added to field", 1, field.size());
		new PlantBean(game, player, card1, field).handle();
		new PlantBean(game, player, card1, field).handle();
		assertEquals("Card multiple cards where not added to field", 3, field.size());
		BeanField field2 = player.getFirstEmptyField();
		new PlantBean(game, player, card1, field2).handle();
		assertEquals("Card was not added to an another field", 1, field2.size());
	}
	
	@Test(expected=IllegalActionException.class)
	public void testIlligalPlantBeanAction() throws IllegalActionException {
		BeanCard card1 = new BeanCard(EBeanTypeMock.CHILIBEAN);
		BeanCard card2 = new BeanCard(EBeanTypeMock.GREENBEAN);
		BeanField field = player.getFirstEmptyField();
		player.addCardToHand(card1);
		player.addCardToHand(card2);
		new PlantBean(game, player, card1, field).handle();
		new PlantBean(game, player, card2, field).handle();
	}

}
