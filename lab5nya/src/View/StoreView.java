package View;

import java.text.DecimalFormat;

import Event.*;
import State.*;
import simulator.View;
import simulator.*;

import java.util.Observable;
import java.util.*;
/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class StoreView extends View {
	private State state;
	private StoreState store;
	private EventQueue eventQueue;

	DecimalFormat df = new DecimalFormat("####0.00");
	DecimalFormat df2 = new DecimalFormat("#0");
	   /**
   	   * 
  	   * @param state statusen pÃ¥ butiken 
  	   * @param store store
   	   */
	public StoreView(State state, StoreState store, EventQueue eventQueue) {
		//super(state, store, eventQueue);

		this.store = store;
		this.eventQueue = eventQueue;
	}
	
	  /**
   	   * skriver ut första utskiften i vyn
    	   */
	  private String openOrNot() {
		  String x = "S";
		  if (store.isStoreOpen()) {
			  x = "Ö";
		  }
		  return x;
	  }
	  
	  
		private void firstPrint() {
			System.out.println("PARAMETRAR");
			System.out.println("==========");
			System.out.println("Antal kassor, N..........: " + store.getAvailableRegisters());
			System.out.println("Max som ryms, M..........: " + store.getMaxCustomers());
			System.out.println("Ankomshastighet, lambda..: " + store.getLambda());
			System.out
					.println("Plocktider, [P_min..Pmax]: [" + store.getPickMinTime() + ".." + store.getPickMaxTime() + "]");
			System.out.println("Betaltider, [K_min..Kmax]: [" + store.getPayMinTime() + ".." + store.getPayMaxTime() + "]");
			System.out.println("Frö, f...................: " + store.getSeed());
			System.out.println("");
			System.out.println("FÖRLOPP");
			System.out.println("=======");
			System.out.println("Tid\thändelse\tKund\tÖ\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]");

		}

//	  public void lastPrint() {
//
//		  System.out.printf("%-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
//				  df.format(eventQueue.getCurrentEvent().getTime()), eventQueue.writeOut(), eventQueue.getCurrentEvent().getCustomerID(),
//				  openOrNot(), store.getFreeRegisters(), df.format(store.getRegisterTime()),
//				  df2.format(store.getPresentCustomers()), store.getSuccessfulPurchase(), store.getFailedPurchase(),
//				  store.getCustomersQueued()
//
//				  , df.format(store.getCollectedQueueTime()), store.getFIFO().size(), store.getPrintedLine());
//
//	  }
//	private void firstPrint() {
//		System.out.println("PARAMETRAR");
//		System.out.println("==========");
//		System.out.println("Antal kassor, N..........: " + store.getAvailableRegisters());
//		System.out.println("Max som ryms, M..........: " + store.getMaxCustomers());
//		System.out.println("Ankomshastighet, lambda..: " + store.getLambda());
//		System.out
//				.println("Plocktider, [P_min..Pmax]: [" + store.getPickMinTime() + ".." + store.getPickMaxTime() + "]");
//		System.out.println("Betaltider, [K_min..Kmax]: [" + store.getPayMinTime() + ".." + store.getPayMaxTime() + "]");
//		System.out.println("Frö, f...................: " + store.getSeed());
//		System.out.println("");
//		System.out.println("FÖRLOPP");
//		System.out.println("=======");
//		System.out.println("Tid\thändelse\tKund\tÖ\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]");
//
//	}
	
	 /**
   	  * skriver ut andra utskriften i view
   	  */
	private void lastPrint() {
		System.out.println("");
		System.out.println("RESULTAT");
		System.out.println("========");
		System.out.println("");
		System.out.println("1) Av " + store.totalCustomers() + " handlade " + store.getCustomerPayed() + " medan "
				+ store.getCustomerNotPayed() + " missade.");
		System.out.println("");
		System.out.println("2) Total tid " + store.getAvailableRegisters() + " kassor varit lediga: "
				+ formatNumber(store.getRegisterFreetime()) + " te.");
		System.out.println("\tGenomsnittlig ledig kassatid: "
				+ formatNumber(store.getRegisterFreetime() / store.getAvailableRegisters()) + " te (dvs "
				+ formatNumber((store.getRegisterFreetime() / store.getAvailableRegisters())
						/ (store.getLastPaymentTime()) * 100)
				+ "% av tiden från" + " öppning tills sista kunden betalat).");
		System.out.println("");
		System.out.println("3) Total tid " + store.getTotCustomersInRegisterQueue() + " kunder tvingats köa: "
				+ formatNumber(store.getTotalQueueTime()) + " te.");
		System.out.println("\tGenomsnittlig kötid: "
				+ formatNumber(store.getTotalQueueTime() / store.getTotCustomersInRegisterQueue()) + " te.");
	}

	/**
	 * skriver ut eventen
	 */
//	public void printEvent() {
//
//		System.out.printf("%-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
//				df.format(eventQueue.getCurrentEvent().getTime()), eventQueue.writeOut(), eventQueue.getCurrentEvent().getCustomerID(),
//				openOrNot(), store.getAvailableRegisters(), df.format(store.getTotalRegisterTime()),
//				df2.format(store.getCurrentCustomers()), store.getCustomerPayed(), store.getCustomerNotPayed(),
//				store.getTotCustomersInRegisterQueue()
//
//				, df.format(store.getTotalRegisterTime()), store.getTheFIFO().size(), store.getMaxSize() //feeel);
//
//	}
	private void printEvent() {

		String formatCurrEvent = "" + eventQueue.writeOut();
		if (formatCurrEvent.length() < 4) {
			formatCurrEvent = formatCurrEvent + " ";
		}

		String checkCustomerNull;
		if (store.getCustomer() == null) {
			checkCustomerNull = "---";
		} else {
			checkCustomerNull = String.valueOf(store.getCustomer().getID());
		}

		System.out.println(formatNumber(eventQueue.getCurrentEvent().getTime()) + "\t" + formatCurrEvent + "\t\t" + eventQueue.getCurrentEvent().getCustomerID() + "\t"
				+ store.isStoreOpen() + "\t" + store.getAvailableRegisters() + "\t"
				+ formatNumber(store.getTotalRegisterTime()) + "\t" + store.getCurrentCustomers() + "\t"
				+ store.getCustomerPayed() + "\t" + store.getTurnedAwayCustomers() + "\t"
				+ store.getTotCustomersInRegisterQueue() + "\t" + formatNumber(store.getTotalRegisterTime()) + "\t"
				+ store.getTheFIFO().size() + "\t" + store.getTheFIFO());
	}

	  /**
   	  *
    	  * @param time time
   	  * @return correctTime
   	  */

	private String formatNumber(double time) {

		String correctTime = "" + (Math.round(time * 100.0) / 100.0);
		if (correctTime.length() < 4) {
			correctTime = correctTime + "0";
		}
		return correctTime;
	}

	 /**
   	  *updatefunktion för storeView
    */

	public void update(Observable o, Object arg) {
		
		if (eventQueue.writeOut().equals("Start")) {
			firstPrint(); // Kanske inte ska vara first()
			//System.out.println(eventQueue.first().getTime() + "\tStart");
		} else if (eventQueue.writeOut().equals("Stop")) {
			System.out.println(formatNumber(eventQueue.first().getTime()) + "\tStop");
			lastPrint();
		} else {
			printEvent();
		}
	}
}
