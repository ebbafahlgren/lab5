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

/**
 * The class stop describes how a stop event works
 */

public class Stop extends Event {
	private double time;
	private State state;
	private EventQueue eventQueue;
	private StoreState store;
	/**
	 * 
	 * @param state när butiken stängs kommer statusen uppdateras till stängd
	 * @param store när butiken stängs kommer eventkön påverkas.
	 */
	public Stop(State state, StoreState store) {
	//	super(state, eventQueue);
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
		state.turnOfSimulation();
	}
	
	/**
 	 * returns getcustomerID
	 * @return 999	
	 */
	public String getCustomerID() {
		return "999";
	}

	
	/**
 	 * String
	 * @return Stop
 	 */
	@Override
	public String writeOut() {
		return "Stop";
	}
}
