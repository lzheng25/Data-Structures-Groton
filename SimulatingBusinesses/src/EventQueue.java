import java.util.Comparator;
import java.util.PriorityQueue;

public class EventQueue<E> {
	
	private PriorityQueue<Event> events;
	private Comparator<Event> c;
	
	public EventQueue(Comparator<Event> c) {
		this.events = new PriorityQueue<Event>(c);
		this.c = c;
	}
	
	public void add(Event e) {
		events.add(e);
	}
	
	public int size() {
		return events.size();
	}
	
	public boolean isEmpty() {
		return events.isEmpty();
	}
	
	public Event remove() {
		return events.remove();
	}
	
	public Event peek() {
		return events.peek();
	}
	
}
