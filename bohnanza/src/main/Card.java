package main;

public abstract class Card {

	/**
	 * @uml.property  name="flipped"
	 */
	private Boolean flipped;

	/** 
	 * Getter of the property <tt>isFlipped</tt>
	 * @return  Returns the isFlipped.
	 * @uml.property  name="flipped"
	 */
	public Boolean isFlipped() {
		return flipped;
	}

	/** 
	 * Setter of the property <tt>isFlipped</tt>
	 * @param isFlipped  The isFlipped to set.
	 * @uml.property  name="flipped"
	 */
	public void setFlipped(Boolean flipped) {
		this.flipped = flipped;
	}

	/**
	 * @uml.property  name="name"
	 */
	private String name;

	/**
	 * Getter of the property <tt>name</tt>
	 * @return  Returns the name.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of the property <tt>name</tt>
	 * @param name  The name to set.
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @uml.property  name="numberOfCards"
	 */
	private int numberOfCards = 0;

	/**
	 * Getter of the property <tt>numberOfCards</tt>
	 * @return  Returns the numberOfCards.
	 * @uml.property  name="numberOfCards"
	 */
	public int getNumberOfCards() {
		return numberOfCards;
	}

	/**
	 * Setter of the property <tt>numberOfCards</tt>
	 * @param numberOfCards  The numberOfCards to set.
	 * @uml.property  name="numberOfCards"
	 */
	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}


}
