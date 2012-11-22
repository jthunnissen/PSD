package main;


public class BeanCard extends Card {

	/**
	 * @uml.property  name="produce" multiplicity="(0 -1)" dimension="2"
	 */
	private int[][] produces;


	/**
	 * @uml.property  name="type"
	 */
	private EBeanType type;
	
	
	public void setType(EBeanType type) {
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

	/**
	 * Setter of the property <tt>produce</tt>
	 * @param produce  The produces to set.
	 * @uml.property  name="produce"
	 */
	public void setProduce(int[][] produce) {
		produces = produce;
	}

}
