package bohnanza.alcabohne.core;
import java.util.ArrayList;

import bohnanza.alcabohne.actions.GiftBeanToMafia;
import bohnanza.alcabohne.actions.RevealBean;
import bohnanza.alcabohne.states.BeanRevelationState;
import bohnanza.alcabohne.states.CultivationState;
import bohnanza.alcabohne.states.GiftMafiaState;
import bohnanza.alcabohne.states.UseLeftoverBeansState;
import bohnanza.core.AbstractFactory;
import bohnanza.core.Card;
import bohnanza.core.IBeanType;
import bohnanza.core.TurnState;

public class GameFactory extends AbstractFactory {

	private static final GameFactory = new GameFactory();
	
	private GameFactory() {
		super(EBeanType.values());
	}
	
	@Override
	public static AbstractFactory getInstance() {
		return null;
	}

	@Override
	protected void fillStateTransistions() {
		setStartState(null);
		TurnState startState = new StartState(game);
		TurnState useLeftovState = new UseLeftoverBeansState(game);
		startState.addTransition(NextPlayer.class, useLeftovState);
		TurnState giftMafiaState = new GiftMafiaState(game);
		useLeftovState.addTransition(null/*TODO*/, giftMafiaState);
		TurnState plantState = new PlantState(game);
		giftMafiaState.addTransition(GiftBeanToMafia.class, plantState);
		giftMafiaState.addTransition(NextPhase.class, plantState);
		TurnState beanRevelationState = new BeanRevelationState(game);
		plantState.addTransition(NextPhase.class, beanRevelationState);
		TurnState cultivationState = new CultivationState(game);
		beanRevelationState.addTransition(RevealBean.class, cultivationState);
		TurnState drawState = new DrawState(game);
		cultivationState.addTransition(NextPhase.class, drawState);
		drawState.addTransition(DrawCards.class, startState);		
		return plantState;
		
	}

}
