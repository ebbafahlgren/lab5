package Simulator;


import java.util.Observable;
import java.util.Observer;

import State.*;
public abstract class View implements Observer {

   State state;
   StoreState store;
   
   public View(State state, StoreState store)
   {
      state.addObserver(this);
   }
    @Override
    abstract public void update(Observable o, Object arg);

}