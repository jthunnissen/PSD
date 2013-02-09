package bohnanza.alcabohne.actions;
import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.alcabohne.model.MobBoss;
import bohnanza.core.Action;
import bohnanza.core.BeanField;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class PayProtectionBeans extends Action<AlCabhoneGame> {

	public PayProtectionBeans(AlCabhoneGame game, Player initiator) {
		super(game, initiator);
	}

	/** Pay a single bean to the mob for each type you both cultivate */
	@Override
	protected void innerHandle() {
		for(BeanField beanfield : initiator.getBeanFields()) {
			if(!beanfield.isEmpty()) {
				for(MobBoss boss : game.getMobbosses()) {
					if(boss.getBeanType() == beanfield.getBeanType())
						try {
							boss.addCard(beanfield.removeCard());
						} catch (IllegalActionException e) {/*This should never happen*/}
				}
			}
		}
	}

}
