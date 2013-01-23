package bohnanza.alcabohne.model;
import bohnanza.alcabohne.actions.GiftBeanToMobb;
import bohnanza.alcabohne.actions.RevealBean;
import bohnanza.alcabohne.states.BeanRevelationState;
import bohnanza.alcabohne.states.CultivationState;
import bohnanza.alcabohne.states.GiftMobState;
import bohnanza.alcabohne.states.UseLeftoverBeansState;
import bohnanza.core.AbstractFactory;
import bohnanza.core.shared.actions.DrawCards;
import bohnanza.core.shared.actions.NextPhase;
import bohnanza.core.shared.actions.NextPlayer;
import bohnanza.core.shared.states.DrawState;
import bohnanza.core.shared.states.PlantState;
import bohnanza.core.shared.states.StartState;

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
		addTransition(UseLeftoverBeansState.class, null /* TODO */, GiftMobState.class);
		addTransition(GiftMobState.class, GiftBeanToMobb.class, PlantState.class);
		addTransition(GiftMobState.class, NextPhase.class, PlantState.class);
		addTransition(PlantState.class, NextPhase.class, BeanRevelationState.class);
		addTransition(BeanRevelationState.class, RevealBean.class, CultivationState.class);
		addTransition(CultivationState.class, NextPhase.class, DrawState.class);
		addTransition(DrawState.class, DrawCards.class, StartState.class);
	}

}
