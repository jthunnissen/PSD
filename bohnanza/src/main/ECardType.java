/**
 * 
 */
package main;

/**
 * @author Damiaan
 *
 */
public enum ECardType {
	CHILIBEAN  {
	    public String toString() {
	        return "Chili Bean";
	    }
	    public int numberOfCards() {
	    	return 18;
	    }
	},
	COFFEEBEANS {
		 public String toString() {
		        return "Coffee Bean";
		    }
		    public int numberOfCards() {
		    	return 24;
		    }
	},
	WAXBEAN {
		public String toString() {
	        return "Wax Bean";
	    }
	    public int numberOfCards() {
	    	return 22;
	    }
	},
	BLUEBEAN {
		public String toString() {
	        return "Blue Bean";
	    }
	    public int numberOfCards() {
	    	return 20;
	    }
	},
	STICKBEAN {
		public String toString() {
	        return "Stick Bean";
	    }
	    public int numberOfCards() {
	    	return 16;
	    }
	},
	GREENBEAN {
		public String toString() {
	        return "Green Bean";
	    }
	    public int numberOfCards() {
	    	return 14;
	    }
	},
	SOYBEAN {
		public String toString() {
	        return "Soy Bean";
	    }
	    public int numberOfCards() {
	    	return 12;
	    }
	},
	BLACKEYEDBEAN {
		public String toString() {
	        return "Black-eyed Bean";
	    }
	    public int numberOfCards() {
	    	return 10;
	    }
	},
	REDBEAN {
		public String toString() {
	        return "Red Bean";
	    }
	    public int numberOfCards() {
	    	return 8;
	    }
	},
	GARDENBEAN {
		public String toString() {
	        return "Garden Bean";
	    }
	    public int numberOfCards() {
	    	return 6;
	    }
	},
	CACOABEAN {
		public String toString() {
	        return "Cocoa Bean";
	    }
	    public int numberOfCards() {
	    	return 4;
	    }
	},

}
