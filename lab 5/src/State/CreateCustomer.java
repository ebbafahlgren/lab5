package State;

public class CreateCustomer {

	private int customercount = 0;
	public Customer CreateCustomer() {
		// TODO Auto-generated constructor stub
		Customer customer = new Customer(customercount);
		customercount++;
		return customer;
	}
}
