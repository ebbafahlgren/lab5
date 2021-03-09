package Simulator;
import java.util.ArrayList;

import Event.Close;
import State.StoreState;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class EventQueue {
	protected ArrayList<Event> queue = new ArrayList<Event>();
	private State state;
	private StoreState store;

//	public EventQueue() {
//		// TODO Auto-generated constructor stub
//		System.out.println("eventQ");
//	}
	
	//tar ut nästa element som är på tur
	public Event first() {
		
		System.out.println("först i event ");
		
		System.out.println(queue.size() + " = eventQueues nuvarande storlek");
		
		if (queue.size() > 0) {
			return queue.get(0);
		}
		return null;
	}
	
	//tar ut storlek
	public int size() {
		return queue.size();
	}
	
	public void SortedSequence(Event event) {
//		den här stannar simulationen om eventkön är tom och om tiden har gått över stopptiden
//		if (queue.size() == 0 && event.getTime() > store.getClosingTime()) {
//			state.stopSimulation();
//		}
		
		
		/// stannar så butiken ej lägger in fler element
		// ett visst antal event i kön.
		//if (event.getTime() < store.getClosingTime()) {
			ArrayList<Event> newlist = new ArrayList<Event>();
			int count = 0;
			
			System.out.println("I EventQueue");
			System.out.println(event + " = events värde ex startevent, stopevent etc");
			
			//måste lägga till start och stop i listan först
			
	        // sätter in alla element som har mindre tid eller lika än det nya elementet
			for (int i = 0; i < queue.size(); i++) { // <=
				
				System.out.println("i for-loop ");
				
				if (queue.get(i).getTime() <= event.getTime()) {
					newlist.add(queue.get(i));
					System.out.println(queue.get(i));
				} else {
					System.out.print("listan är tom");
					count = i;
					break;
				}
			}	
			
				// sätter in det nya elementet
				newlist.add(event);
				System.out.println(newlist + " skriver ut vad som finns i listan");
				
				// sätter in alla element som har längre tid än elementet
				for (int j = count; j < queue.size(); j++) {
					if (queue.get(j).getTime() > event.getTime()) {
						newlist.add(queue.get(j));
					}
				}
			//}
				
			// Byter lista
			queue = newlist;
			System.out.println(queue + " den nya listan");
			
			
			
			
			
//			for(int i = 0; i < queue.size(); i++) {
//				if(queue.get(i).getTime() < = )
//			}

	}
		
		//Byter lista

		
//		if(event == close) {
//			
//		}
		


	public void removeFirst() {
		queue.remove(0);
	}


}
