import static org.junit.Assert.*;

import java.util.ArrayList;

import main.Card;
import main.GameFactory;

import org.junit.Before;
import org.junit.Test;


public class GameFactoryTest {
	
	GameFactory gameFactory = GameFactory.getInstance();
	final int STANDARDDECKSIZE = 154;

	@Test
	public void singeletonTest() {
		GameFactory gameFactory = GameFactory.getInstance();
		assertSame("There must be only one instance of gameFactory(Singleton)", gameFactory, this.gameFactory);
	}
	
	@Test
	public void standartCardDeckTest() {
		ArrayList<Card> deck = gameFactory.getGameDeck();
		
		assertEquals("Standard deck size must be " + String.valueOf(STANDARDDECKSIZE), STANDARDDECKSIZE, deck.size());
	}

}
