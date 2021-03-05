package Simulator;

import State.Customer;

public abstract class Event {
	
	State state = new State();
	EventQueue eventqueue = new EventQueue();
	
	
	public Event(State state, EventQueue eventqueue) {
		// TODO Auto-generated constructor stub
		this.state = state;
		this.eventqueue = eventqueue;	
	}
	 
	

	abstract public void doThis();
	abstract public double getTime();
	abstract public Customer getCustoumer();
	

	 
	
	


}
