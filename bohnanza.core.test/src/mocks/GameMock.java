package mocks;

import bohnanza.core.GameBase;

public class GameMock extends GameBase {

	public GameMock() {
		super(FactoryMock.getInstance(), new GameRulesMock());
	}

	@Override
	protected void setupGame() {
		// TODO Auto-generated method stub
	}
}
