package bohnanza.alcabohne.states;
import bohnanza.alcabohne.actions.CultivateRevealedBeanType;
import bohnanza.alcabohne.actions.GiftBeanToMobb;
import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.alcabohne.model.MobBoss;
import bohnanza.core.Action;
import bohnanza.core.BeanCard;
import bohnanza.core.Card;
import bohnanza.core.GameBase;
import bohnanza.core.TurnState;
import bohnanza.core.shared.actions.Harvest;
import bohnanza.core.shared.actions.NextPhase;
import bohnanza.core.shared.actions.ShowHand;

public class CultivationState extends TurnState<AlCabhoneGame> {

	public CultivationState(AlCabhoneGame context) {
		super(context);
		addAction(GiftBeanToMobb.class);
		addAction(Harvest.class);
		addAction(CultivateRevealedBeanType.class);
	}

	@Override
	protected boolean handled(Action<? extends GameBase> action) {
		if(action instanceof ShowHand) return true;
		if(action instanceof CultivateRevealedBeanType) {
			removeAction(CultivateRevealedBeanType.class);
		}
		if(context.getRevealedBeans().isEmpty()) {
			if(!mobFieldEmpty()) {
				addAction(NextPhase.class);
			} else if(!canPayTax()) {
				addAction(ShowHand.class);
				removeAction(GiftBeanToMobb.class);
			}
		}
		return action instanceof NextPhase;
	}
	
	private boolean mobFieldEmpty() {
		for(MobBoss boss : context.getMobbosses()) {
			if(boss.getCards().isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	/**Determined whether the active play is able to pay tax to mob without violating the requirement that each mob boss cultivates different beans.*/
	private boolean canPayTax() {
		for(Card card : context.getActivePlayer().getHand()) {
			if(card instanceof BeanCard) {
				boolean alreadyCultivatedByMobb = false;
				for(MobBoss boss : context.getMobbosses()) {
					if(!boss.getCards().isEmpty() && boss.getBeanType()==((BeanCard)card).getType()) {
						alreadyCultivatedByMobb = true;
						break;
					}
				}
				if(!alreadyCultivatedByMobb) return true;
			}
		}
		return false;
	}

}
