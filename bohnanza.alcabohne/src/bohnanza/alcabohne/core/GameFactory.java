package bohnanza.alcabohne.core;
import bohnanza.alcabohne.actions.GiftBeanToMafia;
import bohnanza.alcabohne.states.BeanRevelationState;
import bohnanza.alcabohne.states.CultivationState;
import bohnanza.alcabohne.states.GiftMafiaState;
import bohnanza.alcabohne.states.UseLeftoverBeansState;
import bohnanza.standard.core.*;
import bohnanza.standard.core.states.*;
import bohnanza.standard.core.actions.*;

public class GameFactory extends bohnanza.standard.core.GameFactory {

	private GameFactory() {}
	
	/**Links the different states that compose a turn together
	 * @return the start state of the game
	 */
	@Override
	public TurnState buildTurnStatespace(Game game, Player activePlayer) {
		TurnState startState = new TurnStart(game);
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
