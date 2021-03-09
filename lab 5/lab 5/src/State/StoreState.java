package State;

import java.util.ArrayList;


import Simulator.State;
import State.CreateCustomer.customerStatus;
import State.Customer;


public class StoreState extends State {
	
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
   
	private int customerPayed = 0;
	private int availableRegisters;
   
	private double registerFreetime;
   	private double customerQueueTime;
   	private int totalCustomersInQueue;
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
		
		totalCustomersInQueue = 0;
	}
	
	public boolean isStoreOpen(){
		return isOpen;
	}
	
	public void setStoreOpen(boolean value) {
		this.isOpen = value; //FATTAAR INTE VARFÖR ÄN??
	}
	
	public int getnumRegister() {
		return numRegister;
	}
	
	public Customer createCustomer() {
		Customer customer = createCustomer.createCustomer();
		return customer;
	}
	
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
	// lägger till kund till butiken
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


}