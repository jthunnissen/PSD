package bohnanza.alcabohne.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import bohnanza.core.BeanCard;
import bohnanza.core.Card;
import bohnanza.core.GameBase;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;

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
		super(GameFactory.getInstance(), new AlCabohneGameRules());
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
				mobBosses.add(new MobBoss(mobbossNames[0], 3));
				mobBosses.add(new MobBoss(mobbossNames[1], 2));
			} catch(Exception e) {
				e.printStackTrace();
			}
			BeanCard card = (BeanCard) drawCard();
			List<BeanCard> cards = mobBosses.get(0).getCards();
			do {
				try {
					mobBosses.get(0).addCard(card);
				} catch(IllegalActionException e) {
				}
				card = (BeanCard) drawCard();
			} while(cards.get(cards.size() - 1).getType() == card.getType());
			try {
				mobBosses.get(1).addCard(card);
			} catch(IllegalActionException e) {
			}
		}
	}

	public Card drawDiscardedCard() throws IllegalActionException {
		if(discardPile.isEmpty())
			throw new IllegalActionException("Can't draw from empty discard pile");
		return discardPile.remove(0);
	}
}
