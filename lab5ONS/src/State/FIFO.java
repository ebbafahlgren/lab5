package State;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class FIFO {

	private int customersQueued = 0;
	private int maxLength;
	private ArrayList<Customer> queue;
	/**
	 * FIFOklass som använder arraylist
	 */
	public FIFO() {
		queue = new ArrayList<Customer>();

	}
	/**
	 * @return maxlängden i FIFO
	 */
	// returnerar maximala antalet köande
	public int maxLength() {
		return maxLength;
	}
	
	/**
	 * kollar om FIFO ör tom
	 * @return true om den är det
	 */
	public boolean isEmpty(){
		if(queue.size() == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * tar bort första elementet i listan
	 */
	public void removeFirst() {

		try {
			queue.remove(0);
		} catch (Exception IndexOutOfBoundException) {
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * @return storleken på kön
	 */
	public int size() {
		return queue.size();
	}
	
	/**
	 * 
	 * @param c lägger till kunder i kön
	 */
	public void add(Customer c) {
		queue.add(c);
		addCustomers();

		if (maxLength < queue.size()) {

			maxLength += 1;
		}
	}
	
	/**
	 * addCustomers håller koll på antalet i kön
	 */
	public void addCustomers() {
		customersQueued++;
	}
	
	public int getCustomerInQueueTot() {
		return customersQueued;
	}
	
	/**
	 * returnerar en textrad för alla värden mindre än köstorleken. 
	 * @return returnString
	 */
	public String toString() {

		StringBuilder returnString = new StringBuilder("[ ");
		for (Customer c : queue) {
			returnString.append(c.getID()).append(" ");
		}
		returnString.append("]");
		return returnString.toString();

	}

	/**
 	* firstInLine
 	* @return första värdet i i kön
 	*/
	public Customer firstInLine() {
		try {
			return queue.get(0);
		} catch (Exception IndexOutOfBoundException) {
			throw new NoSuchElementException();
		}


	}
	
}
