package bohnanza.alcabohne.actions;

import bohnanza.alcabohne.model.Game;
import bohnanza.alcabohne.model.MobBoss;
import bohnanza.core.Action;
import bohnanza.core.BeanCard;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class GiftBeanToMobb extends Action {

	private final BeanCard card;
	
	public GiftBeanToMobb(Game game, Player initiator, BeanCard card) {
		super(game, initiator);
		this.card = card;
	}

	@Override
	protected void innerHandle() throws IllegalActionException {
		if(!initiator.getHand().contains(card)) throw new IllegalActionException("Player does not own this card");
		for(MobBoss boss : ((Game)game).getMobbosses()) {
			if(boss.getBeanType()==card.getType()) {
				boss.addCard(card);
				initiator.getHand().remove(card);
				return;
			}
		}
		throw new IllegalActionException("No mob boss cultivates this type of bean");
	}

}
