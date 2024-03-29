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
 * 
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

/**
 * The class arrival class describes how an arrival event works
 */

public class Arrival extends Event {
	private Pick pickEvent;
	private double arrivalTime;
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	private StoreTime generalTime;
	private StoreState store;
	
	private double arrivalTimeNew;
	/** 
	 * @param state kommer ange statusen som ankomst
	 * @param eventQueue ankomsten påverkar eventkön
	 * @param arrivalTime ankomsttiden
	 * @param generalTime den generella butikstiden
	 */
	public Arrival(StoreState store, State state, double arrivalTime, EventQueue eventQueue, StoreTime generalTime) {

		this.store = store;
		this.eventQueue = eventQueue;
		this.state = state;
		this.generalTime = generalTime;
		this.arrivalTime = arrivalTime;

		customer = store.createCustomer();
	}
	
	/** 
	 * doThis. uppdaterar vad som sker nÃ¤r en kund ankommer butiken. 
	 * Antal kunder i butiken uppdateras
	 * NÃ¤sta event kommer vara att kunden plockar varor. 
	 */
	public void doThis() {

		store.updateTime(arrivalTime);
		store.updateTotQueueTime(store.getCurrentEventTime(), store.getLastEventTime());
		store.updateTotRegisterTime(store.getCurrentEventTime(), store.getLastEventTime());
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
		
		if (arrivalTimeNew < store.getClosingTime() && store.isStoreOpen()) {

			arrivalTimeNew = arrivalTime + generalTime.arrivalTime();
			Arrival arrival = new Arrival(store, state, arrivalTimeNew, eventQueue, generalTime);

			eventQueue.SortedSequence(arrival);
		}
	}

	/**
	 * @param c sätter kundens status
	 */
	public void setCustomerStatus(Customer c) {
		customer = c;
	}
	/**
	 * getTime
	 * @return double  arrivaltime
	 */

	public double getTime() {
		return arrivalTime;
	}
	/**
	 * getTime to pick items
	 * @return picktime
	 */

	public double timePick() {
		return arrivalTime + generalTime.timePick();
	}
	
	/**
	 * getCustomerID
	 * @return CustomerID
	 */
	public String getCustomerID() {
		return Integer.toString(customer.getID());
	}
	
	 /**
	 * writeOut
	 * @return  Arrival
	 *
	 */

	public String writeOut() {
		return "Arrival";
	}

}
