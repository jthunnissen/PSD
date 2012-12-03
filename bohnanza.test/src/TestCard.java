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
public class TestCard {
	
	@Test
	public void testInit(){
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		assertEquals("Card name is not created correctly", card.getName(), EBeanType.BLACKEYEDBEAN.toString());
		assertEquals("No. of cards is not created correctly", card.getNumberOfCards(), EBeanType.BLACKEYEDBEAN.numberOfCards());
	}
	
	@Test
	public void testBeanoMeter(){
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		assertEquals("Incorrect beanometer", 0, card.getBeanometer(0));
		assertEquals("Incorrect beanometer", 0, card.getBeanometer(1));
		assertEquals("Incorrect beanometer", 1, card.getBeanometer(2));
		assertEquals("Incorrect beanometer", 2, card.getBeanometer(4));
		assertEquals("Incorrect beanometer", 3, card.getBeanometer(5));
		assertEquals("Incorrect beanometer", 4, card.getBeanometer(6));
		assertEquals("Incorrect beanometer", 4, card.getBeanometer(7));
	}
}
