package Event;
import Simulator.Event;
import Simulator.EventQueue;
import Simulator.State;
import State.Customer;
public class Pick extends Event{

	private Customer customer;
	private double time;
	private State state;
	
	private Pay payEvent;
	
	public Pick(State state, EventQueue eventQueue, Customer customer, double time) {
		super(state, eventQueue);
		
		this.customer = customer;
		this.time = time;
	}

	@Override
	public void doThis() {
		state.update(this);
	}
	
	@Override
	public double getTime() {
		return time;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	@Override
	public String writeOut() {
		return "Pickevent";
	}
	
}

