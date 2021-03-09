package View;

import Event.*;
import State.*;
import Simulator.*;

import java.util.Observable;

/**
 * This class prints information every time a event is called
 * also in the beginning and end.
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */ 

public class StoreView extends View{
   private State state;
   private StoreState store;

   public StoreView(State state, StoreState store){
      super(state, store);
      this.state = state;
      this.store = store;
   }

   private void firstPrint()
   {
      System.out.println("PARAMETRAR");
      System.out.println("==========");
      System.out.println("Antal kassor, N..........: " + store.getAvailableRegisters());
      System.out.println("Max som ryms, M..........: " + store.getMaxCustomer());
      System.out.println("Ankomshastighet, lambda..: " + state.getLambda());
      System.out.println("Plocktider, [P_min..Pmax]: [" + state.getMinPickTime() + ".." + state.getMaxPickTime() + "]");
      System.out.println("Betaltider, [K_min..Kmax]: [" + state.getMinPayTime() + ".." + state.getMaxPayTime() + "]");
      System.out.println("Frö, f...................: " + state.getSeed());
      System.out.println("");
      System.out.println("FÖRLOPP");
      System.out.println("=======");
      System.out.println("Tid\tHändelse\tKund\tÖ\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]");
     
   }

   private void lastPrint()
   {
      System.out.println("");
      System.out.println("RESULTAT");
      System.out.println("========");
      System.out.println("");
      System.out.println("1) Av " +
              store.totalCustomers() + " handlade " +
              store.numCustomerPayed() + " medan " +
              store.getCustomersTurnedAway() + " missade.");
      System.out.println("");
      System.out.println("2) Total tid " +
              store.getAvailableRegisters() + " kassor varit lediga: " +
              formatNumber(store.getRegisterFreetime()) + " te.");
      System.out.println("\tGenomsnittlig ledig kassatid: " +
              formatNumber(store.getRegisterFreetime() / store.getAvailableRegisters()) + " te (dvs " +
              formatNumber((store.getRegisterFreetime() / store.getAvailableRegisters()) / (store.getLastPaymentTime()) * 100) + "% av tiden från" +
              " öppning tills sista kunden betalat).");
      System.out.println("");
      System.out.println("3) Total tid " +
              store.getTotalCustomersInQueue() + " kunder tvingats köa: " +
              formatNumber(store.getCustomerQueueTime()) + " te.");
      System.out.println("\tGenomsnittlig kötid: " +
              formatNumber(store.getCustomerQueueTime() / store.getTotalCustomersInQueue()) + " te.");
   }

   private void printEvent()
   {

       String formatCurrEvent = "" + state.getCurrentEvent().getName();
       if(formatCurrEvent.length() < 4){
           formatCurrEvent = formatCurrEvent + " ";
       }

       String checkCustomerNull = "" + state.getCurrentCustomer();
       if(checkCustomerNull.equals("null")){
           checkCustomerNull = " ";
       }

      System.out.println(
              formatNumber(state.getCurrentTime()) + "\t" +
              formatCurrEvent + "\t\t" +
              checkCustomerNull + "\t" +
              store.getIsOpen() + "\t" +
              store.getFreeRegisters() + "\t" +
              formatNumber(store.getRegisterFreetime()) + "\t" +
              store.getCustomersInStore() + "\t" +
              store.getCoinMade() + "\t" +
              store.getCustomersTurnedAway() + "\t" +
              store.getTotalCustomersInQueue() + "\t" +
              formatNumber(store.getCustomerQueueTime()) + "\t" +
              store.getRegisterQueue().size() + "\t" +
              store.getRegisterQueue());
   }

   /**
    *
    * Formats a inputed number so that it always has two numbers
    * after the decimal.
    * @param time
    * @return
    */

   private String formatNumber(double time){

       String correctTime = "" + (Math.round(time * 100.0) / 100.0);
       if(correctTime.length() < 4){
           correctTime = correctTime + "0";
       }

       return correctTime;
   }

   @Override
   public void update(Observable o, Object arg)
   {
      if (state.getCurrentEvent().getClass() == Start.class)
      {
         firstPrint();
         System.out.println(state.getCurrentTime() + "\tStart");
      } else if (state.getCurrentEvent().getClass() == Stop.class)
      {
         System.out.println(formatNumber(state.getCurrentTime()) + "\tStop");
         lastPrint();
      } else
      {
         printEvent();
      }
   }
}