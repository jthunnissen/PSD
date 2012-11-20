package main;


public class BeanType {

	/** 
	 * @uml.property name="nrOfCards" readOnly="true"
	 */
	private String nrOfCards;

	/** 
	 * Getter of the property <tt>nrOfCards</tt>
	 * @return  Returns the nrOfCards.
	 * @uml.property  name="nrOfCards"
	 */
	public String getNrOfCards() {
		return nrOfCards;
	}

	/** 
	 * @uml.property name="name" readOnly="true"
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
	 * @uml.property name="picture" readOnly="true"
	 */
	private String picture;

	/** 
	 * Getter of the property <tt>picture</tt>
	 * @return  Returns the picture.
	 * @uml.property  name="picture"
	 */
	public String getPicture() {
		return picture;
	}

	/** 
	 * @uml.property name="beanometer"
	 * @uml.associationEnd readOnly="true" multiplicity="(1 1)" inverse="beanType:main.Beanometer"
	 */
	private Beanometer beanometer = new Beanometer();

	/** 
	 * Getter of the property <tt>beanometer</tt>
	 * @return  Returns the beanometer.
	 * @uml.property  name="beanometer"
	 */
	public Beanometer getBeanometer() {
		return beanometer;
	}

}
