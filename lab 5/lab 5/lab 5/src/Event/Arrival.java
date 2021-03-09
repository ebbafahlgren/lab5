package Event;

import Simulator.*;

import Simulator.*;
import State.CreateCustomer.customerStatus;
import State.Customer;
import State.StoreState;
import Simulator.State;
import Time.*;
/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Arrival extends Event {
	private Pick pickEvent;
	private double time;
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	
	//private pickTime picktime = new pickTime();

	//private Pick pickevent = new Pick(state, eventQueue, customer, time);
	
	public Arrival(State state, EventQueue eventqueue, double time) {
		super(state, eventqueue);
		this.time = time;
		customer = this.state.getStore().createCustomer();

	}

	@Override
	public void doThis() {
		state.update(this);
		this.state.getStore().createCustomer();
		if (customer.getState() == customerStatus.inStore) {
			double pickTime = this.time + state.timePick(); //
			pickEvent = new Pick(this.state, this.eventQueue, customer, pickTime);
			eventQueue.SortedSequence(pickEvent);
		}
		
	}

	@Override
	public double getTime() {
		return time;
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String writeOut() {
		// TODO Auto-generated method stub
		return null;
	}

}