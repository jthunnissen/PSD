package bohnanza.alcabohne.actions;
import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.alcabohne.model.MobBoss;
import bohnanza.core.Action;
import bohnanza.core.BeanField;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

/** Pay a single bean to the mob for each type you both cultivate */
public class PayProtectionBeans extends Action<AlCabhoneGame> {

	public PayProtectionBeans(AlCabhoneGame game, Player initiator) {
		super(game, initiator);
	}

	@Override
	protected void innerHandle() throws IllegalActionException {
		for(BeanField beanfield : initiator.getBeanFields()) {
			if(!beanfield.isEmpty()) {
				for(MobBoss boss : game.getMobbosses()) {
					if(boss.getBeanType() == beanfield.getBeanType())
						boss.addCard(beanfield.removeCard());
				}
			}
		}
	}

}
