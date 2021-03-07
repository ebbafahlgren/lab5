//Jag är världssämst så denna är inte korrekt men tror inte själva uppbyggnaden är så fel. När jag programmerade i min Eclipse ändrade jag några metoder
//till public i andra klasser men vågade inte röra något i vårt gemensamma

package Event;
import java.awt.EventQueue;
import java.lang.Thread.State;

import Simulator.Event;
import State.Customer;
import State.FIFO;

public class Pay extends Event {

	private double time;
	private Customer customer;

	public Pay(State state, EventQueue eventQueue, double time, Customer customer) {
		super(state, eventqueue); //försöker ärva in från Event
	     
		//skapar nya objekt 
		this.time = time; 
	    this.customer = customer; 
	      
		
	}

	@Override
	public void doThis() {
		
		int counterPayments = 0;
		int customerInStore = arrival //tänker att det kommer finnas en räknare i arrival som räknar antal inkommande?
		
		 state.update(this);
	      
	      state.getStore().addMoney(); //registrera att pengar lagts till i getStore i State
	      state.getStore().removeCustomer(); //registera att kund ska tas bort
	      state.getStore().freeCheckOut(); //registera att kassan blir ledig
	      
	      if (FIFO.queue != null); //om FIFOkön inte är tom
	      FIFO.removeFirst(); //ropas removeFirst och personen först i kön tas bort
	      
	      Pay payEvent = new Pay(this.state, this.eventqueue); //skapar själva betalningen
	        counterPayments ++; //räknar betalningar
		customerInStore --; //uppdaterar antal kunder i butiken
	        
		
	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Customer getCustoumer() {
		// TODO Auto-generated method stub
		return null;
	}

}
