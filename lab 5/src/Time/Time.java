package Time;

public class Time extends ExponentialRandomStream{
	private int pickMinTime;
	private int pickMaxTime;
	private int payMinTime;
	private int payMaxTime;
	private long arrivalTime;
	private long ourSeed;
	

	
	
	public Time() {
		// TODO Auto-generated constructor stub
	}
	
	Seed ourSeed = new Seed();
	public void arrivaltime() {
		ExponentialRandomStream(arrivaltime,ourSeed);
	}

}


public class Time {

	private ExponentialRandomStream customerArrived;
	private UniformRandomStream customerPick, customerPay; 
	
	public Time(double lambda, long seed, double minPick, double maxPick, 
			double minPay, double maxPay) {
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
	
