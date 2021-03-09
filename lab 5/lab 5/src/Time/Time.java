package Time;

public class Time {//extends ExponentialRandomStream{

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
	
	
	
	
	
	
	
}