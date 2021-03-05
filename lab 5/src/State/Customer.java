package State;

public class Customer {
	
	private int customerID;
//	private customer state;

	public Customer(int customerInt) {
		this.customerID = customerInt;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	public CustomerState getState() {
		return state;
	}
	
	public void setState(CustomerState state) {
		this.state = state;
	}

}
