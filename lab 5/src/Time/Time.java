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
	// returnerar n�sta tid f�r en arrivalh�ndelse (Exponential)
	public double arrivalTime() {
		return customerArrived.next();
	}
	// returnerar n�sta tid f�r en pickh�ndelse (Uniform)
	public double timePick() {
		return customerPick.next();
	}
	// returnerar n�sta tid f�r en payh�ndelse (Uniform)
	public double timePay() {
		return customerPay.next();
	}
	
	
	
	
	
	
	
}