package Simulator;
import java.util.ArrayList;
public class EventQueue {
	protected ArrayList<Event> queue = new ArrayList<Event>();
	

	public EventQueue() {
		// TODO Auto-generated constructor stub
	}
	
	//tar ut nästa element som är på tur
	public Event first() {
		return queue.get(0);
	}
	
	//tar ut 
	public int size() {
		return queue.size();
	}
	
	public void SortedSequence(Event event) {
		ArrayList<Event> newList = new ArrayList<Event>();
		int count = 0;
		
		//sätter in alla element som har mindre tid eller lika än det nya elementet
		for(int i = 0; i < queue.size(); i++) {
			if(queue.get(i).getTime() <= event.getTime()) {
				newList.add(queue.get(i));	
			}
			else {
				count = i;
				break;
		}
			
	    //sätter in det nya elementet
		newList.add(event);
		
		//sätter in alla element som har längre tid än elementet
		for(int j = count; j < queue.size(); j++) {
			if(queue.get(j).getTime() > event.getTime()) {
				newList.add(queue.get(j));
				}
			}
		}
		
		//Byter lista
		queue = newList;
		
	}
	
	
	
	
	
	

}
