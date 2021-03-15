package Event;

import simulator.*;
import State.Customer;
import State.FIFO;
import State.StoreState;
import State.StoreTime;
/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Pay extends Event {

	private double time;
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	private StoreState store;
	private StoreTime generalTime;
	
	/**
	 * @param state kommer ange statusen som betalning
	 * @param eventQueue betalningen kommer påverka eventkön
	 * @param time betalningen kommer påverka tiden
	 * @param customer kunden kommer göra en betalning
	 */
	public Pay(State state, EventQueue eventQueue, double time, Customer customer, StoreState store, StoreTime generalTime) {
		//super(state, eventQueue); //försöker ärva in från Event
	     
		//skapar nya objekt 
		this.time = time;
	    this.customer = customer; 
	    //System.out.println("skapar pay event");
	    this.state = state;
	    this.eventQueue = eventQueue;
	    this.store = store;
	    this.generalTime = generalTime;
	}
	
	 /** 
	 * doThis. uppdaterar vad som sker när en en betalning sker 
	 * Under tiden betalningen sker kommer en kassa markeras som upptagen
	 * När en betlaning skett och kunden lämnar kommer antal personer i butiken uppdateras.
	 */
	@Override
	public void doThis() {
		//System.out.print("hej");
		//int customerInStore = arrival; //tänker att det kommer finnas en räknare i arrival som räknar antal inkommande?

		store.updateTime(time);
		store.updateTotRegisterTime(store.getCurrentEventTime(), store.getLastEventTime());
		store.updateTotQueueTime(store.getCurrentEventTime(), store.getLastEventTime());

	   // store.getCustomerPayed(); //registrera att kunder betalat
	   // store.removeCurrentCustomer(); //registera att kund ska tas bort
	    store.addAvailableRegisters();  //GJORDE EN SET FUNKTION
		state.update();
	    //System.out.print(state.getStore().getRegisterQueue() + " kassakön");
	    
	    //FIXA DEN
	    if (!store.getTheFIFO().isEmpty()) { //om FIFOk�n inte �r tom
	    	//System.out.print("testar testar");
	    	
	    	Customer firstInLine = store.getTheFIFO().firstInLine();

	    	//double paymentTime = this.time + storeTime.timePay();
	    	//State state, EventQueue eventQueue, double time, Customer customer, StoreState store
	    	Pay paymentEvent = new Pay(state, eventQueue, getPayTime(), firstInLine, store, generalTime);
	    	eventQueue.SortedSequence(paymentEvent);
	    	
	    	//this.time = paymentTime;

	    	store.setARegisterOccupied();
			
	    	store.getTheFIFO().removeFirst();
	    	
	    	store.addCustomerPayed();
			store.removeCurrentCustomer();
			
		    System.out.println("kund betalar");
	    	//DENNA SKRIVS INTE UT; VARFÖR HÄNDER INTE DEN??
	    	//System.out.print("Gör en betalning på tiden " + paymentTime);
	    }
	    //System.out.println("G�r en betalning p� tiden " + this.time + " tiden");
	}
	
	/**
	 * getTime
	 * @return time
	 */
	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return time;
	}
	
	public double getPayTime() {
		return this.time + generalTime.timePay();
	}
	
	/**
	 * getCustomer
	 * @return customer
	 */
	@Override
	public String getCustomerID() {
		return Integer.toString(customer.getID());
	}
	
	/**
	 * writeOut
	 * @return string
	 */
	@Override
	public String writeOut() {
		return "Paying";
	}

}
