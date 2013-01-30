package bohnanza.core;
import java.text.MessageFormat;
import java.util.List;

public class BeanField extends Field<BeanCard> {

	@Override
	public void addCard(BeanCard card) throws IllegalActionException {
		if(getSize() > 0 && card.getType() != getBeanType()) 
			throw new IllegalActionException(MessageFormat.format(
				"Tried to add card of type {0} but requires {1}", 
				card.getType(), 
				getBeanType()));
		super.addCard(card);
	}

	@Override
	public List<BeanCard> harvest() {
		// TODO Handle here points if possible
		return super.harvest();
	}

	/** Checks if the card that should be added is if the same type as the other
	 * cards in this field.
	 * @param card Card that should be added.
	 * @return true if card is of the same type of the other card on this field. */
	public boolean checkCard(BeanCard card) {
		if(cards.size() > 0) {
			if(getBeanType() != card.getType())
				return false;
		}
		return true;
	}

	public IBeanType getBeanType() {
		return (getSize() > 0 ? cards.get(0).getType() : null);
	}

}
