package State;

import State.*;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class CreateCustomer {

	private int customerCount = 0;
	
	/**
	 * räknar kunder
	 * @return customer
	 */
	public Customer createCustomer() {
		// TODO Auto-generated constructor stub
		Customer customer = new Customer(customerCount);
		customerCount++;
		return customer;
	}
	
	/**
	 * enum customerStatus
	 */
	public enum customerStatus {
		/**inStore*/inStore,/**notInStore*/notInStore,/**fullStore*/fullStore,/**walkedAway*/ walkedAway;
	}
}
