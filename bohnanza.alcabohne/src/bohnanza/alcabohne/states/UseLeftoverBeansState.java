package bohnanza.alcabohne.states;

import bohnanza.alcabohne.model.AlCabhoneGame;
import bohnanza.core.Action;
import bohnanza.core.GameBase;
import bohnanza.core.TurnState;

public class UseLeftoverBeansState extends TurnState<AlCabhoneGame> {

	public UseLeftoverBeansState(AlCabhoneGame context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean handled(Action<? extends GameBase> action) {
		// TODO Auto-generated method stub
		return false;
	}

}
