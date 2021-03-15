package Event;

import Simulator.*;

import Simulator.*;
import State.CreateCustomer.customerStatus;
import State.Customer;
import State.StoreState;
import Simulator.State;
import Time.*;
import State.StoreTime;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Arrival extends Event {
	private Pick pickEvent;
	private double arrivalTime;
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	private StoreTime generalTime;
	private StoreState store;
	
	/** 
	 * @param state kommer ange statusen som ankomst
	 * @param eventQueue ankomsten kommer påverka eventkön
	 */
	
	public Arrival(StoreState store, State state, double arrivalTime, EventQueue eventQueue, StoreTime generalTime) {
	//	super(state, eventQueue);

		this.store = store;
		this.eventQueue = eventQueue;
		this.state = state;
		this.generalTime = generalTime;
		this.arrivalTime = arrivalTime;


	}
	/** 
	 * doThis. uppdaterar vad som sker när en kund ankommer butiken. 
	 * Antal kunder i butiken uppdateras
	 * Nästa event kommer vara att kunden plockar varor. 
	 */


	public void doThis() {

		store.updateTime(arrivalTime);
		store.updateTotQueueTime(store.getCurrentEventTime(), store.getLastEventTime());
		store.updateTotRegisterTime(store.getCurrentEventTime(), store.getLastEventTime());
		//store.addCustomerToArray(customer);
		state.update();

		if (store.getCurrentCustomers() < store.getMaxCustomers() && store.isStoreOpen()) {
			// State state, StoreState store, EventQueue eventQueue, Customer customer, double time, StoreTime generalTime
			Pick pick = new Pick(state, store, eventQueue, customer, timePick(), generalTime);
			eventQueue.SortedSequence(pick);

		} else {
			if (store.isStoreOpen()) {
				store.addCustomerNotPayed();
			}
		}

		if (store.getCurrentCustomers() < store.getMaxCustomers() && store.isStoreOpen()) {
			store.addCurrentCustomers();
		}
	}


//		this.store.addCustomerToArray(customer);
//
//		//System.out.print(customer.getState());
//		if (customer.getState() == customerStatus.inStore) {
//
//
//
//			double a = generalTime.timePick();
//
//			double pickTime = this.time + a;
//			// State state, EventQueue eventQueue, Customer customer, double time, StoreTime generalTime
//			pickEvent = new Pick(state, eventQueue, customer, time, generalTime);
//			eventQueue.SortedSequence(pickEvent);
//
//			//System.out.println(eventQueue + " = vad som finns i eventQueue");
//		}
//	}

	public void setCustomerStatus(Customer c) {
		customer = c;
	}
	/**
	 * getTime
	 */

	public double getTime() {
		return arrivalTime;
	}

	public double timePick() {
		return arrivalTime + generalTime.timePick();
	}

	public Customer getStore() {

		return customer;
	}
	/**
	 * getCustomer
	 */

	public String getCustomerID() {
		return Integer.toString(customer.getID());
	}
	
	 /**
	 * writeOut
	 */

	public String writeOut() {
		return "Arrival";
	}

}
