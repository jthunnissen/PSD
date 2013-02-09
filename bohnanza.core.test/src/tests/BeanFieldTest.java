package tests;
import static org.junit.Assert.assertEquals;

import mocks.EBeanTypeMock;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import bohnanza.core.BeanCard;
import bohnanza.core.BeanField;
import bohnanza.core.IllegalActionException;

/** Test class for the Field
 * @author Anne van de Venis & Damiaan van der Kruk */
public class BeanFieldTest {
	
	private BeanCard card1;
	private BeanField field;
	
	@Before
	public void setUp() {
		card1 = new BeanCard(EBeanTypeMock.BLACKEYEDBEAN);
		field = new BeanField();
	}

	@Test
	public void testAddCard() throws IllegalActionException {
		field.addCard(card1);
	}
	
	@Ignore("Not relevant anymore.")
	@Test(expected = IllegalActionException.class)
	public void testAddSameCard() throws IllegalActionException {
		field.addCard(card1);
		field.addCard(card1);
	}

	@Test
	public void testFieldSetCard() throws IllegalActionException {
		field.addCard(card1);
		assertEquals("Field has incorrect card", field.getCards().get(0), card1);
	}

	@Test(expected = IllegalActionException.class)
	public void testAddOfOtherTypeCard() throws IllegalActionException {
		field.addCard(card1);
		field.addCard(new BeanCard(EBeanTypeMock.BLUEBEAN));
	}

	@Test
	public void testHarvest() throws IllegalActionException {
		int toHarvest = 5;
		for(int i = 0; i < toHarvest; i++) {
			field.addCard(card1);
		}
		assertEquals("Not all beans are harvested", toHarvest, field.harvest().size());
	}
}
