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
	
	//tar ut n�sta element som �r p� tur
	public Event first() {
		
		System.out.println("f�rst i event ");
		
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
//		den h�r stannar simulationen om eventk�n �r tom och om tiden har g�tt �ver stopptiden
//		if (queue.size() == 0 && event.getTime() > store.getClosingTime()) {
//			state.stopSimulation();
//		}
		
		
		/// stannar s� butiken ej l�gger in fler element
		// ett visst antal event i k�n.
		//if (event.getTime() < store.getClosingTime()) {
			ArrayList<Event> newlist = new ArrayList<Event>();
			int count = 0;
			
			System.out.println("I EventQueue");
			System.out.println(event + " = events v�rde ex startevent, stopevent etc");
			
			//m�ste l�gga till start och stop i listan f�rst
			
	        // s�tter in alla element som har mindre tid eller lika �n det nya elementet
			for (int i = 0; i < queue.size(); i++) { // <=
				
				System.out.println("i for-loop ");
				
				if (queue.get(i).getTime() <= event.getTime()) {
					newlist.add(queue.get(i));
					System.out.println(queue.get(i));
				} else {
					System.out.print("listan �r tom");
					count = i;
					break;
				}
			}	
			
				// s�tter in det nya elementet
				newlist.add(event);
				System.out.println(newlist + " skriver ut vad som finns i listan");
				
				// s�tter in alla element som har l�ngre tid �n elementet
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
