package bohnanza.alcabohne.model;

import bohnanza.alcabohne.actions.GiftBeanToMafia;
import bohnanza.alcabohne.actions.RevealBean;
import bohnanza.alcabohne.states.BeanRevelationState;
import bohnanza.alcabohne.states.CultivationState;
import bohnanza.alcabohne.states.GiftMafiaState;
import bohnanza.alcabohne.states.UseLeftoverBeansState;
import bohnanza.core.AbstractFactory;
import bohnanza.standard.actions.DrawCards;
import bohnanza.standard.actions.NextPhase;
import bohnanza.standard.actions.NextPlayer;
import bohnanza.standard.states.DrawState;
import bohnanza.standard.states.PlantState;
import bohnanza.standard.states.StartState;

public class GameFactory extends AbstractFactory {

	private final static GameFactory singleton = new GameFactory();
	
	private GameFactory() {
		super(EBeanType.values());
	}
	
	public static AbstractFactory getInstance() {
		return singleton;
	}

	@Override
	protected void fillStateTransistions() {
		setStartState(StartState.class);
		addTransition(StartState.class, NextPlayer.class, UseLeftoverBeansState.class);
		addTransition(UseLeftoverBeansState.class, null /*TODO*/, GiftMafiaState.class);
		addTransition(GiftMafiaState.class, GiftBeanToMafia.class, PlantState.class);
		addTransition(GiftMafiaState.class, NextPhase.class, PlantState.class);
		addTransition(PlantState.class, NextPhase.class, BeanRevelationState.class);
		addTransition(BeanRevelationState.class, RevealBean.class, CultivationState.class);
		addTransition(CultivationState.class, NextPhase.class, DrawState.class);
		addTransition(DrawState.class, DrawCards.class, StartState.class);
	}

}
