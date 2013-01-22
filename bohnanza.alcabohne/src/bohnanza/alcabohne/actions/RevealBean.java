package bohnanza.alcabohne.actions;
import java.util.ArrayList;
import java.util.List;
import bohnanza.alcabohne.model.Game;
import bohnanza.alcabohne.model.MobBoss;
import bohnanza.core.Action;
import bohnanza.core.BeanCard;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

public class RevealBean extends Action {
	
	public RevealBean(Game game, Player initiator) {
		super(game, initiator);
	}

	@Override
	public void handle() throws IllegalActionException {
		BeanCard card;
		do {
			card = (BeanCard)game.drawCard();
		} while(addToExistingPile(card));
		
		List<BeanCard> newPile = new ArrayList<BeanCard>();
		newPile.add(card);
		((Game)game).getRevealedBeans().add(newPile);
		do {
			card = (BeanCard)((Game)game).drawDiscardedCard();
		} while(addToExistingPile(card));
		game.addCardToDiscardPile(card);
	}
	
	/** Checks is card has the same type as one of the already revealed beans or the mob bosses' beans. If so adds card to this pile. */
	private boolean addToExistingPile(BeanCard card) {
		List<List<BeanCard>> beanPiles = new ArrayList<List<BeanCard>>();
		for(MobBoss boss : ((Game)game).getMobbosses()) {
			beanPiles.add(boss.getCards());
		}
		beanPiles.addAll(((Game)game).getRevealedBeans());
		for(List<BeanCard> pile : beanPiles) {
			if(pile.get(0).getType()==card.getType()) {
				pile.add(card);
				return true;
			}
		}
		return false;
	}

	@Override
	protected void innerHandle() throws IllegalActionException {
		// TODO Auto-generated method stub
		
	}
}
