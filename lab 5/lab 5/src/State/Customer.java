package State;
import State.CreateCustomer.customerStatus;
public class Customer {
	
	private int customerID;
	private customerStatus state;
	
	// Vet ej hur kunden ID skall tilldelas.
	public Customer(int customerInt) {
		this.customerID = customerInt;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	public customerStatus getState() {
		return state;
	}
	
	public void setState(customerStatus state) {
		this.state = state;
	}
	
	public String getID() {
		return String.valueOf(customerID);
	}
	
}
