package Simulator;


import State.StoreState;
import View.StoreView;
import Simulator.State;

/**
 * Runs a standard supermarket that will print the events that unfold.
 * The seed for the random number generator will be a set seed, meaning
 * the output will be the same each run.
 * 
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */

public class RunSim {

   public static void main(String[] args) {
      long seed = 1234;
      int maxCustomers = 5;
      int registers = 2;
      double closingTime = 10;
      double minPick = 0.5;
      double maxPick = 1;
      double minPay = 2;
      double maxPay = 3;
      double lambda = 1;
        

      State state = new State(maxCustomers, registers, closingTime, lambda, seed, minPick, maxPick, minPay, maxPay);
      //System.out.println("har skapat state");
      
      View view = new StoreView(state, state.getStore());
      //System.out.println("har skapat view");
      
      Simulator sim = new Simulator(state, view);
      
      //System.out.println("Nu vill gå in i run");
      
      sim.run();
      
   }
}