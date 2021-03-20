package Simulator;

import Simulator.State;
import Event.*;
import View.*;
import Event.Start;
import State.StoreState;
import State.StoreTime;
/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

/**
 * Simulator creates a simulation of a store
 *
 */

public class Simulator {
	private StoreState store;
	private State state;
	private EventQueue eventQueue;
	private StoreTime generalTime;
	private Start start;

/**
 * @param eventQueue is the event queue
 * @param state is general for the simulator, it contains a stopflag and functions to turn it on and off
 * @param store is a type of state which describes a store
 */
	public Simulator(EventQueue eventQueue, State state, StoreState store) {
		this.state = state;
		this.store = store;
		this.eventQueue = eventQueue;
		generalTime = new StoreTime(store);
	}
	
	/**
	 * run the simulator
	 */
	public void run() {

		eventQueue.SortedSequence(new Start(state, eventQueue, store, generalTime));
		
		eventQueue.SortedSequence(new Stop(state, store));

		
		// While simulator is running, it will keep on getting the first event in
		// queue and running the ques "doThis" function.
		//System.out.println("Run i simulatorn");
		
		while (!state.getStopFlag()){
			eventQueue.eventDone();

			//med denna ligger start kvar i listan tills första arrival skrivs ut
		}
	}
	
}
