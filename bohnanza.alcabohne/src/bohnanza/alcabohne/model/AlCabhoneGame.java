package bohnanza.alcabohne.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import bohnanza.core.*;

public class AlCabhoneGame extends GameBase {

	private final String[] mobbossNames = { "Al Cabhone", "Don Corlebohne", "Joe Bohnano" };
	private List<MobBoss> mobBosses = new ArrayList<MobBoss>();
	private List<List<BeanCard>> revealedBeans = new ArrayList<List<BeanCard>>();

	public List<MobBoss> getMobbosses() {
		return mobBosses;
	}

	public List<List<BeanCard>> getRevealedBeans() {
		return revealedBeans;
	}

	public AlCabhoneGame() {
		super(GameFactory.getInstance());
	}

	@Override
	public Player addPlayer(Player player) throws IllegalActionException {
		assert player instanceof NormalPlayer;
		if(Arrays.asList(mobbossNames).contains(player.getName()))
			throw new IllegalActionException(player.getName() + " is a reserved name");
		return super.addPlayer(player);
	}

	@Override
	public void start() {
		switch(players.size()) {
		case 1:

			break;
		case 2:
			try {
				mobBosses.add(new MobBoss(mobbossNames[0]));
				mobBosses.add(new MobBoss(mobbossNames[1]));
			} catch(Exception e) {
				e.printStackTrace();
			}
			BeanCard card = (BeanCard) drawCard();
			List<BeanCard> cards = mobBosses.get(0).getCards();
			do {
				mobBosses.get(0).addCard(card);
				card = (BeanCard) drawCard();
			} while(cards.get(cards.size() - 1).getType() == card.getType());
			mobBosses.get(1).addCard(card);
		}
	}

	public Card drawDiscardedCard() throws IllegalActionException {
		if(discardPile.isEmpty())
			throw new IllegalActionException("Can't draw from empty discard pile");
		return discardPile.remove(0);
	}
}
