import static org.junit.Assert.*;

import java.util.ArrayList;

import main.BeanCard;
import main.Card;
import main.EBeanType;
import main.Game;
import main.GameFactory;
import main.Player;

import org.junit.Before;
import org.junit.Test;

import actions.BuyThirdField;
import actions.PlantBean;

import exceptions.IllegalActionException;
import exceptions.NotEnoughMoneyException;

/**
 * 
 */

/**
 * Test class for the Player.
 * @author Anne van de Venis
 */
public class ActionDrawCardsTest {

	private static final String PLAYER_NAME = "Player";
	private Player player;
	private Game game;
	private PlantBean action;

	@Before 
	public void setUp() { 
		Player player = new Player(PLAYER_NAME);
		Game game = new Game();
		PlantBean action = new PlantBean(game, player);
	}
	
	@Test
	public void testPlant(){
		boolean result = true;
		Object args[] = {1};
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		player.addCardToHand(card);
		player.addCardToHand(card);
		result = action.handle(args);
		
		assertEquals("Player could not plant a BeanCard", result, true);		
	}
	
	@Test
	public void testPlantNoCardsInHands(){
		boolean result = true;
		Object args[] = {1};
		result = action.handle(args);
		
		assertEquals("Player planted card but has no cards", result, false);		
	}
	
	@Test 
	public void testPlantInNotMatchingField(){
		BeanCard card1 = new BeanCard(EBeanType.BLACKEYEDBEAN);
		BeanCard card2 = new BeanCard(EBeanType.BLUEBEAN);
		player.addCardToHand(card1);
		player.addCardToHand(card2);
		Object args[] = {1};
		action.handle(args);
		boolean result = true;
		result = action.handle(args);
		
		assertEquals("Player planted card in a not-matching field", result, false);
	}
	


}
