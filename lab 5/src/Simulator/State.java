package Simulator;
import java.util.Observable;

import State.StoreState;
import Time.*;
import Event.*;

public class State extends Observable {
	
	private Store store;
	private Boolean StopFlag;
	private double Time;
	private double currentTime;
	
	private double currentTime;
	private double lastEventTime;
	
	private UniformRandomStream payTime;
	private UniformRandomStream pickTime;
	private ExponentialRandomStream arrivalTime;
	
	private Event currentEvent;
	private Customer currentCustomer;
	
//	private long seed;
//	
//	private double lambda;
//	private double payMinTime;
//	private double payMaxTime;
//	private double pickMinTime;
//	private double pickMaxTime;
	

	public State() {
		StoreState store = new StoreState();
		currentTime = 0;
		lastEventTime = 0;
	}
	
	public Store getStore() {
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
		
		setChanged();
		notifyObservers();
	}

}
