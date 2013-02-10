package tests;

import static org.junit.Assert.*;

import java.util.Collection;

import mocks.GameMock;
import mocks.PlayerMock;

import org.junit.Before;
import org.junit.Test;

import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.core.shared.actions.DrawCards;
import bohnanza.core.shared.actions.NextPhase;
import bohnanza.core.shared.actions.NextPlayer;
import bohnanza.core.shared.actions.PlantBean;

public class RunningGameTest {
	private static final String PLAYER1 = "player1";
	private static final String PLAYER2 = "player2";
	
	private GameMock game;
	private PlayerMock player1;
	private PlayerMock player2;

	@Before
	public void setUp() throws Exception {
		game = new GameMock();
		player1 = new PlayerMock(PLAYER1);
		player2 = new PlayerMock(PLAYER2);
		game.addPlayer(player1);
		game.addPlayer(player2);
		game.start();
	}

	@Test
	public void propertyTest() {
		assertTrue("Game is not set as started.", game.isStarted());
	}
	
	@Test
	public void stateTransitionsTest() {
		try {
			game.handle(new NextPlayer(game));
		} catch (IllegalActionException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
