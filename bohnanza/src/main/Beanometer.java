package main;


public class Beanometer {

	/** 
	 * @uml.property name="produce" readOnly="true" multiplicity="(0 -1)" dimension="1"
	 */
	private int[] produces;

	/** 
	 * Getter of the property <tt>produce</tt>
	 * @return  Returns the produces.
	 * @uml.property  name="produce"
	 */
	public int[] getProduce() {
		return produces;
	}

}
