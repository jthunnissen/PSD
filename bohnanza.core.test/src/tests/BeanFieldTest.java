package tests;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

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
		assertThat("added card is not the same as in the field", field.getFirstCard(), is(card1));
	}
	
	@Test
	public void testRemoveCard() throws IllegalActionException {
		field.addCard(card1);
		assertThat("Field did not return the right card", field.removeCard(), is(card1));
		assertThat("Card was not removed from the field", field.size(), is(0));
	}
	
	@Test(expected = IllegalActionException.class)
	public void testRemoveCardFromEmptyField() throws IllegalActionException {
		field.removeCard();
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
	
	@Test
	public void testAddAllCards() throws IllegalActionException {
		Collection<BeanCard> cards = new ArrayList<BeanCard>();
		for(int i = 0; i < 3; i++) {
			cards.add(card1);
		}
		
		field.addAllCards(cards);
		assertThat("Cards where not correctly added", field.size(), is(3));
		
		field.addAllCards(cards);
		assertThat("Second add all did not correclty added the cards", field.size(), is(6));
	}
}
