package Simulator;

import Simulator.State;
import State.StoreState;
import Simulator.EventQueue;

import java.util.Random;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

/**
* Optimize opitmizes the simulator to the optimal amount of registers
*/

public class Optimize implements K{
    int missedCustomers;
    
	int maxCustomers = K.M;
	double closingTime = K.END_TIME;
	double minPick = K.LOW_COLLECTION_TIME;
	double maxPick = K.HIGH_COLLECTION_TIME;
	double minPay = K.LOW_PAYMENT_TIME;
	double maxPay = K.HIGH_PAYMENT_TIME;
	double lambda = K.L;

    /**
     * Runs first method with given parameters and returns the total amount of
     * failed payments.
     * @param maxCustomers maximum number of customers
     * @param registers number of registers
     * @param closingTime the time that the store closes
     * @param lambda expected value of customers arrival
     * @param seed the seed sent in
     * @param minPick the minumum pick time
     * @param maxPick the maximum pick time
     * @param minPay the minumum pay time
     * @param maxPick the maximum pay time
     * @param state the general state
     * @param eventQueue the queue of events
     * @return the number of failed payments
     */
    public static int metod1(int maxCustomers, int registers, double closingTime, double lambda,
                      long seed, double minPick, double maxPick,
                      double minPay, double maxPay, State state, EventQueue eventQueue) {

        //State state = new State();
        StoreState store = new StoreState(maxCustomers, registers, closingTime, lambda, seed, minPick,
                maxPick, minPay, maxPay, eventQueue);
        Simulator simulator = new Simulator(eventQueue, state, store); // Vet inte varför det ska va null där men det funkar
        simulator.run();
        return store.getCustomerNotPayed(); // Här returnerar vi då Alla misslyckade betalningar
    }

    /**
     * Runs the first method until the minimum amount of registers are found without
     * getting a higher amount of missed customers. The amount of registers start
     * with the maximum amount of customers and iterates downwards until the optimal
     * amount of registers have been found.
     * Second method runs the first method until we find the min amount of registers
     * @param seed fröt som skickas in
     * @return optimal number of registers for this specific seed
     */
    public int metod2(long seed) {
        // Set parameters for the first method.

        long seed2 = seed;


        // Minsta antalet kassor får inte överstiga maxCustomers
        int amountOfRegisters = maxCustomers / 2;
        int getNewMissedCustomer = 0;
        
        EventQueue eventQueue = new EventQueue();
        State state = new State();
        
        int optimal = metod1(maxCustomers, maxCustomers, closingTime, lambda, seed2, minPick,
                maxPick, minPay, maxPay, state, eventQueue);
        
        
        int step = maxCustomers / 4;
        int prevStep = 0;
        
        int counter = 0;

        // Vi loopar så länge minsta antalet kassor är minst 1
        while (step != prevStep) {
        	counter++;
        	
        	eventQueue = new EventQueue();
            state = new State();
            // Vi skapar en ny int och vi kollar om den skiljer sig från det ursprungliga
        	
            getNewMissedCustomer = metod1(maxCustomers, amountOfRegisters, closingTime, lambda, seed2, minPick,
                    maxPick, minPay, maxPay, state, eventQueue);


            if (getNewMissedCustomer == optimal) {
            	amountOfRegisters -= step;
               }
            else {
            	amountOfRegisters += step;
            	prevStep = step;
            }

            step = (int) Math.ceil(step / 2.0);
            
           
        }
        
        eventQueue = new EventQueue();
        state = new State();
        
        if (metod1(maxCustomers, amountOfRegisters, closingTime, lambda, seed2, minPick,
                maxPick, minPay, maxPay, state, eventQueue) != optimal) {
        	amountOfRegisters++;
        	
        }
        eventQueue = new EventQueue();
            state = new State();
        	getNewMissedCustomer = metod1(maxCustomers, amountOfRegisters, closingTime, lambda, seed2, minPick,
                    maxPick, minPay, maxPay, state, eventQueue);
        System.out.println("Minsta antalet kassor som ger minimalt antal missade " +"(" +
        getNewMissedCustomer + ")" + " : " + amountOfRegisters );
        System.out.println("Counter: " + counter);
        return getNewMissedCustomer;
    }

    /**
     * Runs the second method with various seeds. Saving the max of the min number
     * of registers. The method continues until nothing has changed after given
     * number of iterations
     * @param seed är fröt vi skickar in
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
	/**
	* main does the optimization
	* @param args runs the main function
	*/

    public static void main(String[] args) {
        Optimize op = new Optimize();
        long seed = 42;
        op.metod2(seed);
        //System.out.println(op.metod3(seed));
    }
}
