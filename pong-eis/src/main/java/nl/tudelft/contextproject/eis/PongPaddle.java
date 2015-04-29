package nl.tudelft.contextproject.eis;

import java.util.ArrayList;
import java.util.List;

import eis.eis2java.annotation.AsAction;
import eis.eis2java.annotation.AsPercept;
import eishub.pong.Pong;

public class PongPaddle {

	private Pong controller;

	public PongPaddle(Pong controller) {
		this.controller = controller;
	}
	
	@AsPercept(name = "ball", multipleArguments = true)
	public List<Integer> ballPosition() {
		List<Integer> result = new ArrayList<Integer>();
		result.add(controller.getBall().getPositionX());
		result.add(controller.getBall().getPositionY());
		return result;
	}
	
	/*@AsPercept(name = "paddle")
	public int paddle() {
		return controller.getPlayer(1).getPosition();
	}*/
	
	@AsAction(name = "move")
	public void movePaddle(int direction){
		if(direction < 0) controller.getPlayer(1).moveDown();
		if(direction > 0) controller.getPlayer(1).moveDown();
	}
}
