package main;


public class BeanCard extends Card {

	/**
	 * @uml.property  name="produce" multiplicity="(0 -1)" dimension="2"
	 */
	private final int[][] produces;


	/**
	 * @uml.property  name="type"
	 */
	private final EBeanType type;
	
	
	public BeanCard(EBeanType type) {
		super(type.toString(), type.numberOfCards());
		this.produces = type.beanometer();
		this.type = type;
	}

	public EBeanType getType(){
		return type;
	}
	/**
	 * Getter of the property <tt>produce</tt>
	 * @return  Returns the produces.
	 * @uml.property  name="produce"
	 */
	public int[][] getProduce() {
		return produces;
	}

}
