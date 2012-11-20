package main;


public class BeanCard extends Card {

	/** 
	 * @uml.property name="type"
	 * @uml.associationEnd readOnly="true" inverse="beanCard:main.BeanType"
	 */
	private BeanType type;

	/** 
	 * Getter of the property <tt>type</tt>
	 * @return  Returns the type.
	 * @uml.property  name="type"
	 */
	public BeanType getType() {
		return type;
	}

}
