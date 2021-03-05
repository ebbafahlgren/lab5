
package State;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FIFO {

	private ArrayList<Customer> queue;
	private int maxLength;

	public FIFO() {
		this.queue = new ArrayList<Customer>();
		this.maxLength = 0; 

	}

//	public void add(Customer c) {
//		queue.add(c);
//		maxLength = Math.max(maxLength, queue.size());
	
	
	// returnerar maximala antalet k�ande
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
	// L�gger till kunder till k�n
	public void add(Customer c) {

		queue.add(c);
		addCustomers();

		if (maxLength < queue.size()) {

			maxLength += 1;
		}
	}
	//H�ller koll p� antalet i k�n
	public void addCustomers() {
		maxLength++;
	}
	
	public String toString() {

		String returnString = "[ ";
		for (int i = 0; i < queue.size(); i++) {
			Customer c = queue.get(i);
			returnString += c.getCustomerID() + " ";
		}
		returnString += "]";
		return returnString;

	}
	
}