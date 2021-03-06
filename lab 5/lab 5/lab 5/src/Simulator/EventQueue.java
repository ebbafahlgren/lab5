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
	// tar ut n�sta element som �r p� tur
	public Event first() {
		return queue.get(0);
	}
	// tar ut
	public int size() {
		return queue.size();
	}
// denna blir ropad p� s� fort k�n t�ms p� ett event
//	if(queue.size==0&&time>closingTime){
//		stopsimultator();
//	}
	public void SortedSequence(Event event) {
//		den h�r stannar simulationen om eventk�n �r tom och om tiden har g�tt �ver stopptiden
		if (queue.size() == 0 && event.getTime() > store.getClosingTime()) {
			state.stopSimulation();
		}
		/// stannar s� butiken ej l�gger in fler element
		// ett visst antal event i k�n.
		if (event.getTime() < store.getClosingTime()) {
			ArrayList<Event> newlist = new ArrayList<Event>();
			int count = 0;
	        // s�tter in alla element som har mindre tid eller lika �n det nya elementet
			for (int i = 0; i < queue.size(); i++) {
				if (queue.get(i).getTime() <= event.getTime()) {
					newlist.add(queue.get(i));
				} else {
					count = i;
					break;
				}
				// s�tter in det nya elementet
				newlist.add(event);
				// s�tter in alla element som har l�ngre tid �n elementet
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
