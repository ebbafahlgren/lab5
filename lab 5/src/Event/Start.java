package Event;

import Simulator.Event;
import Simulator.EventQueue;
import Simulator.State;
import State.StoreState;

import State.*;

public abstract class Start extends Event {
	private Close closing;
	private Arrival arrival;
	private State state;
	private EventQueue eventQueue;
	private Close closeStore;

	private double closeTime;
	private double time;

	public Start(State state, EventQueue eventQueue) {
		super(state, eventQueue);
		this.time = 0d; ///????
		this.closeTime = state.getClosingTime();
	}

	@Override
	public void doThis() {
      state.update(this);
      
      state.getStore().setStoreOpen(true);
      
      closeStore = new Close(this.state, eventQueue, closeTime);
      
      eventQueue.SortedSequence(closing);
      
      double arrivalTime = 0;
      
      //inte ligga här i start.. Start initieras bara när simulatorn kör igång.
      while(closeTime >  arrivalTime) {
    	  arrivalTime += state.arrivalTime();
          arrival = new Arrival(this.state, this.eventQueue, arrivalTime);
          eventQueue.SortedSequence(arrival);
          break;
      }
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

