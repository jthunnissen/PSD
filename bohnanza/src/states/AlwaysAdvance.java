package states;

public class AlwaysAdvance extends StateAdvance {

	@Override
	public boolean canAdvance() {
		return true;
	}

}
