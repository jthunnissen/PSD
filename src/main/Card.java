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

}
