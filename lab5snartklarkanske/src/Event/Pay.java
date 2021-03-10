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


	public Pay(State state, EventQueue eventQueue, double time, Customer customer) {
		super(state, eventQueue); //försöker ärva in från Event
	     
		//skapar nya objekt 
		this.time = time; 
	    this.customer = customer; 
	    //System.out.println("skapar pay event");
	    this.state = state;
	}

	@Override
	public void doThis() {
		//System.out.print("hej");
		//int customerInStore = arrival; //tänker att det kommer finnas en räknare i arrival som räknar antal inkommande?
		
		state.update(this);
	      
	    state.getStore().customerPayed(); //registrera att kunder betalat
	    state.getStore().removeCustomer(customer); //registera att kund ska tas bort
	    state.getStore().setAAvailableResister();  //GJORDE EN SET FUNKTION
	    
	    //System.out.print(state.getStore().getRegisterQueue() + " kassakön");
	    
	    //FIXA DEN
	    if (!state.getStore().getRegisterQueue().isEmpty()) { //om FIFOkön inte är tom
	    	//System.out.print("testar testar");
	    	
	    	Customer firstInLine = state.getStore().getRegisterQueue().firstInLine();
	    	state.getStore().getRegisterQueue().removeFirst();
	    	
	    	double paymentTime = this.time + state.timePay();
	    	
	    	Pay paymentEvent = new Pay(this.state, this.eventQueue, paymentTime, firstInLine);
	    	eventQueue.SortedSequence(paymentEvent);
	    	
	    	state.getStore().setARegisterOccupied();
	    	
	    	//DENNA SKRIVS INTE UT; VARFÖR HÄNDER INTE DEN??
	    	//System.out.print("Gör en betalning på tiden " + paymentTime);
	    }
	    
	    //System.out.print("Gör en betalning på tiden extraaa ");
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