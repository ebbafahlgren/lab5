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
	
	/**
	 * 
	 * @param state uppdaterar statusen
	 * @param eventQueue uppdaterar eventkön
	 */
	public Event(State state, EventQueue eventQueue) {
		//System.out.println("Skapar event!");
		
		this.state = state;
		this.eventQueue = eventQueue;	
	}
	
	/**
	 * ropar på doThis
	 */
	abstract public void doThis();
	/**
	 * @return getTime
	 */
	abstract public double getTime();
	/**  
	 * @return getCustomer
	 */
	abstract public Customer getCustomer();
	/**
	 * @return writeOut
	 **/
	abstract public String writeOut();
}
