package State;

import State

public class CreateCustomer {

	private int customerCount = 0;
	
	public Customer createCustomer() {
		// TODO Auto-generated constructor stub
		Customer customer = new Customer(customerCount);
		customerCount++;
		return customer;
	}
	
	public enum customerStatus {
		inStore, notInStore, fullStore, walkedAway;
	}
}
