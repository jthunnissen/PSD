import static org.junit.Assert.*;

import java.util.ArrayList;

import main.BeanCard;
import main.Card;
import main.EBeanType;
import main.Player;
import org.junit.Test;

import exceptions.IllegalActionException;
import exceptions.NotEnoughMoneyException;

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
	public void testPlayerInit(){
		Player player = new Player(PLAYER_NAME);
		assertSame("Score is not 0", player.calcScore(), 0);
		assertSame("Player has not two fields", player.getFields().size(), 2);
		assertEquals("Player has incorrect name", player.getName(), PLAYER_NAME);
		assertEquals("Player has strange hand", player.getHand().size(), 0);
	}
	
	@Test
	public void testPlayerPlant(){
		Player player = new Player(PLAYER_NAME);
		Card card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		player.addCardToHand(card);
		try {
			player.plantBean(1);
		} catch (IllegalActionException e) {
		}
		assertEquals("Card has not been removed from hand", 1, player.getFields().get(1).getCards().size() );
	}
	
	@Test
	public void testPlayerHarvest(){
		Player player = new Player(PLAYER_NAME);
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		try {
			player.addCardToHand(card);
			player.addCardToHand(card);
			player.plantBean(1);
			player.plantBean(1);
			player.harvastField(1);
		} catch (IllegalActionException e) {

		}
		assertEquals("Score is incorrect",player.calcScore(), card.getBeanometer(2));
	}
	
	@Test
	public void testPlayerHarvestWithDiscard(){
		Player player = new Player(PLAYER_NAME);
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		int nrOfCards = 2;
		for(int i=0;i<nrOfCards;i++){
			player.addCardToHand(card);
			try {
				player.plantBean(1);
			} catch (IllegalActionException e) {
				e.printStackTrace();
			}
		}
		int discard;
		try {
			discard = player.harvastField(1).size();
		} catch (IllegalActionException e) {
			discard = 0;
		}
		assertEquals("Player discards incorrectly",1, discard);
	}
	
	@Test
	public void testPlayerBuyThirdField(){
		Player player = new Player(PLAYER_NAME);
		boolean result = true;
		try{
			result = player.buyThirdField();
		} catch (IllegalActionException e) {
			result = false;
		}
		assertEquals("Player can buy field but has not enough money", false, result );
		
		result = true;
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		// 5 BlackEyedBeanCards means 3 coins
		for(int i=0; i<5; i++){
			player.addCardToHand(card);
			try {
				player.plantBean(1);
			} catch (IllegalActionException e) {
			}		
		}
		
		try {
			player.harvastField(1);
		} catch (IllegalActionException e) {
			result = false;
		}
		
		try {
			player.buyThirdField();
		} catch (IllegalActionException e) {
			result = false;
			System.out.println(e.getMessage());
		}
		assertEquals("Player can not buy third field", true, result);
		assertEquals("Player does not have a third field", 3, player.getFields().size());
		
		for(int i=0; i<5; i++){
			player.addCardToHand(card);
			try {
				player.plantBean(1);
			} catch (IllegalActionException e) {
			}		
		}
		try {
			player.harvastField(1);
		} catch (IllegalActionException e) {
			result = false;
		}
		
		try {
			player.buyThirdField();
		} catch (IllegalActionException e) {
			result = false;
		}
		assertEquals("Player can buy a fourth field", false, result);
		
	}
}
