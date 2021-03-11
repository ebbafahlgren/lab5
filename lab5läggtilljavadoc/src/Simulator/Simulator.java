package Simulator;

import Simulator.State;
import Event.*;
import View.*;
import Event.Start;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Simulator {

	private State state;
	private EventQueue eventQueue;
	private View view;
	private Start start;

	public Simulator(State state, View view) {
		this.state = state;
		this.view = view;
		this.eventQueue = new EventQueue();

	}
	
	/**
	 * run kör simulatorn
	 */
	public void run() {

		eventQueue.SortedSequence(new Start(state, eventQueue));
		
		eventQueue.SortedSequence(new Stop(state, eventQueue));

		
		// While simulator is running, it will keep on getting the first event in
		// queue and running the ques "doThis" function.
		//System.out.println("Run i simulatorn");
		
		while (eventQueue.size()!=0){
			Event event = eventQueue.first();
			event.doThis();
			eventQueue.removeFirst(); //med denna ligger start kvar i listan tills första arrival skrivs ut
		}
	}
	
}
