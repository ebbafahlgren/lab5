package Simulator;


import State.StoreState;
import View.StoreView;
import Simulator.State;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class RunSim {
	
	/**
	 * 
	 * @param args argumenen som används
	 */
   public static void main(String[] args) {
	   //Simulering 1
      long seed = 1234;
      int maxCustomers = 5;
      int registers = 2;
      double closingTime = 10;
      double minPick = 0.5;
      double maxPick = 1;
      double minPay = 2;
      double maxPay = 3;
      double lambda = 1;
      
//	   //Simulering 2
//      long seed = 13;
//      int maxCustomers = 7;
//      int registers = 2;
//      double closingTime = 8;
//      double minPick = 0.6;
//      double maxPick = 0.9;
//      double minPay = 0.35;
//      double maxPay = 0.6;
//      double lambda = 3;
        

      State state = new State(maxCustomers, registers, closingTime, lambda, seed, minPick, maxPick, minPay, maxPay);
      
      View view = new StoreView(state, state.getStore());
      
      Simulator sim = new Simulator(state, view);
            
      sim.run();
      
   }
}
