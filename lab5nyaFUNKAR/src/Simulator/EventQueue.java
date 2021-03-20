package Simulator;
import java.util.ArrayList;

import Event.Close;
import State.StoreState;

/**
 * @author Ebba Fahlgren, Anton Sandberg, Emma Evergren och Erik Hilmersson
 *
 */

/**
* EventQueue is a class that organizes the events by time. The shortest time should be placed first in the list.
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
	
	/** 
	* 
	* getCurrentEvent returns the current event
	* @return event
	*/
	public Event getCurrentEvent() {
		return queue.get(0);
	}
	
	/** 
	* eventDone does the event and removes it from the list
	*/
	public void eventDone() {
		Event event = queue.get(0);
		event.doThis();
		queue.remove(0);
	}
	/**
	 * size returns the current size of the queue of events
	 * @return queue.size
	 */
	public int size() {
		return queue.size();
	}
	
	/** 
	* 
	* @param event sort the events by time
	*/
	public void SortedSequence(Event event) {
//		
			ArrayList<Event> newlist = new ArrayList<Event>();
			int count = 0;

			
			for (int i = 0; i < queue.size(); i++) { // <=

				if (queue.get(i).getTime() <= event.getTime()) {
					newlist.add(queue.get(i));
					
				} else {
					
					count = i;
					break; 
				}
			}
				
				newlist.add(event);
				
			for (int j = 0; j < queue.size(); j++) {
				if (queue.get(j).getTime() > event.getTime()) {
					newlist.add(queue.get(j));
				}
			}

			// Byter lista
			queue = newlist;
			
	}

	/** 
	* Arraylist returns the queue of events
	* @return queue
	*/
	public ArrayList<Event> getList() {
		return queue;
	}
	
	/** 
	* writeOut returns the value of which states the events name
	* @return string
	*/
	public String writeOut() {
		return queue.get(0).writeOut();
	}
	/** 
	* toString returns a string of all events in the queue
	* @return string
	*/
	public String toString() {
		return queue.toString();
	}
	/** 
	* 
	* removeFirst removes the first event in the queue. This should be done after the event has been done. After doThis.
	*/

	public void removeFirst() {
		queue.remove(0);
	}




}
