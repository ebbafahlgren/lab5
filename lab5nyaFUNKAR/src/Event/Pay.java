package Event;

import Simulator.*;
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
	 * @param c kunden kommer göra en betalning
	 */
	public Pay(State state, EventQueue eventQueue, double time, Customer c, StoreState store, StoreTime generalTime) {
		//super(state, eventQueue); //försöker ärva in från Event
	     
		//skapar nya objekt 
		this.time = time;
	   	customer = c;
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

	public void doThis() {
		//System.out.print("hej");
		//int customerInStore = arrival; //tänker att det kommer finnas en räknare i arrival som räknar antal inkommande?

		store.updateTime(time);
		store.updateTotRegisterTime(store.getCurrentEventTime(), store.getLastEventTime());
		store.updateTotQueueTime(store.getCurrentEventTime(), store.getLastEventTime());

	   // store.getCustomerPayed(); //registrera att kunder betalat
	   // store.removeCurrentCustomer(); //registera att kund ska tas bort
		state.update();
	    store.addAvailableRegisters();  //GJORDE EN SET FUNKTION

	    //System.out.print(state.getStore().getRegisterQueue() + " kassakön");
	    
	    //FIXA DEN
	    if (!store.getTheFIFO().isEmpty()) { //om FIFOkön inte är tom
			//System.out.print("testar testar");

			Customer firstInLine = store.getTheFIFO().firstInLine();



			double payTime = this.time + generalTime.timePay();
			Pay paymentEvent = new Pay(state, eventQueue, payTime, firstInLine, store, generalTime);
			eventQueue.SortedSequence(paymentEvent);
			store.removeAvailableRegisters();
		}
			if (store.getTheFIFO().size() > 0) {
				store.getTheFIFO().removeFirst();
			}
			store.removeCurrentCustomer();
			store.addCustomerPayed();
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
	* getTotaltime
	* @return totaltime
	*/
	
	public double totalTime() {

		return getTime() + time;
	}

	/**
	 * getCustomer
	 * @return customer
	 */

	public String getCustomerID() {
		return Integer.toString(customer.getID());
	}
	
	/**
	 * writeOut
	 * @return string "paying"
	 */

	public String writeOut() {
		return "Paying";
	}

}
