package State;

import State.*;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class CreateCustomer {

	private int customerCount = 0;
	
	/**
	 * skapar kunder och räknar dem så de får ett ID
	 * @return customer
	 */
	public Customer createCustomer() {
		Customer customer = new Customer(customerCount);
		customerCount++;
		return customer;
	}
	
	/**
	 * enum customerStatus
	 */
	public enum customerStatus {
		/**inStore*/inStore, /**notInStore*/notInStore, /**fullStore*/fullStore, /**walkedAway*/ walkedAway;
	}
}
