package Simulator;
import java.util.Observable;

import State.*;
import State.StoreState;
import Time.*;
import Event.*; 

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 */

/**
 * state is a class that determines if the simulation is running or not. We also observe the state so if anything is to be changed the update function is called.
 *
 */

public class State extends Observable {

	private boolean stopFlag = false;


/**
 * turnOfSimulation turns of the simulation and set the stopflag to be true.
 *
 */
	public void turnOfSimulation() {
		stopFlag = true;
		update();
	}
	
/**
 * @return stopFlag
 *
 */
	public boolean getStopFlag() {

		return stopFlag;
	}
/**
 * update let the observer know that something has changed
 *
 */
	public void update() {
		setChanged();
		notifyObservers();
	}

}

