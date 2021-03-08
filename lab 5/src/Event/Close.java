package Event;

import Simulator.*;
import State.Customer;
import State.StoreState;
import Simulator.State;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Close extends Event{

	private double time;
	private State state;


	public Close(State state, EventQueue eventQueue, double time) {
		//Skickar in state och eventQueue i Event
		super(state, eventQueue);
		this.time = time;
	}

	@Override
	public void doThis() {
		state.update(this);
		state.getStore().setStoreOpen(false);
	}

	@Override
	public double getTime() {
		return time;
	}

	@Override
	public Customer getCustomer() {
		return null;
	}

	@Override
	public String writeOut() {
		return "Closing store!";
	}

}
