package State;

import java.util.ArrayList;

import Simulator.State;

public class StoreState extends State {
	
	private int maxCustomer;
	private int numRegister; // antal kassor
	private double closingTime;
	private boolean open;
		
	// customers holds all customers, even the ones that aren't in the store.
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
		
		open = false; // Start with a closed store
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
		return open;
	}
	
	public void setStoreOpen() {
		this.open = open; //FATTAAR INTE VARFÖR ÄN??
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
	
	public double getregisterFreetime() {
		return registerFreetime;
	}
	
	public FIFO getregisterQueue() {
		return registerQueue;
	}
	
	public int customerPayed() {
		return customerPayed;
	}
	
	public int getmaxCustomer() {
		return maxCustomer;
	}
	
	public double getclosingTime() {
		return closingTime;
	}
	
	public int getavailableRegisters() {
		return availableRegisters;
	}
	
	

}