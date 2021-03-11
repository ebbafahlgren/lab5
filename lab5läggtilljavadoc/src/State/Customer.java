package State;

import State.CreateCustomer.customerStatus;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class Customer {
	
	private int customerID;
	private customerStatus state;
	
	/**
	 * @param customerInt ger en siffra till customerID
	 */
	public Customer(int customerInt) {
		this.customerID = customerInt;
	}
	
	/**
	 * @return customerID
	 */
	public int getCustomerID() {
		return customerID;
	}
	
	/**
	 * @return state
	 */
	public customerStatus getState() {
		return state;
	}
	
	/**
	 * @param state status uppdateras till det aktuelle
	 */
	public void setState(customerStatus state) {
		this.state = state;
	}

	/**
	 * @return strängen av customerID
	 */
	public String getID() {
		return String.valueOf(customerID);
	}
	
}
