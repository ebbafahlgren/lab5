package Simulator;

//import TimePack.*;
import State.Customer;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public abstract class Event {

	public abstract String getCustomerID();

	public abstract void doThis();

	public abstract double getTime();

	public abstract String writeOut();
}

//	}
//
//	/**
//	 *
//	 * @param state uppdaterar statusen
//	 * @param eventQueue uppdaterar eventkÃ¶n
//	 */
//	public Event(State state, EventQueue eventQueue) {
//		this.state = state;
//		this.eventQueue = eventQueue;
//
//	/**
//	 * @return getCustomer
//	 */
//	abstract public Customer getCustomer();
//
//	/**
//	 * @return writeOut
//	 **/
//	abstract public String writeOut();
//}
