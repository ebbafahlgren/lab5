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
	
	
	// returnerar nästa tid för en arrivalhändelse (Exponential)
	public double arrivalTime() {
		return customerArrived.next();
	}
	
	// returnerar nästa tid för en pickhändelse (Uniform)
	public double timePick() {
		return customerPick.next();
	}
	
	// returnerar nästa tid för en payhändelse (Uniform)
	public double timePay() {
		return customerPay.next();
	}

	public StoreState getStore() {
		return store;
	}
	
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
	
	private double timeBetweenEvent() {
		return currentTime - lastEventTime;
	}

	public void stopSimulation() {
		stopFlag = false;
	}
	
	public boolean getStopFlag() {
		return stopFlag;
	}

	public Event getEvent() {
		return currentEvent;
	}

	public double getTime() {
		return currentTime;
	}
	
	public double getCurrentTime() {
		return currentTime;
	}

	public Customer getCustomer() {
		return currentCustomer;
	}

	public double getLambda() {
		return lambda;
	}

	public double getPickMinTime() {
		return pickMinTime;
	}

	public double getPickMaxTime() {
		return pickMaxTime;
	}

	public double getPayMinTime() {
		return payMinTime;
	}

	public double getPayMaxTime() {
		return payMaxTime;
	}

	public long getSeed() {
		return seed;
	}

	public double getClosingTime() {
		return closingTime;
	}

	public double getLastPayEventTime() {
		return lastPaymentTime;
	}
	
	

}