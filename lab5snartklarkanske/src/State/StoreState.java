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
	


	public boolean isStoreOpen(){
		return isOpen;
	}
	
	public void setStoreOpen(boolean value) {
		this.isOpen = value; //FATTAAR INTE VARF�R �N??
	}
	
	public int getnumRegister() {
		return numRegister;
	}
	
	public Customer createCustomer() {
		Customer customer = createCustomer.createCustomer();
		return customer;
	}
	
	//�NDRA S� att tiden �ndras.. 
	public double getCustomerQueueTime() {
		return customerQueueTime;
	}
	
	public double getRegisterFreetime() {
		return registerFreetime;
	}
	
	public FIFO getRegisterQueue() {
		return registerQueue;
	}
	
	public void customerPayed() {
		customerPayed++;
	}
	
	public int numCustomerPayed() {
		return customerPayed;
	}	
	
	public int getMaxCustomer() {
		return maxCustomer;
	}
	
	public double getClosingTime() {
		return closingTime;
	}
	
	public int getAvailableRegisters() {
		return availableRegisters;
	}

	public void setLastPaymentTime(double time) {
		this.lastPaymentTime = time;
	}
	
	public double getLastPaymentTime() {
		return lastPaymentTime;
	}
	
	//Tar bort kunden
	public void removeCustomer(Customer customer) {
		customer.setState(customerStatus.notInStore);
		
	}
	// l�gger till kund till butiken
	public void addCustomer(Customer customer) {
		
		customer.setState(customerStatus.inStore);
	}

	public int totalCustomers() {
		int numCustomerTot = 0;
		
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getState() != customerStatus.walkedAway) {
				numCustomerTot++;
			}
		}
		return numCustomerTot;
	}
	
	public int getTurnedAwayCustomers(){
		int numTurnedAway = 0;
		
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getState() == customerStatus.walkedAway) {
				numTurnedAway++;
			}
		}
		return numTurnedAway;
	}

	public double getTotCustomersInRegisterQueue() {
		return totCustomersInRegisterQueue;
	}
	
	public void addCustomerTotRegisterQueue() {
		totCustomersInRegisterQueue++;
	}

	public void setRegisterFreeTime(double time) {
		registerFreetime = time;
	}

	public void setTotCustomerQueueTime(double time) {
		customerQueueTime = time;
	}

	public void setARegisterOccupied() {
		availableRegisters--;
	}

	//GJORDE DENNA FUNKTION som g�rs n�r nytt pay-event h�nder
	public void setAAvailableResister() {
		// TODO Auto-generated method stub
		availableRegisters++;
	}
}
