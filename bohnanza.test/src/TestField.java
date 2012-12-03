import static org.junit.Assert.*;
<<<<<<< HEAD
import main.*;
=======

import java.util.ArrayList;

import main.BeanCard;
import main.BeanField;
import main.Card;
import main.EBeanType;
import main.Field;
import main.Game;
import main.GameFactory;
import main.Player;

>>>>>>> branch 'master' of ssh://git@github.com/q41/PSD.git
import org.junit.Test;

/**
 * Test class for the Field
 * @author Anne van de Venis
 */
public class TestField {
	
<<<<<<< HEAD
=======
	@Test
	public void testAddCard(){
		Card card = new BeanCard(EBeanType.BLACKEYEDBEAN);
		BeanField field = new BeanField();
		field.addCard(card);
	}
	
>>>>>>> branch 'master' of ssh://git@github.com/q41/PSD.git
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
