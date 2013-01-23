package bohnanza.standard.model;

import bohnanza.core.AbstractFactory;
import bohnanza.core.shared.actions.DrawCards;
import bohnanza.core.shared.actions.NextPhase;
import bohnanza.core.shared.actions.NextPlayer;
import bohnanza.core.shared.states.DrawState;
import bohnanza.core.shared.states.PlantState;
import bohnanza.core.shared.states.StartState;
import bohnanza.standard.actions.PlantAsideBean;
import bohnanza.standard.states.SecondPlantState;
import bohnanza.standard.states.TradeState;

public class GameFactory extends AbstractFactory {

	/** @uml.property name="singleton" readOnly="true" */
	private static final GameFactory singleton = new GameFactory();

	/**
	 */
	protected GameFactory() {
		super(EBeanType.values());
	}

	/**
	 */
	public static GameFactory getInstance() {
		return singleton;
	}

	protected void fillStateTransistions() {
		setStartState(PlantState.class);
		addTransition(StartState.class, NextPlayer.class, PlantState.class);
		addTransition(PlantState.class, NextPhase.class, TradeState.class);
		addTransition(TradeState.class, NextPhase.class, SecondPlantState.class);
		addTransition(SecondPlantState.class, PlantAsideBean.class,
				DrawState.class);
		addTransition(DrawState.class, DrawCards.class, StartState.class);
	}
}
