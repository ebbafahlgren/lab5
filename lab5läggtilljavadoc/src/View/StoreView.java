package View;

import Event.*;
import State.*;
import Simulator.*;

import java.util.Observable;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class StoreView extends View {
	private State state;
	private StoreState store;
	
	   /**
   	   * 
  	   * @param state statusen på butiken 
  	   * @param store store
   	   */
	public StoreView(State state, StoreState store) {
		super(state, store);
		this.state = state;
		this.store = store;
	}
	
	  /**
   	   * skriver ut f�rsta utskiften i vyn
    	   */
	private void firstPrint() {
		System.out.println("PARAMETRAR");
		System.out.println("==========");
		System.out.println("Antal kassor, N..........: " + store.getAvailableRegisters());
		System.out.println("Max som ryms, M..........: " + store.getMaxCustomer());
		System.out.println("Ankomshastighet, lambda..: " + state.getLambda());
		System.out
				.println("Plocktider, [P_min..Pmax]: [" + state.getPickMinTime() + ".." + state.getPickMaxTime() + "]");
		System.out.println("Betaltider, [K_min..Kmax]: [" + state.getPayMinTime() + ".." + state.getPayMaxTime() + "]");
		System.out.println("Fr�, f...................: " + state.getSeed());
		System.out.println("");
		System.out.println("F�RLOPP");
		System.out.println("=======");
		System.out.println("Tid\th�ndelse\tKund\t�\tled\tledT\tI\t$\t:-(\tk�at\tk�T\tk�ar\t[Kassak�..]");

	}
	
	 /**
   	  * skriver ut andra utskriften i view
   	  */
	private void lastPrint() {
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
						/ (state.getLastPayEventTime()) * 100)
				+ "% av tiden fr�n" + " �ppning tills sista kunden betalat).");
		System.out.println("");
		System.out.println("3) Total tid " + store.getTotNumCustomersInRegisterQueue() + " kunder tvingats k�a: "
				+ formatNumber(store.getCustomerQueueTime()) + " te.");
		System.out.println("\tGenomsnittlig k�tid: "
				+ formatNumber(store.getCustomerQueueTime() / store.getTotNumCustomersInRegisterQueue()) + " te.");
	}

	/**
	 * skriver ut eventen
	 */
	private void printEvent() {

		String formatCurrEvent = "" + state.getEvent().writeOut();
		if (formatCurrEvent.length() < 4) {
			formatCurrEvent = formatCurrEvent + " ";
		}

		String checkCustomerNull;
		if (state.getCustomer() == null) {
			checkCustomerNull = "---";
		} else {
			checkCustomerNull = state.getCustomer().getID();
		}

		System.out.println(formatNumber(state.getTime()) + "\t" + formatCurrEvent + "\t\t" + checkCustomerNull + "\t"
				+ store.isStoreOpen() + "\t" + store.getAvailableRegisters() + "\t"
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
   	  *updatefunktion f�r storeView
    */
	@Override
	public void update(Observable o, Object arg) {
		
		if (state.getEvent().getClass() == Start.class) {
			firstPrint();
			System.out.println(state.getTime() + "\tStart");
		} else if (state.getEvent().getClass() == Stop.class) {
			System.out.println(formatNumber(state.getTime()) + "\tStop");
			lastPrint();
		} else {
			printEvent();
		}
	}
}
