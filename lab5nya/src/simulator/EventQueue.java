package simulator;

import java.util.ArrayList;

import Event.Close;
import State.StoreState;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

public class EventQueue {
	private ArrayList<Event> queue = new ArrayList<Event>();

	
	/**
	 * Event first tar nästa element som är på tur
	 * @return null
	 */
	public Event first() {
		if (queue.size() > 0) {
			return queue.get(0);
		}
		return null;
	}
	
	public Event getCurrentEvent() {
		return queue.get(0);
	}
	
	public void eventDone() {
		Event event = queue.get(0);
		event.doThis();
		queue.remove(0);
	}
	
	/**
	 * size 
	 * @return storleken på kön
	 */
	public int size() {
		return queue.size();
	}
	

	public void SortedSequence(Event event) {
		
		/// stannar så butiken ej lägger in fler element
		// ett visst antal event i kön.
		//if (event.getTime() < store.getClosingTime()) {
			ArrayList<Event> newlist = new ArrayList<Event>();
			int count = 0;
			
			//System.out.println("I EventQueue");
			//System.out.println(event + " = events värde ex startevent, stopevent etc");
			
			//måste lägga till start och stop i listan först
			
	        // sätter in alla element som har mindre tid eller lika än det nya elementet
			for (int i = 0; i < queue.size(); i++) { // <=
				
				//System.out.println("i for-loop ");
				
				if (queue.get(i).getTime() <= event.getTime()) {
					newlist.add(queue.get(i));
					//System.out.println(queue.get(i));
				} else {
					//System.out.println("eventet i kön tar längre tid");
					count = i;
					break; //EVENTUELLT TA BORT
				}
			}
				// sätter in det nya elementet
				newlist.add(event);
				//System.out.println(newlist + " skriver ut vad som finns i listan");
				
			// sätter in alla element som har längre tid än elementet
			for (int j = 0; j < queue.size(); j++) {
				if (queue.get(j).getTime() > event.getTime()) {
					newlist.add(queue.get(j));
				}
			}
				
			// Byter lista
			queue = newlist;
			//System.out.println(queue + " den nya listan");
	}


	public ArrayList<Event> getList() {
		return queue;
	}
	public String writeOut() {
		return queue.get(0).writeOut();
	}
	public String toString() {
		return queue.toString();
	}


	public void removeFirst() {
		queue.remove(0);
	}
}
