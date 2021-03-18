package Event;

import Simulator.Event;

import Simulator.EventQueue;
import Simulator.State;
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
		this.state = state;
		this.eventQueue = eventQueue;
		this.store = store;
		this.generalTime = generalTime;

		arrivalStartTime = 0;
	}

		/**
		 * doThis. uppdaterar vad som sker när en en starten sker
		 * butiken öppnas efter att butiken varit stängd
		 * Nästa event blir att en kund ankommer till butiken
		 */

		public void doThis () {
			arrivalStartTime += generalTime.arrivalTime();
			Arrival arrival = new Arrival(store, state, arrivalStartTime, eventQueue, generalTime);
			
			
			eventQueue.SortedSequence(arrival);
			Close closeStore = new Close(state, store);

			eventQueue.SortedSequence(closeStore);

			state.update();
		}
	


	/**
 	 * getTime
 	 * @return time
	 */

	public double getTime() {
		return 0.00;
	}
	/**
	 * returns the string 0
	 * @return 0
	 */

	public String getCustomerID() {
		return "0";
	}

	/**
	 * Returns the string start
	 * @return start
 	 */

	public String writeOut() {
		return "Start";
	}
}
