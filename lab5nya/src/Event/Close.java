package Event;

import simulator.*;
import State.Customer;
import State.StoreState;
import simulator.State;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Close extends Event{
	private State state;
	private StoreState store;
	
	/**
	 * 
	 * @param state kommer ange statusen som stängning
	 * @param store bajs
	 */


	public Close(State state, StoreState store) {
		//Skickar in state och eventQueue i Event
		//super(state, eventQueue);
		this.state = state;
		this.store = store;
	}
	
	/**
 	*  doThis uppdaterar statusen på butiken till stängd
 	*/


	public void doThis() {
		store.updateTime(store.getClosingTime());
		store.updateTotRegisterTime(store.getCurrentEventTime(), store.getLastEventTime());
		store.updateTotQueueTime(store.getCurrentEventTime(), store.getLastEventTime());
		store.setClose();
		
		state.update();
	}
	
	/**
	 * getTime 
	 * @return time
	 */


	public double getTime() {
		return store.getClosingTime();
	}



	public String writeOut() {
		return "Closing";
	}

	public String getCustomerID() {
		return "--";
	}

}
