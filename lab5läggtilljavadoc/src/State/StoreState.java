package State;

import java.util.ArrayList;

import Simulator.State;
import State.CreateCustomer.customerStatus;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class StoreState{ //extends State {
	
	private int maxCustomer;
	private int numRegister; // antal kassor
	private double closingTime;
	private boolean isOpen;
		
	//customers holds all customers, even the ones that aren't in the store.
	private ArrayList<Customer> customers;
   
	// createCustomer  creates new customers.
	private CreateCustomer createCustomer;
   
	// Queue of customers waiting in line
	private FIFO registerQueue;
   
	private int customerPayed;
	private int availableRegisters;
   
	private double registerFreetime;
   	private double customerQueueTime;
   	private int totCustomersInRegisterQueue;
   	private double lastPaymentTime;

	/**
	 * @param numRegister antal kassor
 	 * @param closingTime stängningstid
	 * @param maxCustomers max antal kunder
	 */
	public StoreState(int numRegister, double closingTime, int maxCustomers) {
		
		isOpen = false; // Start with a closed store
		this.maxCustomer = maxCustomers;
		this.closingTime = closingTime;
		this.numRegister = numRegister;
		this.availableRegisters = numRegister;
		
		customers = new ArrayList<Customer>();
		createCustomer = new CreateCustomer();
		registerQueue = new FIFO();
		
		totCustomersInRegisterQueue = 0;
		//lagt till
		//StoreState store = new StoreState(maxCustomers, numRegister, maxCustomers);
		//createCustomer = new createCustomer();
	}
	
	/**
	 * getNumCustomerInStore ger nuvarande antal kunder i butiken
	 * @return customerInStoreNow
	 */
	public int getNumCustomerInStore() {
		int customerInStoreNow = 0;
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getState() == customerStatus.inStore) {
				customerInStoreNow++;
			}	
		}
		return customerInStoreNow;
	}
	
	/**
	 * isStoreOpen
	 * @return isOpen
	 */
	public String isStoreOpen(){
			if (isOpen == true) {
				return "Ö";
			}
			else {
				return "S";
			}
	}

	/**
	 * @param value value
	 */
	public void setStoreOpen(boolean value) {
		this.isOpen = value; 
	}
	
	/**
	 * @return antal kassor
	 */
	public int getnumRegister() {
		return numRegister;
	}
	
	/**
	 * @return customer
	 */
	public Customer createCustomer() {
		Customer customer = createCustomer.createCustomer();
		return customer;
	}
	
	/**
	 * @return customerQueueTime
	 */
	public double getCustomerQueueTime() {
		return customerQueueTime;
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
	public FIFO getRegisterQueue() {
		return registerQueue;
	}
	/**
	* @return registerQueue
	*/
	public int getTotNumCustomersInRegisterQueue() {
		// TODO Auto-generated method stub
		return registerQueue.getCustomerInQueueTot();
	}
	
	/**
	 *  returnerar att en till kund har betalat
	 * @return customerPayed 
	 */
	public void customerPayed() {
		customerPayed++;
	}
	
	/**
	 * @return customerPayed
	 */
	public int numCustomerPayed() {
		return customerPayed;
	}	
	
	/**
	 * @return maxCustomer
	 */
	public int getMaxCustomer() {
		return maxCustomer;
	}
	
	/** 
	 * @return closingTime
	 */
	public double getClosingTime() {
		return closingTime;
	}
	
	/**
	 * @return avaiableRegisters
	 */
	public int getAvailableRegisters() {
		return availableRegisters;
	}

	/**
	 * @param time tiden uppdateras med den senaste betalningstiden
	 */
	public void setLastPaymentTime(double time) {
		this.lastPaymentTime = time;
	}
	
	/**
	 * @return lastPaymentTime
	 */
	public double getLastPaymentTime() {
		return lastPaymentTime;
	}
	
	/**
	 * @param customer tar bort kund
	 */
	public void removeCustomer(Customer customer) {
		customer.setState(customerStatus.notInStore);		
	}
	
	 /**
	 * @param customer om butiken är stängd eller full blir kunden avvisad annars får den komma in
	 */
	public void addCustomer(Customer customer) {
		
		if(!isOpen) {
			customer.setState(customerStatus.walkedAway);
		} else if(getNumCustomerInStore() < maxCustomer) {
			customer.setState(customerStatus.inStore);
		} else {
			customer.setState(customerStatus.fullStore);
		}
		customers.add(customer);

	}

	/**
	 * räknar totalt antal kunder i butiken
	 * @return numCostumerTot
	 */
	public int totalCustomers() {
		int numCustomerTot = 0;
		
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getState() != customerStatus.walkedAway) {
				numCustomerTot++;
			}
		}
		return numCustomerTot;
	}
	
	/**
	 * räknar de kunder som inte fått komma in i butiken
	 * @return numTurnedAway
	 */
	public int getTurnedAwayCustomers(){
		int numTurnedAway = 0;
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getState() == customerStatus.fullStore) {
				//System.out.print(numTurnedAway);
				numTurnedAway++;
			}
		}
		return numTurnedAway;
	}

	/**
	 * @return totalCustomersInRegisterQueue
	 */
	public double getTotCustomersInRegisterQueue() {
		return getRegisterQueue().size();
	}
	
	/**
	 * totCustomersInRegisterQueue++
	 */
	public void addCustomerTotRegisterQueue() {
		totCustomersInRegisterQueue++;
	}
	
	/**
 	* @param time registerFreetime
 	*/
	public void setRegisterFreeTime(double time) {
		registerFreetime = time;
	}
	
	/**
	 * @param time kötid för kunden
	 */
	public void setTotCustomerQueueTime(double time) {
		customerQueueTime = time;
	}

	/**
	 * minskar lediga kassor
 	 */
	public void setARegisterOccupied() {
		availableRegisters--;
	}

	/**
	 * När en kund betalat blir dennes kassa ledig
	 */
	public void setAAvailableResister() {
		availableRegisters++;
	}


}
