package Simulator;

//import TimePack.*;
import State.Customer;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

/**
 * This abstract class Event is inherited to all the different events in a supermarket
 */

public abstract class Event {

	/** 
	* @return getCustomerID
	* getCustomerID should return the numeric ID of the customer
	*/
	public abstract String getCustomerID();
	
	/** 
	* doThis make sure everything happens
	*/

	public abstract void doThis();
	
	/** 
	* @return getTime
	* getTime should return the time for the events
	*/

	public abstract double getTime();
	
	/** 
	* @return writeOut
	* writeOut should write out the names of each events
	*/

	public abstract String writeOut();
}

