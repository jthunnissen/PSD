/**
 * 
 */
package main;

/**
 * @author Damiaan
 *
 */
public enum EBeanType {
	CHILIBEAN  {
	    public String toString() {
	        return "Chili Bean";
	    }
	    public int numberOfCards() {
	    	return 18;
	    }
	    public int[][] beanometer(){
	    	return new int[][]
	    	{{1,2,3,4},
	    	 {3,6,8,9}		
	    	};
	    }
	},
	COFFEEBEANS {
		 public String toString() {
		        return "Coffee Bean";
		    }
		    public int numberOfCards() {
		    	return 24;
		    }
		    public int[][] beanometer(){
		    	return new int[][]
		    	{{1,2,3,4},
		    	 {4,7,10,12}		
		    	};
		    }
	},
	WAXBEAN {
		public String toString() {
	        return "Wax Bean";
	    }
	    public int numberOfCards() {
	    	return 22;
	    }
	    public int[][] beanometer(){
	    	return new int[][]
	    	{{1,2,3,4},
	    	 {4,7,9,11}		
	    	};
	    }
	},
	BLUEBEAN {
		public String toString() {
	        return "Blue Bean";
	    }
	    public int numberOfCards() {
	    	return 20;
	    }
	    public int[][] beanometer(){
	    	return new int[][]
	    	{{1,2,3,4},
	    	 {4,6,8,10}		
	    	};
	    }
	},
	STICKBEAN {
		public String toString() {
	        return "Stick Bean";
	    }
	    public int numberOfCards() {
	    	return 16;
	    }
	    public int[][] beanometer(){
	    	return new int[][]
	    	{{1,2,3,4},
	    	 {3,5,7,8}		
	    	};
	    }
	},
	GREENBEAN {
		public String toString() {
	        return "Green Bean";
	    }
	    public int numberOfCards() {
	    	return 14;
	    }
	    public int[][] beanometer(){
	    	return new int[][]
	    	{{1,2,3,4},
	    	 {3,5,6,7}		
	    	};
	    }
	},
	SOYBEAN {
		public String toString() {
	        return "Soy Bean";
	    }
	    public int numberOfCards() {
	    	return 12;
	    }
	    public int[][] beanometer(){
	    	return new int[][]
	    	{{1,2,3,4},
	    	 {2,4,6,7}		
	    	};
	    }
	},
	BLACKEYEDBEAN {
		public String toString() {
	        return "Black-eyed Bean";
	    }
	    public int numberOfCards() {
	    	return 10;
	    }
	    public int[][] beanometer(){
	    	return new int[][]
	    	{{1,2,3,4},
	    	 {2,4,5,6}		
	    	};
	    }
	},
	REDBEAN {
		public String toString() {
	        return "Red Bean";
	    }
	    public int numberOfCards() {
	    	return 8;
	    }
	    public int[][] beanometer(){
	    	return new int[][]
	    	{{1,2,3,4},
	    	 {2,3,4,5}		
	    	};
	    }
	},
	GARDENBEAN {
		public String toString() {
	        return "Garden Bean";
	    }
	    public int numberOfCards() {
	    	return 6;
	    }
	    public int[][] beanometer(){
	    	return new int[][]
	    	{{2,3},
	    	 {2,3}		
	    	};
	    }
	},
	CACOABEAN {
		public String toString() {
	        return "Cocoa Bean";
	    }
	    public int numberOfCards() {
	    	return 4;
	    }
	    public int[][] beanometer(){
	    	return new int[][]
	    	{{2,3,4},
	    	 {2,3,4}		
	    	};
	    }
	},

}
