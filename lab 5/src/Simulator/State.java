package Simulator;
import java.util.Observable;

import State.*;
import State.StoreState;
import Time.*;
import Time.Time;
import Event.*; 

public class State extends Observable {
	
	private StoreState store;
	private Boolean StopFlag;
	private double Time; //tid butiken ska vara öppen?
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
	
	public void Time(double lambda, long seed, double minPick, double maxPick, double minPay, double maxPay) {
		this.customerArrived = new ExponentialRandomStream(lambda, seed);
		this.customerPay = new UniformRandomStream(minPick, maxPick, seed);
		this.customerPick = new UniformRandomStream(minPay, maxPay, seed);
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

	public State() {
		StoreState store = new StoreState(numRegisters, closingTime, maxCustomers);
		currentTime = 0;
		lastEventTime = 0;
		closingTime = closingTime;
	}
	
	public double getClosingTime() {	
		return closingTime;
	}

	public StoreState getStore() {
		return store;
	}
	
	public void update(Event thisEvent) {
		currentEvent = thisEvent;
		
		
		lastEventTime = currentEventTime;
		currentTime = thisEvent.getTime();

		
		if(thisEvent.getClass() != Stop.class) {
			
			
			if(thisEvent.getClass() == Pay.class) {
				store.setLastPaymentTime(thisEvent.getTime());
			}
		} else {
			
		}
		
		if (stopFlag == true) {
			turnOfSimulator();
		}
		
		if (time > closingTime) {
			store.
		}
		
		setChanged();
		notifyObservers();
	}

	public void turnOfSimulator() {
		// TODO Auto-generated method stub
		simulator.stop
	}


}
