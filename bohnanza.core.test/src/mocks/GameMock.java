package mocks;

import bohnanza.core.BaseGame;

public class GameMock extends BaseGame {

	public GameMock() {
		super(FactoryMock.getInstance(), new GameRulesMock());
	}

	@Override
	protected void setupGame() {
		// TODO Auto-generated method stub
	}
}
