package State;

import java.util.ArrayList;
import Event.*;
import Simulator.Event;
import Simulator.EventQueue;
import Simulator.State;
import State.CreateCustomer.customerStatus;
import State.Customer;
import Time.ExponentialRandomStream;
import Time.UniformRandomStream;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class StoreState extends State {
	private double lastPaymentTime = 0;
	private double currentEventTime = 0;
	private double lastEventTime = 0;
	private double totalQueueTime = 0;
	private double totalRegisterTime = 0;
	private int currentCustomers = 0;


	private ArrayList<Customer> customerList = new ArrayList<>();
	private EventQueue eventQueue;
	private StoreState store;
	private Customer currentCustomer;

	private int maxCustomers;
	private double closingTime;
	private double currentTime;

	private long seed;
	private double lambda;
	private double payMinTime;
	private double payMaxTime;
	private double pickMinTime;
	private double pickMaxTime;

	private int numRegister; // antal kassor
	private boolean isOpen; //kollar om butiken är öppen
   
	// Queue of customers waiting in line
	private FIFO registerQueue;

   
	private int customerPayed;
	private int customerNotPayed;
	private int availableRegisters; //Free registers
	private int maxRegisters;
   
	private double registerFreetime;
   	private int totCustomersInRegisterQueue;


	/**
	 * @param registers antal kassor
 	 * @param closingTime stängningstid
	 * @param maxCustomers max antal kunder
	 * @param lambda ankomsthastighet
	 * @param seed frö
	 * @param minPick minsta plocktiden
	 * @param maxPick max plocktiden
	 * @param maxPay max betalningstid
	 * @param minPay minsta betalningstid
	 * @param eventQueue eventkön
	 */
	public StoreState(int maxCustomers, int registers, double closingTime, double lambda,
					  long seed, double minPick, double maxPick,
					  double minPay, double maxPay, EventQueue eventQueue) {

		this.closingTime = closingTime;
		this.maxCustomers = maxCustomers;
		this.seed = seed;
		this.pickMinTime = minPick;
		this.pickMaxTime = maxPick;
		this.payMinTime = minPay;
		this.payMaxTime = maxPay;
		this.lambda = lambda;
		this.eventQueue = eventQueue;
		this.maxRegisters = registers;
		availableRegisters = registers;
		isOpen = true;
		registerQueue = new FIFO();

	}

	/**
	 * Lägger till kund i en ArrayList
	 * @param customer lägger till kund
	 */

	public void addCustomerToArray(Customer customer) {
		customerList.add(customer);
	}

	/**
	 * Räknar antalet lyckade betalningar
	 */
	public void addCustomerPayed() {
		customerPayed++;
	}

	/**
	 * Räknar antalet misslyckade betalningar
	 */
	public void addCustomerNotPayed() {
		customerNotPayed++;
	}

	/**
	 * Räknar antalet kunder i butiken
	 */
	public void addCurrentCustomers() {
		currentCustomers++;
	}

	/**
	 * Räknar antalet lediga kassor
	 */
	public void addAvailableRegisters() {
		availableRegisters++;
	}

	/**
	 * En setter som stänger butiken
	 */
	public void setStoreClose() {
		isOpen = false;
	}

	/**
	 * Kollar om butiken är öppen eller stängd
	 * @return isOpen
	 */
	public boolean isStoreOpen() {
		return isOpen;
	}
	public int getQueue(){
		return registerQueue.size();
	}

	/**
	 * Tar bort customers från butiken
	 */
	public void removeCurrentCustomer() {
		currentCustomers--;
	}

	/**
	 * Tar bort lediga kassor
	 */
	public void removeAvailableRegisters() {
		availableRegisters--;
	}

	/**
	 * Returnerar ankomsthastigheten lambda
	 * @return lambda
	 */
	public double getLambda() {
		return lambda;
	}

	/**
	 * Returnerar fröet
	 * @return seed
	 */
	public long getSeed() {
		return seed;
	}
	/**
	 * Returnerar minsta plocktiden
	 * @return pickMinTime
	 */
	public double getPickMinTime() {
		return pickMinTime;
	}

	/**
	 * Returnerar maximala plocktiden
	 * @return pickMaxTime
	 */
	public double getPickMaxTime() {
		return pickMaxTime;
	}

	/**
	 * Returnerar minsta betalningstiden
	 * @return payMinTime
	 */
	public double getPayMinTime() {
		return payMinTime;
	}

	/**
	 * Returnerar högsta betalningstiden
	 * @return payMaxTime
	 */
	public double getPayMaxTime() {
		return payMaxTime;
	}

	/**
	 * Returnerar närvarande kunder
	 * @return currentCustomers
	 */
	public int getCurrentCustomers() {
		return currentCustomers;
	}

	/**
	 * Returnerar tiden för stängning
	 * @return closingTime
	 */
	public double getClosingTime() {
		return closingTime;
	}

	/**
	 * Returnerar max antal kunder som får vistas i butiken
	 * @return maxCustomers
	 */
	public int getMaxCustomers() {
		return maxCustomers;
	}

	/**
	 * Returnerar max antal registers
	 * @return maxRegisters
	 */
	public int getMaxRegisters() {
		return maxRegisters;
	}

	/**
	 * Hämtar lediga kassor
	 * @return availableRegisters
	 */
	public int getAvailableRegisters() {
		return availableRegisters;
	}

	/**
	 * Hämtar antalet lyckade betalningar
	 * @return customerPayed
	 */
	public int getCustomerPayed() {
		return customerPayed;
	}

	/**
	 * Returnerar antalet misslyckade betalningar
	 * @return customerNotPayed
	 */
	public int getCustomerNotPayed() {
		return customerNotPayed;
	}

	/**
	 * Returnerar både lyckade och misslyckade betalningar
	 * @return customerPayed + customerNotPayed
	 */
	public int getTotalPayments() {
		return  customerPayed + customerNotPayed;
	}

	/**
	 * Uppdaterar kassatiden allt eftersom betalningar görs
	 * @param currentTime
	 * @param lastEventTime
	 */

	public void updateTotRegisterTime(double currentTime, double lastEventTime) {
		if(isStoreOpen()) {
			totalRegisterTime += (currentTime - lastEventTime)* getAvailableRegisters();
		} else {
			int count = 0;
			ArrayList<Event> eventList = eventQueue.getList();
			for(Event event : eventList) {
				if(event.writeOut().equals("Paying")) {
					count++;
					lastPaymentTime = event.getTime();
				}
			}
			if(count > 0) {
				totalRegisterTime += (currentTime - lastEventTime)* getAvailableRegisters();
			}
		}
	}

	/**
	 * Uppdaterar den totala kötiden mha den nuvarande tiden minus eventtiden
	 * @param currentTime
	 * @param lastEventTime
	 */
	public void updateTotQueueTime(double currentTime, double lastEventTime) {
		totalQueueTime += (currentTime - lastEventTime) * registerQueue.size();
	}

	/**
	 * Uppdaterar tiden kontinuerligt så att tiderna räknas rätt för varje event
	 * @param time
	 */
	public void updateTime(double time) {
		lastEventTime = currentEventTime;
		currentEventTime = time;
	}

	/**
	 * Returnerar tiden för förra eventhändelsen
	 * @return lastEventTime
	 */
	public double getLastEventTime() {
		return lastEventTime;
	}

	/**
	 * Returnerar tiden för den nuvarande eventet
	 * @return currentEventTime
	 */
	public double getCurrentEventTime(){
		return currentEventTime;
	}

	/**
	 * Returnerar förra tiden för betalning
	 * @return lastPaymentTime
	 */
	public double getLastPaymentTime(){
		return lastPaymentTime;
	}

	/**
	 * Returnerar totala kassatiden
	 * @return totalRegisterTime
	 */
	public double getTotalRegisterTime() {
		return totalRegisterTime;
	}

	/**
	 * Returnerar totala kötiden
	 * @return totalQueueTime
	 */
	public double getTotalQueueTime() {
		return totalQueueTime;
	}

	/**
	 * Returnerar största storleken
	 * @return maxLength
	 */
	public int getMaxSize() {
		return registerQueue.maxLength();
	}

	/**
	 * returnerar fifokön
	 * @return registerQueue
	 */
	public FIFO getTheFIFO() {
		return registerQueue;

	}

	/**
	 * Returnerar kön som en string
	 * @return string kö
	 */
	public String getPrintedQueue() {
		return registerQueue.toString();
	}

		public double getRegisterFreetime() {
		return registerFreetime;
	}

	/**
	 *
	 * @return totala antalet i kön
	 */
	public int getCustomerInQueueTot() {
		return registerQueue.getCustomerInQueueTot();
	}

	/**
	 * getstore
	 * @return store
	 */
	public StoreState getStore() {
		return store;
	}

	/**
	 * Returnerar den specifika kunden
	 * @return currentCustomer
	 */
	public Customer getCustomer() {
		return currentCustomer;
	}





}







