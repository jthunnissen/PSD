package bohnanza.standard.model;

import bohnanza.core.AbstractFactory;
import bohnanza.standard.actions.DrawCards;
import bohnanza.standard.actions.NextPhase;
import bohnanza.standard.actions.NextPlayer;
import bohnanza.standard.actions.PlantAsideBean;
import bohnanza.standard.states.DrawState;
import bohnanza.standard.states.PlantState;
import bohnanza.standard.states.SecondPlantState;
import bohnanza.standard.states.StartState;
import bohnanza.standard.states.TradeState;

public class GameFactory extends AbstractFactory {

	/**
	 * @uml.property name="singleton" readOnly="true"
	 */
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
		addTransition(SecondPlantState.class, PlantAsideBean.class, DrawState.class);
		addTransition(DrawState.class, DrawCards.class, StartState.class);
	}
}
