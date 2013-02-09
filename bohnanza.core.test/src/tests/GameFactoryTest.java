package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import mocks.FactoryMock;

import org.junit.Test;

import bohnanza.core.Card;

public class GameFactoryTest {
	public static final int STANDARDDECKSIZE = 154;
	
	@Test
	public void standartCardDeckTest() {
		ArrayList<Card> deck = new FactoryMock().getGameDeck();
		assertEquals("Standard deck size must be " + String.valueOf(STANDARDDECKSIZE), STANDARDDECKSIZE, deck.size());
	}
}
