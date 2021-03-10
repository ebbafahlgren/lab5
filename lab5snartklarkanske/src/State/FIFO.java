package State;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class FIFO {

	private ArrayList<Customer> queue;
	private int maxLength;

	public FIFO() {
		this.queue = new ArrayList<Customer>();
		this.maxLength = 0; 

	}
	
	// returnerar maximala antalet köande
	public int maxLength() {
		return maxLength;
	}
	
	public boolean isEmpty(){
		if(queue.size() == 0){
			return true;
		}
		return false;
	}
	
	public void removeFirst() {

		try {
			queue.remove(0);
		} catch (Exception IndexOutOfBoundException) {
			throw new NoSuchElementException();
		}
	}
	
	public int size() {
		return queue.size();
	}
	
	// Lägger till kunder till kön
	public void add(Customer c) {

		queue.add(c);
		addCustomers();

		if (maxLength < queue.size()) {

			maxLength += 1;
		}
	}
	
	//Håller koll på antalet i kön
	public void addCustomers() {
		maxLength++;
	}
	
	public String toString() {

		String returnString = "[ ";
		for (int i = 0; i < queue.size(); i++) {
			Customer c = queue.get(i);
			returnString += c.getCustoumerID() + " ";
		}
		returnString += "]";
		return returnString;

	}

	public Customer firstInLine() {
		// TODO Auto-generated method stub
		return queue.get(0);
	}
	
}