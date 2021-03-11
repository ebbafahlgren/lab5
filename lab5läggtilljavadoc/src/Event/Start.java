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
	private Close closing;
	private Arrival arrival;
	private State state;
	private EventQueue eventQueue;
	private Close closeStore;
	private StoreState store;

	private double closeTime;
	private double time;
	
	/**
	 * 
	 * @param state starten kommer påverka statusen på butiken till öppen
	 * @param eventQueue starten kommer påverka eventQueue
	 */

	public Start(State state, EventQueue eventQueue) {
		
		super(state, eventQueue);
		
		//System.out.println(state.getClosingTime() + " tid för stängning");
		
		this.time = 0d; 
		this.closeTime = state.getClosingTime(); //state.getStore().getClo... funkar ej!
		this.state = state; //kanske ha med denna
		this.eventQueue = eventQueue;
	}
	
	 /** 
	 * doThis. uppdaterar vad som sker när en en starten sker
	 * butiken öppnas efter att butiken varit stängd
	 * Nästa event blir att en kund ankommer till butiken
	 */
	@Override
	public void doThis() {
		//System.out.println("Do this i start");
		
		state.update(this);

		state.getStore().setStoreOpen(true);

		Start start = new Start(this.state, eventQueue);

		closeStore = new Close(this.state, eventQueue, closeTime);

		eventQueue.SortedSequence(closeStore);

		double arrivalTime = 0;
		
		while(closeTime > arrivalTime) {
			arrivalTime = arrivalTime + state.arrivalTime();
			arrival = new Arrival(this.state, this.eventQueue, arrivalTime);
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
