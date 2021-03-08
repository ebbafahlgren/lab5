package State;

import java.util.ArrayList;

import Simulator.State;

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
   
	private int customerPayed;
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
		Customer customer = createCustomer.CreateCustomer();
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
	
	public int customerPayed() {
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
	
	public void addCustomer() {
		if(isOpen == false) {
			
		} else if (getCustomersInStore() < maxCustomer){
			customer.setState(CustomerState.turnedAway)
		}
	}
}
