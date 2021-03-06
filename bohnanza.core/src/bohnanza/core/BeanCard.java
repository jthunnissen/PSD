package bohnanza.core;

import java.util.HashMap;

/**
 * This class respresent a BeanCard
 *
 */
public class BeanCard extends Card {

	/**
	 * Bean-o-meter of this Beancard 
	 * @uml.property name="produce" multiplicity="(0 -1)" dimension="2" 
	 */
	private final HashMap<Integer, Integer> produces;

	/**
	 * Type of this Beancard
	 *  @uml.property name="type" 
	 */
	private final IBeanType type;

	public BeanCard(IBeanType type) {
		super(type.toString(), type.numberOfCards());
		this.produces = type.beanometer();
		this.type = type;
	}

	/**
	 * Getter for the type
	 * @return Type of this Beancard
	 */
	public IBeanType getType() {
		return type;
	}

	/** Getter of the property <tt>produce</tt>
	 * @return Returns the produces.
	 * @uml.property name="produce" */
	public HashMap<Integer, Integer> getProduce() {
		return produces;
	}

	/** Calculates the beanometer.
	 * @param cards Number of cards
	 * @return Score of the beanometer */
	public int getBeanometer(int cards) {
		int nrOfCards = cards;
		int result = 0;
		while(result == 0 && nrOfCards != 0) {
			if(produces.get(nrOfCards) != null) {
				result = produces.get(nrOfCards);
			} else {
				nrOfCards--;
			}
		}
		return result;
	}

}
