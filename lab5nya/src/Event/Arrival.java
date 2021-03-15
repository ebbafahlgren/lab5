package Event;

import simulator.*;

import simulator.*;
import State.CreateCustomer;
import State.CreateCustomer.customerStatus;
import State.Customer;
import State.StoreState;
import simulator.State;
import Time.*;
import State.StoreTime;

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
	private StoreTime generalTime;
	private StoreState store;
	
	/** 
	 * @param state kommer ange statusen som ankomst
	 * @param eventQueue ankomsten kommer påverka eventkön
	 */
	
	public Arrival(StoreState store, State state, double time, EventQueue eventQueue, StoreTime generalTime) {
	//	super(state, eventQueue);

		this.store = store;
		this.eventQueue = eventQueue;
		this.state = state;
		this.generalTime = generalTime;
		this.time = time;
		
		customer = store.createCustomer();
	}
	
	/** 
	 * doThis. uppdaterar vad som sker när en kund ankommer butiken. 
	 * Antal kunder i butiken uppdateras
	 * Nästa event kommer vara att kunden plockar varor. 
	 */

	@Override
	public void doThis() {

		store.updateTime(time);
		store.updateTotQueueTime(store.getCurrentEventTime(), store.getLastEventTime());
		store.updateTotRegisterTime(store.getCurrentEventTime(), store.getLastEventTime());

		state.update();
		store.addCustomer(customer);
		//System.out.println("vill g� in i butik");
		
		if (customer.getState() == customerStatus.inStore) {
			
			//System.out.println(store.getCurrentCustomers() + " antal i butik nu");
			// State state, StoreState store, EventQueue eventQueue, Customer customer, double time, StoreTime generalTime
			Pick pick = new Pick(state, store, eventQueue, customer, getPickTime(), generalTime);
			store.addCurrentCustomers();

			eventQueue.SortedSequence(pick);

		}
		
		//System.out.println(store.getCustomerNotPayed());

//		if (store.getCurrentCustomers() < store.getMaxCustomers() && store.isStoreOpen()) {
//			store.addCurrentCustomers();
//		}
	}


//		this.store.addCustomerToArray(customer);
//
//		//System.out.print(customer.getState());
//		if (customer.getState() == customerStatus.inStore) {
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
		return time;
	}

	public double getPickTime() {
		return time + generalTime.timePick();
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
