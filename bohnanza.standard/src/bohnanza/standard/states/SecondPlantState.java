package bohnanza.standard.states;

import bohnanza.core.Action;
import bohnanza.core.Player;
import bohnanza.core.TurnState;
import bohnanza.standard.actions.BuyBeanField;
import bohnanza.standard.actions.Harvest;
import bohnanza.standard.actions.PlantAsideBean;
import bohnanza.standard.model.Game;

public class SecondPlantState extends TurnState {

	public SecondPlantState(Game context) {
		super(context);
	}

	@Override
	protected boolean handled(Action action) {
		if(action instanceof PlantAsideBean) {
			Player initiator = action.getInitiator();
			if(initiator.getSetAsideCards().isEmpty()) {
				removeAction(initiator, PlantAsideBean.class);
				removeAction(initiator, Harvest.class);
				removeAction(initiator, BuyBeanField.class);
				if(phaseEnd()) return true;
			}
		}
		return false;
	}

	private boolean phaseEnd() {
		for(Player player: context.getPlayers()) {
			if(!player.getSetAsideCards().isEmpty()) return false;
		}
		return true;
	}

	@Override
	protected void reset() {
		removeAllActions();
		for(Player player: context.getPlayers()) {
			if(!player.getSetAsideCards().isEmpty()) {
				addAction(player, PlantAsideBean.class);
				addAction(player, Harvest.class);
				addAction(player, BuyBeanField.class);
			}
		}
	}
}

