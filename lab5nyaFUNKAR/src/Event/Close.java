package Event;

import Simulator.*;
import State.Customer;
import State.StoreState;
import Simulator.State;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

/**
 * The class close describes how a close event works
 */

public class Close extends Event{
	private State state;
	private StoreState store;
	
	/**
	 * 
	 * @param state generell status
	 * @param store status för specifika butiken
	 *
	 */


	public Close(State state, StoreState store) {
		//Skickar in state och eventQueue i Event
		//super(state, eventQueue);
		this.state = state;
		this.store = store;
	}
	
	/**
 	*  doThis uppdates the status to closed.
 	*/
	public void doThis() {
		store.updateTime(store.getClosingTime());
		store.updateTotRegisterTime(store.getCurrentEventTime(), store.getLastEventTime());
		store.updateTotQueueTime(store.getCurrentEventTime(), store.getLastEventTime());

		store.setStoreClose();
		state.update();
	}
	
	/**
	 * getTime 
	 * @return closingtime
	 */
	public double getTime() {
		return store.getClosingTime();
	}


	/**
	 * Writing out the string closing
	 * @return "Closing"
	 */
	public String writeOut() {
		return "Closing";
	}

	
	/**
	 * getCustomerID
	 * @return  "---"
	 */
	public String getCustomerID() {
		return "---";
	}

}
