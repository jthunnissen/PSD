/**
 * 
 */
package main;

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

		public final int[][] beanometer() {
			return new int[][] { { 1, 2, 3, 4 }, { 3, 6, 8, 9 } };
		}
	},
	COFFEEBEANS {
		public final String toString() {
			return "Coffee Bean";
		}

		public final int numberOfCards() {
			return 24;
		}

		public final int[][] beanometer() {
			return new int[][] { { 1, 2, 3, 4 }, { 4, 7, 10, 12 } };
		}
	},
	WAXBEAN {
		public final String toString() {
			return "Wax Bean";
		}

		public final int numberOfCards() {
			return 22;
		}

		public final int[][] beanometer() {
			return new int[][] { { 1, 2, 3, 4 }, { 4, 7, 9, 11 } };
		}
	},
	BLUEBEAN {
		public final String toString() {
			return "Blue Bean";
		}

		public final int numberOfCards() {
			return 20;
		}

		public final int[][] beanometer() {
			return new int[][] { { 1, 2, 3, 4 }, { 4, 6, 8, 10 } };
		}
	},
	STICKBEAN {
		public final String toString() {
			return "Stick Bean";
		}

		public final int numberOfCards() {
			return 16;
		}

		public final int[][] beanometer() {
			return new int[][] { { 1, 2, 3, 4 }, { 3, 5, 7, 8 } };
		}
	},
	GREENBEAN {
		public final String toString() {
			return "Green Bean";
		}

		public final int numberOfCards() {
			return 14;
		}

		public final int[][] beanometer() {
			return new int[][] { { 1, 2, 3, 4 }, { 3, 5, 6, 7 } };
		}
	},
	SOYBEAN {
		public final String toString() {
			return "Soy Bean";
		}

		public final int numberOfCards() {
			return 12;
		}

		public final int[][] beanometer() {
			return new int[][] { { 1, 2, 3, 4 }, { 2, 4, 6, 7 } };
		}
	},
	BLACKEYEDBEAN {
		public final String toString() {
			return "Black-eyed Bean";
		}

		public final int numberOfCards() {
			return 10;
		}

		public final int[][] beanometer() {
			return new int[][] { { 1, 2, 3, 4 }, { 2, 4, 5, 6 } };
		}
	},
	REDBEAN {
		public final String toString() {
			return "Red Bean";
		}

		public final int numberOfCards() {
			return 8;
		}

		public final int[][] beanometer() {
			return new int[][] { { 1, 2, 3, 4 }, { 2, 3, 4, 5 } };
		}
	},
	GARDENBEAN {
		public final String toString() {
			return "Garden Bean";
		}

		public final int numberOfCards() {
			return 6;
		}

		public final int[][] beanometer() {
			return new int[][] { { 2, 3 }, { 2, 3 } };
		}
	},
	CACOABEAN {
		public final String toString() {
			return "Cocoa Bean";
		}

		public final int numberOfCards() {
			return 4;
		}

		public final int[][] beanometer() {
			return new int[][] { { 2, 3, 4 }, { 2, 3, 4 } };
		}
	},

}
