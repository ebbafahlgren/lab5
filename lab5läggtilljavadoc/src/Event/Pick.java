package Event;
import Simulator.Event;
import Simulator.EventQueue;
import Simulator.State;
import State.Customer;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Pick extends Event{

	private Customer customer;
	private double time;
	
	private State state;
	
	private Pay payEvent;
	private EventQueue eventQueue;
	
	/**
	 * @param state kommer ange statusen som plockning
	 * @param eventQueue plockningen kommer påverka eventkön
	 * @param customer kunden plockar varor
	 * @param time plockningen kommer påverka tiden
	 */
	public Pick(State state, EventQueue eventQueue, Customer customer, double time) {
		super(state, eventQueue);
		//System.out.println(time + "tid man får in i pick");
		
		this.customer = customer;
		
		this.time = time;
		
		this.eventQueue = eventQueue;
		this.state = state;
	}
	
	/** 
	 * doThis. uppdaterar vad som sker när plockning av varor skett.
	 * När kunden plockat klart kommer den att betala eller ställa sig i kö till kassorna om det inte finns en kassa ledig. 
	 * Nästa event kommer bli betalning
	 */
	@Override
	public void doThis() {
		state.update(this);
		
		state.getStore().addCustomerTotRegisterQueue();
	
		if (state.getStore().getAvailableRegisters() > 0) {
			
			double paymentTime = this.time + state.timePay();
			
			//System.out.println(paymentTime + "tid för betalning");
			//System.out.println(this.time + " this time");
			//System.out.println(state.timePay() + " timepay");
		
			payEvent = new Pay(this.state, this.eventQueue, paymentTime, this.customer);
			eventQueue.SortedSequence(payEvent);
			
			//LA TILL ATT KASSAN INTE LÄNGRE ÄR LEDIG
			state.getStore().setARegisterOccupied();
		} else {
			state.getStore().getRegisterQueue().add(customer);
			state.getStore().addCustomerTotRegisterQueue();
		}
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
	 * customer
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * String
	 * @return string
	 */
	@Override
	public String writeOut() {
		return "Pick";
	}
	
}
