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
		
		//System.out.println("kör simulatorn");
	}

	public void run() {
		
		//System.out.println("lägger till start");
		eventQueue.SortedSequence(new Start(state, eventQueue));
		
		//System.out.println("lägger till stop");
		eventQueue.SortedSequence(new Stop(state, eventQueue));

		
		// While simulator is running, it will keep on getting the first event in
		// queue and running the ques "doThis" function.
		//System.out.println("Run i simulatorn");
		
		while (eventQueue.size()!=0){
			Event event = eventQueue.first();
			//System.out.println(event + " skriver");
			eventQueue.removeFirst();
			event.doThis();
		}
	}
	
//	public void run() throws IndexOutOfBoundsException{
//		while (this.state.getFlag()) {
//			if (this.queue.isEmpty()) {
//				throw new IndexOutOfBoundsException("No ending event has been added!");
//			}
//			queue.nextEvent(this.state);
//	}
}