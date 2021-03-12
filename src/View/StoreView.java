package View;

import Event.*;
import Simulator.*;
import State.StoreState;

import java.util.Observable;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class StoreView extends View {
//	private store store;
	private StoreState store;
	
	   /**
   	   * 
  	   * @param store statusen pÃ¥ butiken 
  	   * @param store store
   	   */
	public StoreView(StoreState store) {
		super(store);
		this.store = store;
	}
	
	  /**
   	   * skriver ut första utskiften i vyn
    	   */
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
	
	 /**
   	  * skriver ut andra utskriften i view
   	  */
	private void lastPrint() {
		System.out.println();
		System.out.println("");
		System.out.println("RESULTAT");
		System.out.println("========");
		System.out.println("");
		System.out.println("1) Av " + store.totalCustomers() + " handlade " + store.numCustomerPayed() + " medan "
				+ store.getTurnedAwayCustomers() + " missade.");
		System.out.println("");
		System.out.println("2) Total tid " + store.getAvailableRegisters() + " kassor varit lediga: "
				+ formatNumber(store.getRegisterFreetime()) + " te.");
		System.out.println("\tGenomsnittlig ledig kassatid: "
				+ formatNumber(store.getRegisterFreetime() / store.getAvailableRegisters()) + " te (dvs "
				+ formatNumber((store.getRegisterFreetime() / store.getAvailableRegisters())
						/ (store.getLastPaymentTime()) * 100)
				+ "% av tiden från" + " öppning tills sista kunden betalat).");
		System.out.println("");
		System.out.println("3) Total tid " + store.getTotNumCustomersInRegisterQueue() + " kunder tvingats köa: "
				+ formatNumber(store.getCustomerQueueTime()) + " te.");
		System.out.println("\tGenomsnittlig kötid: "
				+ formatNumber(store.getCustomerQueueTime() / store.getTotNumCustomersInRegisterQueue()) + " te.");
	}

	/**
	 * skriver ut eventen
	 */
	private void printEvent() {

		String formatCurrEvent = "" + store.getEvent().writeOut();
		if (formatCurrEvent.length() < 4) {
			formatCurrEvent = formatCurrEvent + " ";
		}

		String checkCustomerNull;
		if (store.getCustomer() == null) {
			checkCustomerNull = "---";
		} else {
			checkCustomerNull = store.getCustomer().getID();
		}
// isstopre open här skall skicaka ut ö eller s
		System.out.println(formatNumber(store.getTime()) + "\t" + formatCurrEvent + "\t\t" + checkCustomerNull + "\t"
				+ this.isStoreOpen() + "\t" + store.getAvailableRegisters() + "\t"
				+ formatNumber(store.getRegisterFreetime()) + "\t" + store.getNumCustomerInStore() + "\t"
				+ store.numCustomerPayed() + "\t" + store.getTurnedAwayCustomers() + "\t"
				+ store.getTotNumCustomersInRegisterQueue() + "\t" + formatNumber(store.getCustomerQueueTime()) + "\t"
				+ store.getRegisterQueue().size() + "\t" + store.getRegisterQueue());
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
	@Override
	public void update(Observable o, Object arg) {
		
		if (store.getEvent().getClass() == Start.class) {
			firstPrint();
			System.out.println(store.getTime() + "\tStart");
		} else if (store.getEvent().getClass() == Stop.class) {
			System.out.println(formatNumber(store.getTime()) + "\tStop");
			lastPrint();
		} else {
			printEvent();
		}
	}
	/**
	 * isStoreOpen
	 * @return isOpen
	 */
	public String isStoreOpen(){
			if (store.isStoreOpen() == true) {
				return "Ö";
			}
			else {
				return "S";
			}
	} 

}
