package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import mocks.FactoryMock;
import mocks.GameMock;
import mocks.PlayerMock;

import org.junit.Before;
import org.junit.Test;

import bohnanza.core.Card;
import bohnanza.core.IllegalActionException;

public class GameFactoryTest {
	public static final int STANDARDDECKSIZE = 154;
	
	FactoryMock factory = FactoryMock.getInstance();
	GameMock game;
	
	@Before
	public void setUp() throws IllegalActionException {
		game = new GameMock();
		game.addPlayer(new PlayerMock("Player1"));
	}
	
	@Test
	public void standartCardDeckTest() {
		ArrayList<Card> deck = factory.getGameDeck();
		assertEquals("Standard deck size must be " + String.valueOf(STANDARDDECKSIZE), STANDARDDECKSIZE, deck.size());
	}
	
	@Test
	public void testTest(){
		factory.buildTurnStatespace(game);
	}
}
