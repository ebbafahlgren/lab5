package Event;

import simulator.Event;
import simulator.EventQueue;
import simulator.State;
import State.StoreState;

import State.*;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Start extends Event {
	private CreateCustomer newCust = new CreateCustomer();

	private Customer customer;
	private Close closing;
	private Arrival arrival;
	private State state;
	private EventQueue eventQueue;
	private Close closeStore;
	private StoreState store;
	private StoreTime generalTime;

	private double arrivalStartTime;

	private double closeTime;
	private double time;
	
	/**
	 * 
	 * @param state starten kommer påverka statusen på butiken till öppen
	 * @param eventQueue starten kommer påverka eventQueue
	 */

	public Start(State state, EventQueue eventQueue, StoreState store, StoreTime generalTime) {
		// super(state, eventQueue);
		this.state = state;
		this.eventQueue = eventQueue;
		this.store = store;
		this.generalTime = generalTime;
		this.closeTime = store.getClosingTime(); //state.getStore().getClo... funkar ej!

		arrivalStartTime = 0;
	}

		/**
		 * doThis. uppdaterar vad som sker när en en starten sker
		 * butiken öppnas efter att butiken varit stängd
		 * Nästa event blir att en kund ankommer till butiken
		 */

		public void doThis () {

			// double arrivalTime = 0;

			while (arrivalStartTime < store.getClosingTime()) {

				arrivalStartTime += generalTime.arrivalTime();
				Arrival arrival = new Arrival(store, state, arrivalStartTime, eventQueue, generalTime);

				eventQueue.SortedSequence(arrival);
				//customer = newCust.createCustomer();

				//arrival.setCustomerStatus(customer);
				//store.addCustomerToArray(customer);
			}
			
			closeStore = new Close(state, store);

			eventQueue.SortedSequence(closeStore);

			state.update();
		}

		//System.out.println("Do this i start");


		
//		while(closeTime > arrivalTime) {
//			arrivalTime = arrivalTime + store.g.arrivalTime();
//			arrival = new Arrival(this.state, this.eventQueue, arrivalTime);
//			eventQueue.SortedSequence(arrival);


	/**
 	 * getTime
 	 * @return time
	 */

	public double getTime() {
		return time;
	}
	/**
	 * Customer
	 * @return null
	 */

	public String getCustomerID() {
		return null;
	}

	/**
	 * Returns the string start
	 * @return String
 	 */

	public String writeOut() {
		return "Start";
	}
}
