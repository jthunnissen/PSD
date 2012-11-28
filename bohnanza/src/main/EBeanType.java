/**
 * 
 */
package main;

import java.util.HashMap;

/**
 * @author Damiaan
 * 
 */
public enum EBeanType {
	CHILIBEAN {
		public final String toString() {
			return "Chili Bean";
		}

		public final int numberOfCards() {
			return 18;
		}

		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(9, 4);
			beano.put(8, 3);
			beano.put(6, 2);
			beano.put(3, 1);
			return beano;
		}
	},
	COFFEEBEANS {
		public final String toString() {
			return "Coffee Bean";
		}

		public final int numberOfCards() {
			return 24;
		}

		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(12, 4);
			beano.put(10, 3);
			beano.put(7, 2);
			beano.put(4, 1);
			return beano;
		}
		
	},
	WAXBEAN {
		public final String toString() {
			return "Wax Bean";
		}

		public final int numberOfCards() {
			return 22;
		}

		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(11, 4);
			beano.put(9, 3);
			beano.put(7, 2);
			beano.put(4, 1);
			return beano;
		}
		
	},
	BLUEBEAN {
		public final String toString() {
			return "Blue Bean";
		}

		public final int numberOfCards() {
			return 20;
		}

		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(10, 4);
			beano.put(8, 3);
			beano.put(6, 2);
			beano.put(4, 1);
			return beano;
		}

	},
	STICKBEAN {
		public final String toString() {
			return "Stick Bean";
		}

		public final int numberOfCards() {
			return 16;
		}
		
		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(8, 4);
			beano.put(7, 3);
			beano.put(5, 2);
			beano.put(3, 1);
			return beano;
		}
	},
	GREENBEAN {
		public final String toString() {
			return "Green Bean";
		}

		public final int numberOfCards() {
			return 14;
		}
		
		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(7, 4);
			beano.put(6, 3);
			beano.put(5, 2);
			beano.put(3, 1);
			return beano;
		}

	},
	SOYBEAN {
		public final String toString() {
			return "Soy Bean";
		}

		public final int numberOfCards() {
			return 12;
		}
		
		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(7, 4);
			beano.put(6, 3);
			beano.put(4, 2);
			beano.put(2, 1);
			return beano;
		}

	},
	BLACKEYEDBEAN {
		public final String toString() {
			return "Black-eyed Bean";
		}

		public final int numberOfCards() {
			return 10;
		}
		
		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(6, 4);
			beano.put(5, 3);
			beano.put(4, 2);
			beano.put(2, 1);
			return beano;
		}

	},
	REDBEAN {
		public final String toString() {
			return "Red Bean";
		}

		public final int numberOfCards() {
			return 8;
		}
		
		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(5, 4);
			beano.put(4, 3);
			beano.put(3, 2);
			beano.put(2, 1);
			return beano;
		}

	},
	GARDENBEAN {
		public final String toString() {
			return "Garden Bean";
		}

		public final int numberOfCards() {
			return 6;
		}
		
		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(3, 3);
			beano.put(2, 2);
			return beano;
		}

	},
	CACOABEAN {
		public final String toString() {
			return "Cocoa Bean";
		}

		public final int numberOfCards() {
			return 4;
		}
		
		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(4, 4);
			beano.put(3, 3);
			beano.put(2, 2);
			return beano;
		}
	},;

	public int numberOfCards() {
		return this.numberOfCards();
	}
	
	public HashMap<Integer, Integer> beanometer() {
		return this.beanometer();
	}
}
