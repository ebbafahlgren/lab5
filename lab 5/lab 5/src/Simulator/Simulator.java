package Simulator;

import Simulator.State;
import Event.*;
import View.*;
import Event.Start;


/**
 * 
 * Starts the event queue and creates two events which are put into the queue
 * event Start and event Stop. When the program comes to running the method of
 * the start event, more events will be added.
 *
 *
 */

public class Simulator {

   private State state;
   private EventQueue eventQueue;
   private View view;
   private Start start;
   
   public Simulator(State state, View view) {
      this.state = state;
      this.view = view;
      this.eventQueue = new EventQueue();
   }

   public void run() {
      eventQueue.SortedSequence(new Start(state, eventQueue));
      eventQueue.SortedSequence(new Stop(state, eventQueue));
      
      //While simulator is running, it will keep on getting the first event in
      //queue and running the ques "doThis" function.
      
      while (state.getStopFlag()) {
         Event event = eventQueue.first();
         event.doThis();
         eventQueue.removeFirst();
      }
   }
}