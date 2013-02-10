package mocks;

import bohnanza.core.AbstractFactory;
import bohnanza.core.shared.actions.DrawCards;
import bohnanza.core.shared.actions.NextPlayer;
import bohnanza.core.shared.states.PlantState;
import bohnanza.core.shared.states.StartState;

public class FactoryMock extends AbstractFactory {

	public static FactoryMock instance = new FactoryMock();
	
	private FactoryMock() {
		super(EBeanTypeMock.values());
	}
	
	public static FactoryMock getInstance() {
		return instance;
	}

	@Override
	protected void fillStateTransistions() {
		setStartState(PlantState.class);
		addTransition(PlantState.class, DrawCards.class, StartState.class);
		addTransition(StartState.class, NextPlayer.class, PlantState.class);
		
	}

}
