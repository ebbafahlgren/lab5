package Event;

import Simulator.*;
import State.Customer;
import State.FIFO;
import State.StoreState;
import State.StoreTime;
/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Pay extends Event {

	private double time;
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	private StoreState store;
	private StoreTime generalTime;
	
	/**
	 * @param state kommer ange statusen som betalning
	 * @param eventQueue betalningen kommer påverka eventkön
	 * @param time betalningen kommer påverka tiden
	 * @param c kunden kommer göra en betalning
	 */
	public Pay(State state, EventQueue eventQueue, double time, Customer c, StoreState store, StoreTime generalTime) {
		this.time = time;
	   	customer = c;
	    this.state = state;
	    this.eventQueue = eventQueue;
	    this.store = store;
	    this.generalTime = generalTime;
	}
	
	 /** 
	 * doThis. uppdaterar vad som sker när en en betalning sker 
	 * Under tiden betalningen sker kommer en kassa markeras som upptagen
	 * När en betlaning skett och kunden lämnar kommer antal personer i butiken uppdateras.
	 */

	public void doThis() {

		store.updateTime(time);
		store.updateTotRegisterTime(store.getCurrentEventTime(), store.getLastEventTime());
		store.updateTotQueueTime(store.getCurrentEventTime(), store.getLastEventTime());

		state.update();
	    store.addAvailableRegisters();  

	    
	    if (!store.getTheFIFO().isEmpty()) { //om FIFOkön inte är tom
			Customer firstInLine = store.getTheFIFO().firstInLine();

			double payTime = this.time + generalTime.timePay();
			Pay paymentEvent = new Pay(state, eventQueue, payTime, firstInLine, store, generalTime);
			eventQueue.SortedSequence(paymentEvent);
			store.removeAvailableRegisters();
		}
			if (store.getTheFIFO().size() > 0) {
				store.getTheFIFO().removeFirst();
			}
			store.removeCurrentCustomer();
			store.addCustomerPayed();
	}
	
	/**
	 * getTime
	 * @return time
	 */
	@Override
	public double getTime() {

		return time;
	}
	/**
	* getTotaltime
	* @return totaltime
	*/
	
	public double totalTime() {

		return getTime() + time;
	}

	/**
	 * getCustomer
	 * @return customer
	 */

	public String getCustomerID() {
		return Integer.toString(customer.getID());
	}
	
	/**
	 * writeOut
	 * @return "paying"
	 */

	public String writeOut() {
		return "Paying";
	}

}
