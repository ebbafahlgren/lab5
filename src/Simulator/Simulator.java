package Simulator;

import Simulator.State;
import State.StoreState;
import Event.*;
import View.*;
import Event.Start;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Simulator {

	private State state;
	private StoreState store;
	private EventQueue eventQueue;
	private View view;
	private StoreView storeView;
	private Start start;

	public Simulator(StoreState store, StoreView storeView) {
		this.store = store;
		this.storeView = storeView;
		this.eventQueue = new EventQueue();
	}
	
	/**
	 * run kör simulatorn
	 */
	public void run() {

		eventQueue.SortedSequence(new Start(store, eventQueue));
		
		eventQueue.SortedSequence(new Stop(store, eventQueue));

		
		// While simulator is running, it will keep on getting the first event in
		// queue and running the ques "doThis" function.
		//System.out.println("Run i simulatorn");
//		&& state.getStopFlag() ska va i while satsen
		while (eventQueue.size()!=0 ){
			Event event = eventQueue.first();
			event.doThis();
			eventQueue.removeFirst(); //med denna ligger start kvar i listan tills första arrival skrivs ut
		}
	}
	
}
