package Simulator;
import java.util.Observable;

import State.*;
import State.StoreState;
import Time.*;
import Time.Time;
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
		
	
	private ExponentialRandomStream customerArrived;
	private UniformRandomStream customerPick, customerPay;
	private double maxPickTime; 
	
	public State(int maxCustomers, int numRegisters, double closingTime, double lambda, long seed, double minPick, double maxPick, double minPay, double maxPay) {
		stopFlag = true;
		currentTime = 0;
		lastEventTime = 0;
		
		this.customerArrived = new ExponentialRandomStream(lambda, seed);
		this.customerPay = new UniformRandomStream(minPick, maxPick, seed);
		this.customerPick = new UniformRandomStream(minPay, maxPay, seed);
		
		this.maxCustomers = maxCustomers;
		this.numRegisters = numRegisters;
		this.closingTime = closingTime;
		
		this.seed = seed;
		this.pickMinTime = minPick;
		this.pickMaxTime = maxPick;
		this.payMinTime = minPay;
		this.payMaxTime = maxPay;
		this.lambda = lambda;
		
		System.out.println("Skapar butik");
		
		//Skapar butiken
		StoreState store = new StoreState(this.numRegisters, this.closingTime, this.maxCustomers);
		this.store = store;
		System.out.println(store + " detta är specifika butiken");
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

//	public State() {
//		StoreState store = new StoreState(numRegisters, closingTime, maxCustomers);
//		currentTime = 0;
//		lastEventTime = 0;
//	}

	public StoreState getStore() {
		return store;
	}
	
	public void update(Event thisEvent) {
		currentEvent = thisEvent;
		currentCustomer = thisEvent.getCustomer();
		
		lastEventTime = currentEventTime;
		currentTime = thisEvent.getTime();
		
		if(thisEvent.getClass() != Stop.class) {
			store.setRegisterFreeTime(store.getRegisterFreetime() + (timeBetweenEvent()*store.getAvailableRegisters()));
			store.setTotCustomerQueueTime(store.getCustomerQueueTime()+ (timeBetweenEvent()*store.getRegisterQueue().size()));
			
			if(thisEvent.getClass() == Pay.class) {
				store.setLastPaymentTime(thisEvent.getTime());
			}
		} else {
			
		}
		
		setChanged();
		notifyObservers();
	}
	
	private double timeBetweenEvent() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return currentTime;
	}

	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return currentCustomer;
	}

	public double getLambda() {
		// TODO Auto-generated method stub
		return lambda;
	}

	public double getPickMinTime() {
		// TODO Auto-generated method stub
		return pickMinTime;
	}

	public double getPickMaxTime() {
		// TODO Auto-generated method stub
		return pickMaxTime;
	}

	public double getPayMinTime() {
		// TODO Auto-generated method stub
		return payMinTime;
	}

	public double getPayMaxTime() {
		// TODO Auto-generated method stub
		return payMaxTime;
	}

	public long getSeed() {
		// TODO Auto-generated method stub
		return seed;
	}

	public double getClosingTime() {
		// TODO Auto-generated method stub
		return closingTime;
	}
	

}