package Event;

import Simulator.Event;
import Simulator.EventQueue;
import Simulator.State;
import State.StoreState;




import State.*;
// SKALL START VARA ABSTRACT ????????
//SAMMA GÄLLER STOPP???????????????????????????????????????????????????????
public  class Start extends Event{ 
   private Close closing; 
   private Arrival arrival;
   
   private double closeTime;
   private double time; 
   private State state;
   private StoreState storeState;
   private EventQueue eventQueue;

   public Start(State state, EventQueue eventQueue){
      super(state, eventQueue);
      this.time = 0d;
      this.closeTime = storeState.getClosingTime();
   }


   public void doMe(){
      state.update(this);
      
      state.getStore().setStoreOpen(true);
      Close Store = new Close(this.state, eventQueue, closeTime);
      // Addar ett element till eventqueue genom att ropa på sortedsequence(event)
      eventQueue.SortedSequence(closing);
      
      double arrivalTime = 0;
   }


   public double getTime(){
      return time;
   }

   public Customer getCustomer(){
      return null;
   }

   public String getName(){
      return "Start";
   }


@Override
public void doThis() {
	// TODO Auto-generated method stub
	
}
}
