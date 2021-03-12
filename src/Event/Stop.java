package Event;

import Simulator.Event;
import Simulator.EventQueue;
import Simulator.State;
import State.Customer;
import State.StoreState;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Stop extends Event {
	private double time;
	private State state;
	private EventQueue eventQueue;
	
	/**
	 * 
	 * @param state när butiken stängs kommer statusen uppdateras till stängd
	 * @param eventQueue när butiken stängs kommer eventkön påverkas. 
	 */
	public Stop(State state, EventQueue eventQueue) {
		super(state, eventQueue);
		this.time = 999;
		this.state = state;
	}
	
	/**
 	 * getTime
 	 * @return time
 	 */
	public double getTime() {
		return time;
	}
	
	/**
	 * doThis uppdaterar statusen till att butiken är stängd
	 */
	@Override
	public void doThis() {
		state.update();
		//state.turnOfSimulator(); // Den ska sluta köra simulator
	}
	
	/**
 	 * getCustomer
	 * @return null	
	 */
	@Override
	public Customer getCustomer() {
		return null;
	}
	
	/**
 	 * String
	 * @return String
 	 */
	@Override
	public String writeOut() {
		return "Stop";
	}
}
