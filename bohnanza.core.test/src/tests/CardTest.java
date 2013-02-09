package tests;
import static org.junit.Assert.assertEquals;
import mocks.EBeanTypeMock;

import org.junit.Test;

import bohnanza.core.BeanCard;

/**
 * 
 */

/** Test class cards.
 * @author Anne & Damiaan */
public class CardTest {

	@Test
	public void testInit() {
		BeanCard card = new BeanCard(EBeanTypeMock.BLACKEYEDBEAN);
		assertEquals("Card name is not created correctly", card.getName(), EBeanTypeMock.BLACKEYEDBEAN.toString());
		assertEquals("No. of cards is not created correctly", card.getNumberOfCards(), EBeanTypeMock.BLACKEYEDBEAN.numberOfCards());
		assertEquals("Produces of card is not created correctly", card.getProduce(), EBeanTypeMock.BLACKEYEDBEAN.beanometer());
	}

	@Test
	public void testBeanoMeter() {
		BeanCard card = new BeanCard(EBeanTypeMock.BLACKEYEDBEAN);
		assertEquals("Incorrect beanometer", 0, card.getBeanometer(0));
		assertEquals("Incorrect beanometer", 0, card.getBeanometer(1));
		assertEquals("Incorrect beanometer", 1, card.getBeanometer(2));
		assertEquals("Incorrect beanometer", 2, card.getBeanometer(4));
		assertEquals("Incorrect beanometer", 3, card.getBeanometer(5));
		assertEquals("Incorrect beanometer", 4, card.getBeanometer(6));
		assertEquals("Incorrect beanometer", 4, card.getBeanometer(7));
	}
}
