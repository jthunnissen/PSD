import static org.junit.Assert.*;
import org.junit.Test;
import bohnanza.core.BeanCard;
import bohnanza.standard.model.EBeanType;

/**
 * 
 */

/** Test class for the Player.
 * @author Anne van de Venis */
public class TestCard {

	@Test
	public void testInit() {
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		assertEquals("Card name is not created correctly", card.getName(),
				EBeanType.BLACKEYEDBEAN.toString());
		assertEquals("No. of cards is not created correctly",
				card.getNumberOfCards(),
				EBeanType.BLACKEYEDBEAN.numberOfCards());
		assertEquals("Produces of card is not created correctly",
				card.getProduce(), EBeanType.BLACKEYEDBEAN.beanometer());
	}

	@Test
	public void testBeanoMeter() {
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
