package Event;
import simulator.Event;
import simulator.EventQueue;
import simulator.State;
import State.Customer;
import State.StoreState;
import State.StoreTime;
import State.FIFO;
/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Pick extends Event{

	private Customer customer;
	private double thisPickTime;
	
	private State state;

	private StoreTime storeTime;
	private Pay payEvent;
	private EventQueue eventQueue;
	private StoreState store;
	private StoreTime generalTime;
	
	/**
	 * @param state kommer ange statusen som plockning
	 * @param eventQueue plockningen kommer påverka eventkön
	 * @param customer kunden plockar varor
	 * @param time plockningen kommer påverka tiden
	 */
	public Pick(State state, StoreState store, EventQueue eventQueue, Customer customer, double time, StoreTime generalTime) {
		//super(state, eventQueue);
		//System.out.println(time + "tid man får in i pick");
		this.generalTime = generalTime;
		this.customer = customer;
		thisPickTime = time;
		this.eventQueue = eventQueue;
		this.state = state;
		this.store = store;
	}
	
	/** 
	 * doThis. uppdaterar vad som sker när plockning av varor skett.
	 * När kunden plockat klart kommer den att betala eller ställa sig i kö till kassorna om det inte finns en kassa ledig. 
	 * Nästa event kommer bli betalning
	 */

	public void doThis() {

		FIFO fifo = store.getTheFIFO();


		
		store.addCustomerToArray(customer);
	
		if (store.getAvailableRegisters() > 0) {
			store.updateTime(thisPickTime);
			store.updateTotQueueTime(store.getCurrentEventTime(), store.getLastEventTime());
			store.updateTotRegisterTime(store.getCurrentEventTime(), store.getLastEventTime());

			//double paymentTime = thisPickTime + storeTime.timePay();
			//State state, EventQueue eventQueue, double time, Customer customer, StoreState store

			payEvent = new Pay(state, eventQueue, getTimePay(), customer, store, generalTime);
			eventQueue.SortedSequence(payEvent);

			state.update();

			//LA TILL ATT KASSAN INTE LÄNGRE ÄR LEDIG
			//store.removeAvailableRegisters();
			store.setARegisterOccupied();
			
		} else {
			store.updateTime(thisPickTime);
			store.updateTotQueueTime(store.getCurrentEventTime(), store.getLastEventTime());
			store.updateTotRegisterTime(store.getCurrentEventTime(), store.getLastEventTime());
			state.update();
			
			store.getTheFIFO().add(customer);
			//store.addCurrentCustomers();
		}
	}
	
	/**
	 * getTime
	 * @return time
	 */

	public double getTime() {
		return thisPickTime;
	}

	public double getTimePay() {
		return thisPickTime + generalTime.timePay();
	}
	/**
	 * customer
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	public String getCustomerID() {
		return Integer.toString(customer.getID());
	}
	
	/**
	 * String
	 * @return string
	 */

	public String writeOut() {
		return "Pick";
	}
	
}
