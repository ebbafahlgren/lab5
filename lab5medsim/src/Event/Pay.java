package Event;

import Simulator.*;
import State.Customer;
import State.FIFO;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Pay extends Event {

	private double time;
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	private int counterPayments = 0;
	private int customerInStore;

	public Pay(State state, EventQueue eventQueue, double time, Customer customer) {
		super(state, eventQueue); //försöker ärva in från Event
	     
		//skapar nya objekt 
		this.time = time; 
	    this.customer = customer; 
	}

	@Override
	public void doThis() {
		
		//int customerInStore = arrival; //tänker att det kommer finnas en räknare i arrival som räknar antal inkommande?
		
		state.update(this);
	      
	    state.getStore().customerPayed(); //registrera att kunder betalat
	    state.getStore().removeCustomer(customer); //registera att kund ska tas bort
	    state.getStore().getAvailableRegisters(); //registera att kassan blir ledig
	      
	    if (state.getStore().getRegisterQueue() != null) { //om FIFOkön inte är tom
	    	state.getStore().getRegisterQueue().removeFirst(); //ropas removeFirst och personen först i kön tas bort
	    }
	    
	    Pay payEvent = new Pay(state, eventQueue, time, customer); //skapar själva betalningen
//	    counterPayments++; //räknar betalningar
//		customerInStore--; //uppdaterar antal kunder i butiken
	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	@Override
	public String writeOut() {
		return "Paying...";
	}

}