package Event;

import Simulator.Event;
import Simulator.EventQueue;
import Simulator.State;
import State.*;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Start extends Event {
	private Close closing;
	private Arrival arrival;
	private State state;
	private EventQueue eventQueue;
	private Close closeStore;
	private StoreState store;

	private double closeTime;
	private double time;
	private StoreTime generalTime;

	/**
	 * 
	 * @param state starten kommer påverka statusen på butiken till öppen
	 * @param eventQueue starten kommer påverka eventQueue
	 */

	public Start(StoreState store, EventQueue eventQueue) {
		
		super(store, eventQueue);
		
		//System.out.println(state.getClosingTime() + " tid för stängning");
		
		this.time = 0d; 
		this.closeTime = store.getClosingTime(); //state.getStore().getClo... funkar ej!
		this.store = store; //kanske ha med denna
		this.eventQueue = eventQueue;
		generalTime = new StoreTime(store);
	}
	
	 /** 
	 * doThis. uppdaterar vad som sker när en en starten sker
	 * butiken öppnas efter att butiken varit stängd
	 * Nästa event blir att en kund ankommer till butiken
	 */
	@Override
	public void doThis() {
		//System.out.println("Do this i start");
		
		store.update(this);

		store.setStoreOpen(true);

		Start start = new Start(store, eventQueue);

		closeStore = new Close(store, eventQueue, closeTime);

		eventQueue.SortedSequence(closeStore);

		double arrivalTime = 0;
		System.out.println("heeeeeeeeej " + arrivalTime);
		while(closeTime > arrivalTime) {
			
			arrivalTime = arrivalTime + generalTime.arrivalTime();
			arrival = new Arrival(store, this.eventQueue, arrivalTime);
			eventQueue.SortedSequence(arrival);
		}


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
	 * Customer
	 * @retutn null
	 */
	@Override
	public Customer getCustomer() {
		return null;
	}

	/**
	 * String
	 * @return String
 	 */
	@Override
	public String writeOut() {
		return "Start";
	}
}
