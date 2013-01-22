package bohnanza.standard.actions;

import bohnanza.core.Action;
import bohnanza.core.BeanCard;
import bohnanza.core.BeanField;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.standard.model.Game;

public class PlantAsideBean extends Action {

	private final BeanCard card;
	private final BeanField field;
	
	public PlantAsideBean(Game game, Player initiator, BeanCard card, BeanField field) {
		super(game, initiator);
		this.card = card;
		this.field = field;
	}

	@Override
	public void handle() throws IllegalActionException {
		if(initiator.getSetAsideCards().contains(card)) initiator.plantBean(card, field);
		else throw new IllegalActionException("Can only plant set aside cards");
	}
}
