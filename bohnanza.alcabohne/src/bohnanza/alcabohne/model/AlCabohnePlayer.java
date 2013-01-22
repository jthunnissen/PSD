package bohnanza.alcabohne.model;

import bohnanza.standard.model.BohnanzaPlayer;

public abstract class AlCabohnePlayer extends BohnanzaPlayer {

	private boolean isPlayable;
	
	protected AlCabohnePlayer(String name) {
		this(name, true);
	}
	
	protected AlCabohnePlayer(String name, boolean isPlayable) {
		super(name);
		this.isPlayable = isPlayable;
	}
	
	public boolean isPlayerable() {
		return isPlayable;
	}

}
