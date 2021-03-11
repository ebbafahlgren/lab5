package Event;

import Simulator.*;

import Simulator.*;
import State.CreateCustomer.customerStatus;
import State.Customer;
import State.StoreState;
import Simulator.State;
import Time.*;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Arrival extends Event {
	private Pick pickEvent;
	private double time;
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	
	/** 
	 * @param state kommer ange statusen som ankomst
	 * @param eventQueue ankomsten kommer påverka eventkön
	 * @param time ankomsten kommer påverka tiden
	 */
	
	public Arrival(State state, EventQueue eventQueue, double time) {
		super(state, eventQueue);
		this.time = time;
		//System.out.println(this.time);
		
		this.state = state;
		customer = this.state.getStore().createCustomer();
		//System.out.println("arrival!");
		//System.out.println(eventQueue + " eventkön direkt i arrival");

		//this.eventQueue = eventQueue;
		this.eventQueue = eventQueue;

	}
	/** 
	 * doThis. uppdaterar vad som sker när en kund ankommer butiken. 
	 * Antal kunder i butiken uppdateras
	 * Nästa event kommer vara att kunden plockar varor. 
	 */

	@Override
	public void doThis() {
		state.update(this);
		
		this.state.getStore().addCustomer(customer);
		
		//System.out.print(customer.getState());
		if (customer.getState() == customerStatus.inStore) {
			
			//double pickTime = this.time + state.timePick(); //
			
			double a = state.timePick();
			
			double pickTime = this.time + a;
			//System.out.println(a);
			//System.out.println(pickTime + " uträknad");
			//System.out.println(this.eventQueue + " eventkö");
			//System.out.println(this.time + " this time inuti do this");
			//System.out.println(state.timePick() + " plocktid");
			
			//System.out.println(pickTime + " picktime");
			
			pickEvent = new Pick(state, eventQueue, customer, pickTime);
			eventQueue.SortedSequence(pickEvent);
			//System.out.println(pickEvent);
			
			//System.out.println(eventQueue + " = vad som finns i eventQueue");
			
		}
	}
	
	/**
	 * getTime
	 */
	@Override
	public double getTime() {
		return time;
	}
	
	/**
	 * getCustomer
	 */
	@Override
	public Customer getCustomer() {
		return customer;
	}
	
	 /**
	 * writeOut
	 */
	@Override
	public String writeOut() {
		return "Arrival";
	}

}
