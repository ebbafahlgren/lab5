package Simulator;


public abstract class Event {
	
	State state = new State();
	EventQueue eventqueue = new EventQueue();
	
	
	public Event(State state, EventQueue eventqueue) {
		// TODO Auto-generated constructor stub
		this.state = state;
		this.eventqueue = eventqueue;	
	}
	
	public Double getTime() {
		return 
	}
	
	
	
	
	
	public void Arrival() {
		
	}
	public void Close() {
		
	}
	public void Pay() {
		
	}
	public void Pick() {
		
	}
	public void Start() {
		
	}
	public void Stop() {
		
	}

}
