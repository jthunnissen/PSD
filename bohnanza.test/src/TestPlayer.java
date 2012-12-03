import static org.junit.Assert.*;

import java.util.ArrayList;

import main.BeanCard;
import main.Card;
import main.EBeanType;
import main.IllegalActionException;
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
	public void testPlayerTakeFirstCard(){
		Player player = new Player(PLAYER_NAME);
		Card card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		player.addCardToHand(card);
		int cardsInHand = player.getHand().size();
		player.takeBean();
		assertEquals("Card has not been removed from hand", cardsInHand - 1,player.getHand().size() );
	}
	
	@Test
	public void testPlayerPlant() throws IllegalActionException{
		Player player = new Player(PLAYER_NAME);
		Card card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		player.addCardToHand(card);
		player.plantBean(1);
		assertEquals("Card has not been removed from hand", 1, player.getFields().get(1).getCards().size() );
	}
	
	@Test
	public void testPlayerHarvest() throws IllegalActionException {
		Player player = new Player(PLAYER_NAME);
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		player.addCardToHand(card);
		player.addCardToHand(card);
		player.plantBean(1);
		player.plantBean(1);
		try {
			player.harvastField(1);
		} catch (IllegalActionException e) {

		}
		assertEquals("Score is incorrect",player.calcScore(), card.getBeanometer(2));
	}
	
	@Test
	public void testPlayerHarvestWithDiscard() throws IllegalActionException {
		Player player = new Player(PLAYER_NAME);
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		int nrOfCards = 2;
		for(int i=0;i<nrOfCards;i++){
			player.addCardToHand(card);
			player.plantBean(1);
		}
		int discard;
		try {
			discard = player.harvastField(1).size();
		} catch (IllegalActionException e) {
			discard = 0;
		}
		assertEquals("Player discards incorrectly",1, discard);
	}
}
