package Event;

import Simulator.*;
import State.Customer;
import State.StoreState;
import Simulator.State;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Close extends Event{

	private double time;
	private State state;
	private StoreState store;
	
	/**
	 * 
	 * @param state      kommer ange statusen som stängning
	 * @param eventQueue stängningen kommer påverka eventkön
	 * @param time       stängningen kommer påverka tiden
	 */


	public Close(State state, EventQueue eventQueue, double time) {
		//Skickar in state och eventQueue i Event
		super(state, eventQueue);
		this.time = time;
		this.state = state;
		this.store = (StoreState) state;
	}
	
	/**
 	*  doThis uppdaterar statusen på butiken till stängd
 	*/

	@Override
	public void doThis() {
		state.update();
		store.setStoreOpen(false);
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
	 * getCustomer
	 * @return null
	 */

	@Override
	public Customer getCustomer() {
		return null;
	}
	/**
	 * writeOut
	 * @return string 
	 */

	@Override
	public String writeOut() {
		return "Closing";
	}

}
