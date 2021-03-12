package Simulator;
import java.util.Observable;


import State.*;
import State.StoreState;
import Time.*;
import Event.*; 

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 */

public class State extends Observable {
	private boolean stopFlag = true;
	private void setstopFlag() {
		stopFlag = false;;
	}
	private boolean getstopflag() {
		return stopFlag;
	}
	public void update() {
		setChanged();
		notifyObservers();
	}
}
	
	