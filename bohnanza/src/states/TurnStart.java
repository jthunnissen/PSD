package states;
import java.util.ArrayList;
import java.util.List;
import main.*;
import actions.*;

public class TurnStart extends TurnState {
	
	public TurnStart(Game context) {
		super(context);
		addAction(context.getActivePlayer(), NextPlayer.class);
	}

	@Override
	protected boolean handled(Action action) {
		return action instanceof NextPlayer;
	}

}
