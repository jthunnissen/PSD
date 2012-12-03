import static org.junit.Assert.*;

import java.util.ArrayList;

import main.BeanCard;
import main.BeanField;
import main.Card;
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
	public void testAddCard(){
		Card card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		BeanField field = new BeanField();
		field.addCard(card);
	}
	
	@Test
	public void testFieldSetCard(){
		BeanField field = new BeanField();
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		field.addCard(card);
		assertEquals("Field has incorrect card", field.getCards().get(0),card);
	}
	
	@Test
	public void testIncorrectCard(){
		BeanField field = new BeanField();
		BeanCard card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		field.addCard(card);
		BeanCard errorCard = new BeanCard(EBeanType.BLUEBEAN);	
		boolean result = field.addCard(errorCard);
		assertEquals("Field accepts card from other type", false, result);
	}
	
	@Test
	public void testHarvest(){
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
