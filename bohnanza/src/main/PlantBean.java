package main;


public class PlantBean extends Action {
	
	@Override
	public boolean handle(Player player, Object[] args) {
		int fieldnr = (int) args[0];
		return player.plantBean(fieldnr);
	}

}
