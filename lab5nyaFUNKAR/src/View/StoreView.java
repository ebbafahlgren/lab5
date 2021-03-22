package View;

import java.text.DecimalFormat;
import Event.*;
import State.*;
import Simulator.*;

import java.util.Observable;
import java.util.*;
/**
 * Det här är det som skriver ut vyn för butiken. 
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
		  if (store.isStoreOpen() == true) {
			  x = "Ö";
		  }
		  return x;
	  }
	/**
	* prints out the parameters first
	*/
	  public void firstPrint() {
		  System.out.println("PARAMETRAR\n" + "==========\n" + "Antal kassor, N..........: " + store.getMaxRegisters() + "\n"
				  + "Max som ryms, M..........: " + store.getMaxCustomers() + "\n" + "Plocktider, [P_min..Pmax]: ["
				  + store.getPickMinTime() + ".." + store.getPickMaxTime() + "]" + "\n" + "Betaltider, [K_min..Kmax]: ["
				  + store.getPayMinTime() + ".." + store.getPayMaxTime() + "]" + "\n" + "Ankomshastighet, lambda..: "
				  + store.getLambda() + "\n" + "Frö, f...................: " + store.getSeed() + "\n");

		  System.out.println("FÖRLOPP\n" + "=======");

		  System.out.printf("%-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", "Tid",
				  "Händelse", "Kund", "?", "led", "ledT", "I", "$", ":-(", "köat", "köT", "köar", "[kassakö..]");

		  System.out.println("0.00" + "\t" + " " + " " + " " + "Start");
	  }
	
	 /**
   	  * skriver ut andra utskriften i view
   	  */
	private void lastPrint() {
		System.out.println("");
		System.out.println("RESULTAT");
		System.out.println("========");
		System.out.println("");
		System.out.println("1) Av " + store.getTotalPayments() +  " handlade " + store.getCustomerPayed() + " medan "
				+ store.getCustomerNotPayed() + " missade.");
		System.out.println("");
		System.out.println("2) Total tid " + store.getAvailableRegisters() + " kassor varit lediga: "
				+ formatNumber(store.getTotalRegisterTime()) + " te.");
		System.out.println("\tGenomsnittlig ledig kassatid: "
				+ formatNumber(store.getTotalRegisterTime() / store.getMaxRegisters()) + " te (dvs "
				+ formatNumber((store.getTotalRegisterTime() / store.getMaxRegisters())
						/ (store.getLastPaymentTime()) * 100)
				+ "% av tiden från" + " öppning tills sista kunden betalat).");
		System.out.println("");
		System.out.println("3) Total tid " + store.getCustomerInQueueTot() + " kunder tvingats köa: "
				+ formatNumber(store.getTotalQueueTime()) + " te.");
		System.out.println("\tGenomsnittlig kötid: "
				+ formatNumber(store.getTotalQueueTime() / store.getCustomerInQueueTot()) + " te.");
	}

	/**
	 * skriver ut eventen
	 */
	public void printEvent() {

		System.out.printf("%-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
				df.format(eventQueue.getCurrentEvent().getTime()), eventQueue.writeOut(), eventQueue.getCurrentEvent().getCustomerID(),
				openOrNot(), store.getAvailableRegisters(), df.format(store.getTotalRegisterTime()),
				df2.format(store.getCurrentCustomers()), store.getCustomerPayed(), store.getCustomerNotPayed(),
				store.getCustomerInQueueTot()

				, df.format(store.getTotalQueueTime()), store.getTheFIFO().size(), store.getPrintedQueue());
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
