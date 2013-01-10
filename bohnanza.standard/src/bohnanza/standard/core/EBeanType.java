package bohnanza.standard.core;

import java.util.HashMap;

/**
 * @author Damiaan
 * 
 */
public enum EBeanType implements IBeanType {
	CHILIBEAN {
		@Override
		public final String toString() {
			return "Chili Bean";
		}

		@Override
		public final int numberOfCards() {
			return 18;
		}

		@Override
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
		@Override
		public final String toString() {
			return "Coffee Bean";
		}

		@Override
		public final int numberOfCards() {
			return 24;
		}

		@Override
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
		@Override
		public final String toString() {
			return "Wax Bean";
		}

		@Override
		public final int numberOfCards() {
			return 22;
		}

		@Override
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
		@Override
		public final String toString() {
			return "Blue Bean";
		}

		@Override
		public final int numberOfCards() {
			return 20;
		}

		@Override
		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(10, 4);
			beano.put(8, 3);
			beano.put(6, 2);
			beano.put(4, 1);
			return beano;
		}

	},
	STINKBEAN {
		@Override
		public final String toString() {
			return "Stink Bean";
		}

		@Override
		public final int numberOfCards() {
			return 16;
		}

		@Override
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
		@Override
		public final String toString() {
			return "Green Bean";
		}

		@Override
		public final int numberOfCards() {
			return 14;
		}

		@Override
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
		@Override
		public final String toString() {
			return "Soy Bean";
		}

		@Override
		public final int numberOfCards() {
			return 12;
		}

		@Override
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
		@Override
		public final String toString() {
			return "Black-eyed Bean";
		}

		@Override
		public final int numberOfCards() {
			return 10;
		}

		@Override
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
		@Override
		public final String toString() {
			return "Red Bean";
		}

		@Override
		public final int numberOfCards() {
			return 8;
		}

		@Override
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
		@Override
		public final String toString() {
			return "Garden Bean";
		}

		@Override
		public final int numberOfCards() {
			return 6;
		}

		@Override
		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(3, 3);
			beano.put(2, 2);
			return beano;
		}

	},
	CACOABEAN {
		@Override
		public final String toString() {
			return "Cocoa Bean";
		}

		@Override
		public final int numberOfCards() {
			return 4;
		}

		@Override
		public final HashMap<Integer, Integer> beanometer() {
			HashMap<Integer, Integer> beano = new HashMap<Integer, Integer>();
			beano.put(4, 4);
			beano.put(3, 3);
			beano.put(2, 2);
			return beano;
		}
	}
}
