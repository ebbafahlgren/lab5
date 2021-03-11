package Simulator;
import Simulator.State;
import State.StoreState;
import Simulator.EventQueue;

import java.util.Random;

public class Optimize {

    /**
     * Runs first method with given parameters and returns the total amount of failed
     * payments.
     * @return the number of failed payments
     */
    public int metod1(double closingTime, int maxCustomers, int registers, double lambda, long seed, double maxPick,
                      double maxPay, double minPick, double minPay) {

        State store = new State(maxCustomers, registers, closingTime, lambda, seed, minPick, maxPick, minPay, maxPay);

        Simulator simulator = new Simulator(store, null); //Vet inte varf�r det ska va null d�r men det funkar
        simulator.run();
        return store.getStore().getTurnedAwayCustomers(); // H�r returnerar vi d� Alla misslyckade betalningar
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
        int maxCustomers = 5;
        double closingTime = 10;
        double minPick = 0.5;
        double maxPick = 1;
        double minPay = 2;
        double maxPay = 3;
        double lambda = 1;

        // Minsta antalet kassor f�r inte �verstiga maxCustomers
        int minAmountOfRegisters = maxCustomers;

        // Det ursprungliga antalet missade kunder, "kopplar ihop" allt till en int
        int customersMissed = metod1(closingTime, minAmountOfRegisters,  maxCustomers,
                lambda,  seed, minPick, maxPick, minPay, maxPay);
        //Vi loopar s� l�nge minsta antalet kassor �r minst 1
        while (minAmountOfRegisters > 0) {
            //Vi skapar en ny int och vi kollar om den skiljer sig fr�n det ursprungliga
            int getNewMissedCustomer = metod1(closingTime, minAmountOfRegisters,  maxCustomers,
                    lambda,  seed, minPick, maxPick, minPay, maxPay);

            if (customersMissed != getNewMissedCustomer) {
                return minAmountOfRegisters + 1;  // Om det skiljer sig s� var det f�reg�ende antalet det "optimalaste"
            }  // Vi forts�tter r�kna ner
                minAmountOfRegisters--;
        }
        return maxCustomers;
    }

    /**
     * Runs the second method with various seeds. Saving the max of the min
     * number of registers. The method continues until nothing has changed
     * after given number of iterations
     * @return the max of the min number of registers with the different
     *         seeds.
     */
    public int metod3(long seed) {
        Random random = new Random(seed);
        int counter = 0;
        int maxMinRegisters = 0;

        while (counter < 75) {
            int newAmountOfRegisters = metod2(random.nextLong());

            if (maxMinRegisters != Math.max(maxMinRegisters, newAmountOfRegisters)) {
                counter = 0;
            } else {
                counter ++;
            }
            maxMinRegisters = Math.max(maxMinRegisters, newAmountOfRegisters);
        }
        return maxMinRegisters;
    }

    public static void main(String[] args) {
        Optimize op = new Optimize();
        long seed = 12345;
        System.out.println("Optimal amount of registers: ");
        System.out.println(op.metod2(seed));
    }
}