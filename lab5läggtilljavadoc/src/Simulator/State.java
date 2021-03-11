package Simulator;
import java.util.Observable;

import State.*;
import State.StoreState;
import Time.*;
import Event.*; 

public class State extends Observable {
	
	private StoreState store;
	private boolean stopFlag;
	private double Time;
	private double currentTime;
	private double currentEventTime;
	//private Time time = new Time();
	
	private double lastEventTime;
	
	private UniformRandomStream payTime;
	private UniformRandomStream pickTime;
	private ExponentialRandomStream arrivalTime;
	
	private Event currentEvent;
	private Customer currentCustomer;
	
	private long seed;
	
	private double lambda;
	private double payMinTime;
	private double payMaxTime;
	private double pickMinTime;
	private double pickMaxTime;
	
	private int maxCustomers;
	private int numRegisters;
	private double closingTime;	
	private double lastPaymentTime;
		
	
	private ExponentialRandomStream customerArrived;
	private UniformRandomStream customerPick, customerPay;
	private double maxPickTime; 
	
	/**
	 * 
	 * @param maxCustomers max antal kunder i butiken
	 * @param numRegisters antal kassor i butiken
	 * @param closingTime stängningstiden
	 * @param lambda tidsvariabel
	 * @param seed startvärde
	 * @param minPick minsta plocktiden
	 * @param maxPick längsta plocktiden
	 * @param minPay minsta betaltiden
	 * @param maxPay längsta betaltiden
	 */
	public State(int maxCustomers, int numRegisters, double closingTime, double lambda, long seed, double minPick, double maxPick, double minPay, double maxPay) {
		stopFlag = true;
		lastEventTime = 0;
		currentTime = 0;
		
		this.customerArrived = new ExponentialRandomStream(lambda, seed);
		this.customerPay = new UniformRandomStream(minPay, maxPay, seed);
		this.customerPick = new UniformRandomStream(minPick, maxPick, seed);
		
		this.maxCustomers = maxCustomers;
		this.numRegisters = numRegisters;
		this.closingTime = closingTime;
		
		this.seed = seed;
		this.pickMinTime = minPick;
		this.pickMaxTime = maxPick;
		this.payMinTime = minPay;
		this.payMaxTime = maxPay;
		this.lambda = lambda;
		
		//System.out.println("Skapar butik");
		
		//Skapar butiken
		StoreState store = new StoreState(this.numRegisters, this.closingTime, this.maxCustomers);
		this.store = store;
		//System.out.println(store + " detta är specifika butiken");
	}	
	
	/**
	 * @return nästa tid för en ankomsthändelse
	 */
	public double arrivalTime() {
		return customerArrived.next();
	}
	
	/**
	 * @return nösta tid för en plockhändelse
	 */
	public double timePick() {
		return customerPick.next();
	}
	
	/**	
	 * @return nästa tid för en betalningshändelse
	 */
	public double timePay() {
		return customerPay.next();
	}
	
	/**
	 * @return store
	 */
	public StoreState getStore() {
		return store;
	}
	
	/**
	 * @param thisEvent uppdaterar eventet till det aktuella.
	 */
	public void update(Event thisEvent) {
		currentEvent = thisEvent;
		currentCustomer = thisEvent.getCustomer();
		
		lastEventTime = currentTime;
		currentTime = thisEvent.getTime();

		System.out.println(thisEvent.getClass());
		
		if(thisEvent.getClass() != Stop.class) {
			if(store.getAvailableRegisters() == 0) {
				
				store.setRegisterFreeTime(store.getRegisterFreetime());
				
				if(thisEvent.getClass() == Pay.class) {
					store.setLastPaymentTime(thisEvent.getTime());
				}
				
			} else {
				if(thisEvent.getClass() == Arrival.class && store.isStoreOpen() == "S" ) {
					System.out.println(" arrival event efter stängning");
					store.setRegisterFreeTime(store.getRegisterFreetime());
					
				} else {
					store.setRegisterFreeTime(store.getRegisterFreetime() + timeBetweenEvent()*store.getAvailableRegisters());
					System.out.println("i else satsen");
				}
			
				if(thisEvent.getClass() == Pay.class && store.isStoreOpen() == "S") {
					lastPaymentTime = currentTime;
					System.out.print("hej");
				}
			}
						
			store.setTotCustomerQueueTime(store.getCustomerQueueTime() + timeBetweenEvent()*store.getTotCustomersInRegisterQueue());	
		}
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @return tiden mellan event
	 */
	private double timeBetweenEvent() {
		return currentTime - lastEventTime;
	}
	
	/**
	 * stoppar simulatorn
	 */
	public void stopSimulation() {
		stopFlag = false;
	}
	
	/**
	 * @return stoppflaggan
	 */
	public boolean getStopFlag() {
		return stopFlag;
	}

	/**
	 * @return nuvarande event
	 */
	public Event getEvent() {
		return currentEvent;
	}

	/**
	 * @return nuvarande tid
	 */
	public double getTime() {
		return currentTime;
	}
	
	/**
	 * @return nuvarande tid
	 */
	public double getCurrentTime() {
		return currentTime;
	}
	
	/**
	 * @return nuvarande kund
	 */
	public Customer getCustomer() {
		return currentCustomer;
	}

	/**
	 * @return lambda
 	 */
	public double getLambda() {
		return lambda;
	}
	
	/**
	 * @return pickMinTime
	 */
	public double getPickMinTime() {
		return pickMinTime;
	}
	
	/**
	 * @return picMaxTime
	 */
	public double getPickMaxTime() {
		return pickMaxTime;
	}
	
	/**
	 * @return payMinTime
	 */
	public double getPayMinTime() {
		return payMinTime;
	}
	
	/**
	 * @return payMaxTime
	 */
	public double getPayMaxTime() {
		return payMaxTime;
	}

	/** 
	 * @return seed
	 */
	public long getSeed() {
		return seed;
	}

	/**
	 * @return closingTime
	 */
	public double getClosingTime() {
		return closingTime;
	}
	
	/**
	 * @return lastPaymentTime
	 */
	public double getLastPayEventTime() {
		return lastPaymentTime;
	}
	
	

}
