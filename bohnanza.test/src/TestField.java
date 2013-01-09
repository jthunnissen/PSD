import static org.junit.Assert.*;
import org.junit.Test;

import bohnanza.standard.core.*;

/**
 * Test class for the Field
 * @author Anne van de Venis
 */
public class TestField {
	
	@Test
	public void testAddCard() throws IllegalActionException {
		Card card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		BeanField field = new BeanField();
		field.addCard(card);
	}
	
	@Test
	public void testFieldSetCard() throws IllegalActionException {
		BeanField field = new BeanField();
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		field.addCard(card);
		assertEquals("Field has incorrect card", field.getCards().get(0),card);
	}
	
	@Test(expected=IllegalActionException.class)
	public void testIncorrectCard() throws IllegalActionException {
		BeanField field = new BeanField();
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		field.addCard(card);
		BeanCard errorCard = new BeanCard(EBeanType.BLUEBEAN);	
		field.addCard(errorCard);
	}
	
	@Test
	public void testHarvest() throws IllegalActionException {
		BeanField field = new BeanField();
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		int toHarvest = 5;
		for(int i=0; i<toHarvest; i++){
			field.addCard(card);
		}
		int harvested = field.harvest().size();
		assertEquals("Not all beans are harvested", toHarvest, harvested);
	}
}
