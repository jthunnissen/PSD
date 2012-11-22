package main;


public class BeanCard extends Card {

	/**
	 * @uml.property  name="produce" multiplicity="(0 -1)" dimension="2"
	 */
	private int[][] produces;

	/**
	 * Getter of the property <tt>produce</tt>
	 * @return  Returns the produces.
	 * @uml.property  name="produce"
	 */
	public int[][] getProduce() {
		return produces;
	}

	/**
	 * Setter of the property <tt>produce</tt>
	 * @param produce  The produces to set.
	 * @uml.property  name="produce"
	 */
	public void setProduce(int[][] produce) {
		produces = produce;
	}

}
