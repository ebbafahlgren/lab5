package Simulator;

import java.util.Observable;
import java.util.Observer;

import State.*;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 */

public abstract class View implements Observer {

   State state;
   StoreState store;
   
    /**
    * @param state statusen
    * @param store butiken
    */
   public View(State state, StoreState store)
   {
      state.addObserver(this);
   }
   
   /**
    * @param arg arg
    */
    @Override
    abstract public void update(Observable o, Object arg);

}
