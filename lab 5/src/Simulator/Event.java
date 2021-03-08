package Simulator;

//import TimePack.*;
import State.Customer;

public abstract class Event{
	
	State state = new State();
	EventQueue eventQueue = new EventQueue();
	
	public Event(State state, EventQueue eventQueue) {
		this.state = state;
		this.eventQueue = eventQueue;	
	}
	
	abstract public void doThis();
	abstract public double getTime();
	abstract public Customer getCustomer();
	abstract public String writeOut();
}

