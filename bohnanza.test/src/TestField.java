import static org.junit.Assert.*;

import java.util.ArrayList;

import main.BeanCard;
import main.BeanField;
import main.EBeanType;
import main.Field;
import main.Game;
import main.GameFactory;
import main.Player;

import org.junit.Test;

/**
 * 
 */

/**
 * Test class for the Field
 * @author Anne van de Venis
 */
public class TestField {

	@Test
	public void testFieldSetCard(){
		BeanField field = new BeanField();
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		field.addCard(card);
		assertSame("Field has incorrect card", field.getCard().get(0),card);
	}
	
	public void testIncorrectCard(){
		BeanField field = new BeanField();
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		field.addCard(card);
		BeanCard errorCard = new BeanCard(EBeanType.BLUEBEAN);	
		boolean result = field.addCard(errorCard);
		assertEquals("Field accepts card from other type", false, result);
	}

	
}
