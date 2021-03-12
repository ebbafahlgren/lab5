package State;

import java.util.ArrayList;
import Event.*;
import Simulator.Event;
import Simulator.EventQueue;
import Simulator.State;
import State.CreateCustomer.customerStatus;
import Time.ExponentialRandomStream;
import Time.UniformRandomStream;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class StoreState extends State {
	private double lastPaymentTime = 0;
	private double currentEventTime = 0;
	private double lastEventTime = 0;
	private double totalQueueTime = 0;
	private double totalRegisterTime = 0;
	private int currentCustomers = 0;

	private ArrayList<Customer> customerList = new ArrayList<>();
	private EventQueue eventQueue;

	private int maxCustomers;
	private double closingTime;
	private double currentTime;

	private long seed;
	private double lambda;
	private double payMinTime;
	private double payMaxTime;
	private double pickMinTime;
	private double pickMaxTime;

	private int numRegister; // antal kassor
	private boolean isOpen; // kollar om butiken �r �ppen

	// Queue of customers waiting in line
	private FIFO registerQueue;

	private int customerPayed;
	private int customerNotPayed;
	private int availableRegisters; // Free registers
	private int maxRegisters;

	private double registerFreetime;
	private int totCustomersInRegisterQueue;
	private double customerQueueTime;
	private Event currentEvent;
	private Customer currentCustomer;
	private StoreTime storetime;
	private CreateCustomer createCustomer;


	/**
	 * @param registers    antal kassor
	 * @param closingTime  st�ngningstid
	 * @param maxCustomers max antal kunder
	 */
	public StoreState(int maxCustomers, int registers, double closingTime, double lambda, long seed, double minPick,
			double maxPick, double minPay, double maxPay, EventQueue eventQueue) {

		this.closingTime = closingTime;
		this.maxCustomers = maxCustomers;
		this.seed = seed;
		this.pickMinTime = minPick;
		this.pickMaxTime = maxPick;
		this.payMinTime = minPay;
		this.payMaxTime = maxPay;
		this.lambda = lambda;
		this.eventQueue = eventQueue;
		this.maxRegisters = registers;
		availableRegisters = registers;
		isOpen = true;
		registerQueue = new FIFO();
		createCustomer = new CreateCustomer();
		
		
	}

	/**
	 * @return customer
	 */
	public Customer createCustomer() {
		Customer customer = createCustomer.createCustomer();
		return customer;
	}

	
	public void addCustomerToArray(Customer customer) {
		customerList.add(customer);
	}

	public void addCustomerPayed() {
		customerPayed++;
	}

	public void addCustomerNotPayed() {
		customerNotPayed++;
	}

	public void addCurrentCustomers() {
		currentCustomers++;
	}

	public void addAvailableRegisters() {
		availableRegisters++;
	}

	public void setClose() {
		isOpen = false;
	}

	public boolean isStoreOpen() {
		return isOpen;
	}

	public int getQueue() {
		return registerQueue.size();
	}

	public void removeCurrentCustomer() {
		currentCustomers--;
	}

	public void removeAvailableRegisters() {
		availableRegisters--;
	}

	public double getLambda() {
		return lambda;
	}

	public long getSeed() {
		return seed;
	}

	/**
	 * @return pickMinTime
	 */
	public double getPickMinTime() {
		return pickMinTime;
	}

	/**
	 * @return picMaxTime
	 */
	public double getPickMaxTime() {
		return pickMaxTime;
	}

	/**
	 * @return payMinTime
	 */
	public double getPayMinTime() {
		return payMinTime;
	}

	/**
	 * @return payMaxTime
	 */
	public double getPayMaxTime() {
		return payMaxTime;
	}

	public int getCurrentCustomers() {
		return currentCustomers;
	}

	public double getClosingTime() {
		return closingTime;
	}

	public int getMaxCustomers() {
		return maxCustomers;
	}

	public int getMaxRegisters() {
		return maxRegisters;
	}

	public int getAvailableRegisters() {
		return availableRegisters;
	}

	public int getCustomerPayed() {
		return customerPayed;
	}

	public int getCustomerNotPayed() {
		return customerNotPayed;
	}

	public int getTotalPayments() {
		return customerNotPayed + customerPayed;
	}

	public void updateTotRegisterTime(double currentTime, double lastEventTime) {
		if (isStoreOpen()) {
			totalRegisterTime += (currentTime - lastEventTime) * getAvailableRegisters();
		} else {
			int count = 0;
			ArrayList<Event> eventList = eventQueue.getList();
			for (Event event : eventList) {
				if (event.writeOut() == "Betalning") {
					count++;
					lastPaymentTime = event.getTime();
				}
			}
			if (count > 0) {
				totalRegisterTime += (currentTime - lastEventTime) * getAvailableRegisters();
			}
		}
	}

	public void updateTotQueueTime() {
		totalQueueTime += (currentTime - lastEventTime) * registerQueue.size();
	}

	public void updateTime(double time) {
		lastEventTime = currentEventTime;
		currentEventTime = time;
	}
	
	/**
	 * @param thisEvent uppdaterar eventet till det aktuella.
	 */
	public void update(Event thisEvent) {
		currentEvent = thisEvent;
		currentCustomer = thisEvent.getCustomer();
		
		lastEventTime = currentTime;
		currentTime = thisEvent.getTime();
		
		if(thisEvent.getClass() != Stop.class) {
			if(this.getAvailableRegisters() == 0) {
				
				this.setRegisterFreeTime(this.getRegisterFreetime());
				
				if(thisEvent.getClass() == Pay.class) {
				    this.setLastPaymentTime(thisEvent.getTime());
				}
				
			} else {
				if(thisEvent.getClass() == Arrival.class && this.isStoreOpen() == true ) {
					this.setRegisterFreeTime(this.getRegisterFreetime());
					
				} else {
				   this.setRegisterFreeTime(this.getRegisterFreetime() + timeBetweenEvent()*this.getAvailableRegisters());
				}
			
				if(thisEvent.getClass() == Pay.class && this.isStoreOpen() == false) {
					lastPaymentTime = currentTime;
				}
			}
						
			this.setTotCustomerQueueTime(this.getCustomerQueueTime() + timeBetweenEvent()*this.getTotCustomersInRegisterQueue());	
		}
		setChanged();
		notifyObservers();
	}
	

	public double getLastEventTime() {
		return lastEventTime;
	}

	public double getCurrentEventTime() {
		return currentEventTime;
	}

	public double getLastPaymentTime() {
		return lastPaymentTime;
	}

	public double getTotalRegisterTime() {
		return totalRegisterTime;
	}

	public double getTotalQueueTime() {
		return totalQueueTime;
	}

	public int getMaxSize() {
		return registerQueue.maxLength();
	}

	public FIFO getTheFIFO() {
		return registerQueue;

	}
	
	
	
	
	/**
	 * räknar totalt antal kunder i butiken
	 * @return numCostumerTot
	 */
	public int totalCustomers() {
		int numCustomerTot = 0;
		
		for(int i = 0; i < customerList.size(); i++) {
			if(customerList.get(i).getState() != customerStatus.walkedAway) {
				numCustomerTot++;
			}
		}
		return numCustomerTot;
	}
	
	/**
	 * @return customerPayed
	 */
	public int numCustomerPayed() {
		return customerPayed;
	}	
	
	
	/**
	 * räknar de kunder som inte fått komma in i butiken
	 * @return numTurnedAway
	 */
	public int getTurnedAwayCustomers(){
		int numTurnedAway = 0;
		for(int i = 0; i < customerList.size(); i++) {
			if(customerList.get(i).getState() == customerStatus.fullStore) {
				//System.out.print(numTurnedAway);
				numTurnedAway++;
			}
		}
		return numTurnedAway;
	}
	
	
	
	/**
	 * @return registerFreeTime
	 */
	public double getRegisterFreetime() {
		return registerFreetime;
	}
	
	/**
	* @return registerQueue
	*/
	public int getTotNumCustomersInRegisterQueue() {
		// TODO Auto-generated method stub
		return registerQueue.getCustomerInQueueTot();
	}
	
	
	/**
	 * @return customerQueueTime
	 */
	public double getCustomerQueueTime() {
		return customerQueueTime;
	}
	
	
	/**
	 * @return nuvarande event
	 */
	public Event getEvent() {
		return currentEvent;
	}

	/**
	 * @return nuvarande kund
	 */
	public Customer getCustomer() {
		return currentCustomer;
	}

	/**
 	* @param time registerFreetime
 	*/
	public void setRegisterFreeTime(double time) {
		registerFreetime = time;
	}
/**
	 * @param time tiden uppdateras med den senaste betalningstiden
	 */
	public void setLastPaymentTime(double time) {
		this.lastPaymentTime = time;
	}
/**
	 * @return tiden mellan event
	 */
	private double timeBetweenEvent() {
		return currentTime - lastEventTime;
	}

	/**
	 * @return registerQueue
	 */
	public FIFO getRegisterQueue() {
		return registerQueue;
	}
	
	/**
	 * @return nuvarande tid
	 */
	public double getTime() {
		return currentTime;
	}
	
	
	/**
	 * getNumCustomerInStore ger nuvarande antal kunder i butiken
	 * @return customerInStoreNow
	 */
	public int getNumCustomerInStore() {
		int customerInStoreNow = 0;
		for(int i = 0; i < customerList.size(); i++) {
			if(customerList.get(i).getState() == customerStatus.inStore) {
				customerInStoreNow++;
			}	
		}
		return customerInStoreNow;
	}

	/**
	 * @return totalCustomersInRegisterQueue
	 */
	public double getTotCustomersInRegisterQueue() {
		return getRegisterQueue().size();
	}

	/**
	 * @param value value
	 */
	public void setStoreOpen(boolean value) {
		this.isOpen = value; 
	}

	/**
	 * @param time kötid för kunden
	 */
	public void setTotCustomerQueueTime(double time) {
		customerQueueTime = time;
	}


	


	
	
}