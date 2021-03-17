package Simulator;

import Simulator.State;
import State.StoreState;
import Simulator.K;
import Simulator.EventQueue;

import java.util.Random;

public class Optimize implements K{
	
//    int missedCustomers;
    int maxCustomers = K.M;
	double closingTime = K.END_TIME;
	double minPick = K.LOW_COLLECTION_TIME;
	double maxPick = K.HIGH_COLLECTION_TIME;
	double minPay = K.LOW_PAYMENT_TIME;
	double maxPay = K.HIGH_PAYMENT_TIME;
	double lambda = K.L;
	
//	int maxCustomers = 7;
//	double closingTime = 10;
//	double minPick = 0.5;
//	double maxPick = 1.0;
//	double minPay = 2.0;
//	double maxPay = 3.0;
//	double lambda = 2;

    //int seed = K.SEED;
    /**
     * Runs first method with given parameters and returns the total amount of
     * failed payments.
     *
     * @return the number of failed payments
     */
    public int metod1(int maxCustomers, int registers, double closingTime, double lambda, long seed, double minPick, double maxPick, double minPay, double maxPay, State state, EventQueue eventQueue) {
        //State state = new State();
        StoreState store = new StoreState(maxCustomers, registers, closingTime, lambda, seed, minPick, maxPick, minPay, maxPay, eventQueue);
        Simulator simulator = new Simulator(eventQueue, state, store); // Vet inte varför det ska va null där men det funkar
        simulator.run();
        System.out.println(store.getCustomerNotPayed() + " antal missade kunder hhhjhh");
        return store.getCustomerNotPayed(); // Här returnerar vi då alla misslyckade betalningar
        //
    }

    /**
     * Runs the first method until the minimum amount of registers are found without
     * getting a higher amount of missed customers. The amount of registers start
     * with the maximum amount of customers and iterates downwards until the optimal
     * amount of registers have been found.
     * <p>
     * Second method runs the first method until we find the min amount of registers
     *
     * @return optimal number of registers for this specific seed
     */
    public int metod2(long seed) {
        // Set parameters for the first method.

        EventQueue eventQueue = new EventQueue();
        State state = new State();

        // Minsta antalet kassor får inte överstiga maxCustomers
        int minAmountOfRegisters = maxCustomers;
        
        int optimalRegisters = 1;
        int lowestMissedCustomers = 0;
        

        // Det ursprungliga antalet missade kunder, "kopplar ihop" allt till en int
        System.out.println("första");
        
        //testar alla antal olika kassor från 1 till maxCustomers tills det minsta antal kassor ger minsta antalet 
        //som inte kunnat betala.
        
        // Vi loopar så länge minsta antalet kassor är minst 1
		while(true) {
			System.out.println(optimalRegisters + " börjar med 1 kassa");
			
			int test2 = metod1(maxCustomers, 2, closingTime, lambda, seed, minPick, maxPick, minPay, maxPay, state, eventQueue);			
			System.out.println(test2 + " antal missade kunder vid 2 kassor");
			
			int test3 = metod1(maxCustomers, 3, closingTime, lambda, seed, minPick, maxPick, minPay, maxPay, state, eventQueue);			
			System.out.println(test3 + " antal missade kunder vid 3 kassor");
			
	        int customersMissed = metod1(maxCustomers, optimalRegisters, closingTime, lambda, seed, minPick, maxPick, minPay, maxPay, state, eventQueue);			
			
            // Vi skapar en ny int och vi kollar om den skiljer sig från det ursprungliga
            //int getNewMissedCustomer = metod1(maxCustomers, optimalRegisters, closingTime, lambda, seed, minPick, maxPick, minPay, maxPay, state, eventQueue);
	        
	        if(lowestMissedCustomers > customersMissed) {
	        	lowestMissedCustomers = customersMissed;
	        }
	        
//            if (customersMissed > lowestMissedCustomers) {
//            	lowestMissedCustomers = customersMissed;
//            	//optimalRegisters = optimalRegisters;
//             // Om det skiljer sig så var det föregående antalet det "optimalaste"
//            } // Vi fortsätter räkna ner
//            
            System.out.println(customersMissed + " missade kunder");
            
            if(customersMissed == 0) {
            	System.out.println("missade kunder är 0");
            	break;
            }
            
            if(optimalRegisters == maxCustomers) {
            	break;
            }
            
            System.out.println("optimala antal kassor: " + optimalRegisters);
            optimalRegisters++;
        }
        return maxCustomers - optimalRegisters;
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
        System.out.println(op.metod2(seed));
      //  System.out.println(op.metod3(seed));
    }
}
