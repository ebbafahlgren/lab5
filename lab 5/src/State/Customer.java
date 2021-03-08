package State;

import State.CreateCustomer.customerStatus;

public class Customer {
	
	private int customerID;
	private customerStatus state;

	//Vet inte hur kundens id ska tilldelas
	public Customer(int customerInt) {
		this.customerID = customerInt;
	}
	
	public int getCustoumerID() {
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
