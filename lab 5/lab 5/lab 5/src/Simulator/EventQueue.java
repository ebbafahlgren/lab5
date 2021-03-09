package Simulator;

import java.util.ArrayList;
import State.StoreState;

public class EventQueue {
	protected ArrayList<Event> queue = new ArrayList<Event>();
	StoreState store;
	State state;

	public EventQueue() {
		// TODO Auto-generated constructor stub

	}
	// tar ut nästa element som är på tur
	public Event first() {
		return queue.get(0);
	}
	// tar ut
	public int size() {
		return queue.size();
	}
// denna blir ropad på så fort kön töms på ett event
//	if(queue.size==0&&time>closingTime){
//		stopsimultator();
//	}
	public void SortedSequence(Event event) {
//		den här stannar simulationen om eventkön är tom och om tiden har gått över stopptiden
		if (queue.size() == 0 && event.getTime() > store.getClosingTime()) {
			state.stopSimulation();
		}
		/// stannar så butiken ej lägger in fler element
		// ett visst antal event i kön.
		if (event.getTime() < store.getClosingTime()) {
			ArrayList<Event> newlist = new ArrayList<Event>();
			int count = 0;
	        // sätter in alla element som har mindre tid eller lika än det nya elementet
			for (int i = 0; i < queue.size(); i++) {
				if (queue.get(i).getTime() <= event.getTime()) {
					newlist.add(queue.get(i));
				} else {
					count = i;
					break;
				}
				// sätter in det nya elementet
				newlist.add(event);
				// sätter in alla element som har längre tid än elementet
				for (int j = count; j < queue.size(); j++) {
					if (queue.get(j).getTime() > event.getTime()) {
						newlist.add(queue.get(j));
					}
				}
			}
			// Byter lista
			queue = newlist;
		}

	}
	public void removeFirst() {
		queue.remove(0);
	}
}
