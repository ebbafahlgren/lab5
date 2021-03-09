package Simulator;

//import TimePack.*;
import State.Customer;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public abstract class Event{
	
	protected State state;
	
	EventQueue eventQueue = new EventQueue();
	
	public Event(State state, EventQueue eventQueue) {
		System.out.println("Skapar event!");
		
		this.state = state;
		this.eventQueue = eventQueue;	
	}
	
	abstract public void doThis();
	abstract public double getTime();
	abstract public Customer getCustomer();
	abstract public String writeOut();
}
