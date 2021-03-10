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

	public Start(State state, EventQueue eventQueue) {
		
		super(state, eventQueue);
		
		//System.out.println(state.getClosingTime() + " tid för stängning");
		
		this.time = 0d; /// ????
		this.closeTime = state.getClosingTime(); //state.getStore().getClo... funkar ej!
		this.state = state; //kanske ha med denna
		this.eventQueue = eventQueue;
	}

	@Override
	public void doThis() {
		//System.out.println("Do this i start");
		
		//state.update(this);

		state.getStore().setStoreOpen(true);
		//state.getStore();
		//System.out.println(state.getStore() + " butiken");
		//store.setStoreOpen(true);
		//storeTest.get
		
		closeStore = new Close(this.state, eventQueue, closeTime);

		//System.out.println("Sortera in closing");
		eventQueue.SortedSequence(closeStore);

		double arrivalTime = 0;
		
		while(closeTime > arrivalTime) {
			arrivalTime = arrivalTime + state.arrivalTime();
			arrival = new Arrival(this.state, this.eventQueue, arrivalTime);
			eventQueue.SortedSequence(arrival);
		}

		// inte ligga här i start.. Start initieras bara när simulatorn kör igång.
//      while(closeTime >  arrivalTime) {
//    	  arrivalTime += state.arrivalTime();
//          arrival = new Arrival(this.state, this.eventQueue, arrivalTime);
//          eventQueue.SortedSequence(arrival);
//          break;
//      }
	}

	@Override
	public double getTime() {
		return time;
	}

	@Override
	public Customer getCustomer() {
		return null;
	}

	@Override
	public String writeOut() {
		return "Start";
	}
}
