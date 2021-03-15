package simulator;
import simulator.State;
import Event.*;
import View.*;
import Event.Start;
import State.StoreState;
import State.StoreTime;
/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Simulator {
	private StoreState store;
	private State state;
	private EventQueue eventQueue;
	private StoreTime generalTime;
	private Start start;

	public Simulator(EventQueue eventQueue, State state, StoreState store) {
		this.state = state;
		this.store = store;
		this.eventQueue = eventQueue;
		generalTime = new StoreTime(store);
	}
	
	/**
	 * run kör simulatorn
	 */
	public void run() {

		eventQueue.SortedSequence(new Start(state, eventQueue, store, generalTime));
		
		eventQueue.SortedSequence(new Stop(state, store));

		
		// While simulator is running, it will keep on getting the first event in
		// queue and running the ques "doThis" function.
//		System.out.println("Run i simulatorn" + state.getStopFlag());
//		System.out.println(eventQueue.size());
		
		while (!state.getStopFlag() || eventQueue.size() != 0){
			//System.out.println(eventQueue.size());
			eventQueue.eventDone();
			//med denna ligger start kvar i listan tills första arrival skrivs ut
		}
	}
	
}
