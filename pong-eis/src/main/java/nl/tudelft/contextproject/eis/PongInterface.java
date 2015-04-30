package nl.tudelft.contextproject.eis;

import java.util.Map;

import eis.eis2java.environment.AbstractEnvironment;
import eis.exceptions.EntityException;
import eis.exceptions.ManagementException;
import eis.iilang.Action;
import eis.iilang.EnvironmentState;
import eis.iilang.Parameter;
import eishub.pong.Pong;

@SuppressWarnings("serial")
public class PongInterface extends AbstractEnvironment {
	
	private Pong controller = null;
	
	@Override
	protected boolean isSupportedByEnvironment(Action a) {
		return a.getName().equals("move") && a.getParameters().size() == 1;
	}

	@Override
	protected boolean isSupportedByType(Action a, String type) {
		return isSupportedByEnvironment(a);
	}
	
	
	public void init(Map<String, Parameter> parameters) throws ManagementException {
	    // Prepare the game.
	    reset(parameters);

	    // Try creating and registering an entity.
	    try {
	        registerEntity("pongpaddle", new PongPaddle(controller));
	    } catch (EntityException e) {
	        throw new ManagementException("Could not create an entity", e);
	    }
	}
	
	public void reset(Map<String, Parameter> parameters) throws ManagementException {
		if (controller == null) {
            controller = new Pong();
        }
		setState(EnvironmentState.PAUSED);
	}
	
	@Override
	public void kill() throws ManagementException {
	    //TODO
	    setState(EnvironmentState.KILLED);
	}

	public static void main(String[] args) {
		Pong.main(args);
	}

}
