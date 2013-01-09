import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import bohnanza.standard.core.*;

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
		assertSame("Player has not two fields", player.getBeanFields().size(), 2);
		assertEquals("Player has incorrect name", player.getName(), PLAYER_NAME);
		assertEquals("Player has strange hand", player.getHand().size(), 0);
	}
	
	@Test
	public void testPlayerPlant(){
		Player player = new Player(PLAYER_NAME);
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		BeanField field = player.getBeanFields().get(0);
		player.addCardToHand(card);
		try {
			player.plantBean(card, field);
		} catch (IllegalActionException e) {
		}
		assertEquals("Card has not been removed from hand", 1, player.getBeanFields().get(1).getCards().size() );
	}
	
	@Test
	public void testPlayerHarvest(){
		Player player = new Player(PLAYER_NAME);
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		BeanField field = player.getBeanFields().get(0);
		try {
			player.addCardToHand(card);
			player.addCardToHand(card);
			player.plantBean(card, field);
			player.plantBean(card, field);
			player.harvastField(field);
		} catch (IllegalActionException e) {

		}
		assertEquals("Score is incorrect",player.calcScore(), card.getBeanometer(2));
	}
	
	@Test
	public void testPlayerHarvestWithDiscard(){
		Player player = new Player(PLAYER_NAME);
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		BeanField field = player.getBeanFields().get(0);
		int nrOfCards = 2;
		for(int i=0;i<nrOfCards;i++){
			player.addCardToHand(card);
			try {
				player.plantBean(card, field);
			} catch (IllegalActionException e) {
				e.printStackTrace();
			}
		}
		int discard;
		try {
			discard = player.harvastField(field).size();
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
			result = player.buyField();
		} catch (IllegalActionException e) {
			result = false;
		}
		assertEquals("Player can buy field but has not enough money", false, result );
		
		result = true;
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		BeanField field = player.getBeanFields().get(0);
		// 5 BlackEyedBeanCards means 3 coins
		for(int i=0; i<5; i++){
			player.addCardToHand(card);
			try {
				player.plantBean(card, field);
			} catch (IllegalActionException e) {
			}		
		}
		
		try {
			player.harvastField(field);
		} catch (IllegalActionException e) {
			result = false;
		}
		
		try {
			player.buyField();
		} catch (IllegalActionException e) {
			result = false;
			System.out.println(e.getMessage());
		}
		assertEquals("Player can not buy third field", true, result);
		assertEquals("Player does not have a third field", 3, player.getBeanFields().size());
		
		for(int i=0; i<5; i++){
			player.addCardToHand(card);
			try {
				player.plantBean(card, field);
			} catch (IllegalActionException e) {
			}		
		}
		try {
			player.harvastField(field);
		} catch (IllegalActionException e) {
			result = false;
		}
		
		try {
			player.buyField();
		} catch (IllegalActionException e) {
			result = false;
		}
		assertEquals("Player can buy a fourth field", false, result);
		
	}
}
