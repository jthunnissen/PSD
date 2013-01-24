package bohnanza.standard.states;

import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.Player;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.Harvest;
import bohnanza.standard.actions.BuyBeanField;
import bohnanza.standard.actions.PlantAsideBean;
import bohnanza.standard.model.StandardGame;

public class SecondPlantState extends TurnState<StandardGame> {

	public SecondPlantState(StandardGame context) {
		super(context);
		addDefaultActions();
		for(Player player : context.getPlayers()) {
			if(!player.getSetAsideCards().isEmpty()) {
				addAction(player, PlantAsideBean.class);
			}
		}
	}

	@Override
	protected boolean handled(Action<? extends GameBase> action) {
		if(action instanceof PlantAsideBean) {
			Player initiator = action.getInitiator();
			if(initiator.getSetAsideCards().isEmpty()) {
				removeAction(initiator, PlantAsideBean.class);
				removeAction(initiator, Harvest.class);
				removeAction(initiator, BuyBeanField.class);
				if(phaseEnd())
					return true;
			}
		}
		return false;
	}

	private boolean phaseEnd() {
		for(Player player : context.getPlayers()) {
			if(!player.getSetAsideCards().isEmpty())
				return false;
		}
		return true;
	}
}
