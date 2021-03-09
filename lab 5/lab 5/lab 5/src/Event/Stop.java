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

	public Stop(State state, EventQueue eventQueue) {
		super(state, eventQueue);
		this.time = 999;
	}

	public double getTime() {
		return time;
	}

	@Override
	public void doThis() {
		state.update(this);
		state.stopSimulation(); // Den ska sluta köra simulator
	}

	@Override
	public Customer getCustomer() {
		return null;
	}

	@Override
	@Override
	public String writeOut() {
		return "Stop";
	}
}