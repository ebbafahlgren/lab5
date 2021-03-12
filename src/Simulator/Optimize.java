package Simulator;

import Simulator.State;
import State.StoreState;
import Simulator.EventQueue;

import java.util.Random;

public class Optimize {

	/**
	 * Runs first method with given parameters and returns the total amount of
	 * failed payments.
	 * 
	 * @return the number of failed payments
	 */
	public int metod1(double closingTime, int maxCustomers, int registers, double lambda, long seed, double maxPick,
			double maxPay, double minPick, double minPay) {

		State state = new State(maxCustomers, registers, closingTime, lambda, seed, minPick, maxPick, minPay, maxPay);

		Simulator simulator = new Simulator(state, null); // Vet inte varför det ska va null där men det funkar
		simulator.run();
		return state.getStore().getTurnedAwayCustomers(); // Här returnerar vi då Alla misslyckade betalningar
	}

	/**
	 * Runs the first method until the minimum amount of registers are found without
	 * getting a higher amount of missed customers. The amount of registers start
	 * with the maximum amount of customers and iterates downwards until the optimal
	 * amount of registers have been found.
	 *
	 * Second method runs the first method until we find the min amount of registers
	 *
	 * @return optimal number of registers for this specific seed
	 */
	public int metod2(long seed) {
		// Set parameters for the first method.
		// Exempel 4 - ska bli 14 rätt
//        int maxCustomers = 100;
//        double closingTime = 20;
//        double minPick = 0.45d;
//        double maxPick = 0.65d;
//        double minPay = 0.2d;
//        double maxPay = 0.3d;
//        double lambda = 50;

		// Exempel 2 - ska bli 3 rätt
		int maxCustomers = 7;
		double closingTime = 10;
		double minPick = 0.5;
		double maxPick = 1.0;
		double minPay = 2.0;
		double maxPay = 3.0;
		double lambda = 2;

		// Exempel 1 - ska bli 4 rätt
//        int maxCustomers = 5;
//        double closingTime = 10;
//        double minPick = 0.5;
//        double maxPick = 1.0;
//        double minPay = 2.0;
//        double maxPay = 3.0;
//        double lambda = 1;

		// Minsta antalet kassor får inte överstiga maxCustomers
		int minAmountOfRegisters = maxCustomers;

		// Det ursprungliga antalet missade kunder, "kopplar ihop" allt till en int

		int customersMissed = metod1(closingTime, maxCustomers, minAmountOfRegisters, lambda, seed, maxPick, maxPay,
				minPick, minPay);

		// Vi loopar så länge minsta antalet kassor är minst 1
		while (minAmountOfRegisters > 0) {
			// Vi skapar en ny int och vi kollar om den skiljer sig från det ursprungliga
			int getNewMissedCustomer = metod1(closingTime, maxCustomers, minAmountOfRegisters, lambda, seed, maxPick,
					maxPay, minPick, minPay);

			if (customersMissed != getNewMissedCustomer) {
				return minAmountOfRegisters + 1; // Om det skiljer sig så var det föregående antalet det "optimalaste"
			} // Vi fortsätter räkna ner
			minAmountOfRegisters--;
		}
		return maxCustomers;
	}

	/**
	 * Runs the second method with various seeds. Saving the max of the min number
	 * of registers. The method continues until nothing has changed after given
	 * number of iterations
	 * 
	 * @return the max of the min number of registers with the different seeds.
	 */
	public int metod3(long seed) {
		Random random = new Random(seed);
		int counter = 0;
		int maxMinRegisters = 0;

		while (counter < 100) {
			int newAmountOfRegisters = metod2(random.nextLong());

			if (maxMinRegisters != Math.max(maxMinRegisters, newAmountOfRegisters)) {
				counter = 0;
			} else {
				counter++;
			}
			maxMinRegisters = Math.max(maxMinRegisters, newAmountOfRegisters);
		}
		return maxMinRegisters;
	}

	public static void main(String[] args) {
		Optimize op = new Optimize();
		long seed = 1234;
		System.out.println("Optimal amount of registers: ");
		// System.out.println(op.metod2(seed));
		System.out.println(op.metod3(seed));
	}
}