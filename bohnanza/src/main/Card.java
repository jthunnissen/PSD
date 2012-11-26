package main;

public abstract class Card {

	/**
	 * @uml.property  name="name"
	 */
	private final String name;

	/**
	 * @uml.property  name="numberOfCards"
	 */
	private final int numberOfCards;

	public Card(String name, int numberOfCards) {
		this.name = name;
		this.numberOfCards = numberOfCards;
	}

	/**
	 * Getter of the property <tt>name</tt>
	 * @return  Returns the name.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter of the property <tt>numberOfCards</tt>
	 * @return  Returns the numberOfCards.
	 * @uml.property  name="numberOfCards"
	 */
	public int getNumberOfCards() {
		return numberOfCards;
	}

}
