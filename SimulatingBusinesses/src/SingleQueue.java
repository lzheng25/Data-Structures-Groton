import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SingleQueue {
	
	protected Queue<Customer> waitLine; //if no tellers available, put customer into waiting line
	protected Teller[] tellers; //array of tellers, easy to loop through
	protected EventQueue<Event> events; //initially only customers, will later contain process and finish events too
	protected ArrayList<Integer> waits; //wait time for each customer
	protected int numCustomers;
	
	
	public SingleQueue(int numCustomers, int numTellers) {
		this.waitLine = new LinkedList<Customer>();
		this.events = new EventQueue<Event>(new TimeComparator());
		this.tellers = new Teller[numTellers];
		this.waits = new ArrayList<Integer>();
		this.numCustomers = numCustomers;
		
		//Create customers
		for(int i = 0; i < numCustomers; i++) {
			events.add(new Customer(i, 100, 20, false));
		}
		//Create tellers
		for(int i = 0; i < tellers.length; i++) {
			tellers[i] = new Teller(i);
		}
	}
	
	//Use same customer queue as multiqueue
	public SingleQueue(EventQueue<Event> c, int numTellers) {
		this.waitLine = new LinkedList<Customer>();
		this.events = c;
		this.tellers = new Teller[numTellers];
		this.numCustomers = c.size();
		//Create tellers
		for(int i = 0; i < tellers.length; i++) {
			tellers[i] = new Teller(i);
		}
		this.waits = new ArrayList<Integer>();
	}
	
	public void simulate() {
		//customers();
		int time = 0;
		while(!events.isEmpty()) {
			Event e = events.remove();
			time = e.getTime();
			
			//Check if customer arrived
			if(e.getEvent().equals("Customer")) {	
				Customer c = (Customer) e;
				boolean tellerAvailable = false;
			    Process p = null;
				for(int i = 0; i < tellers.length; i++) {
					Teller t = tellers[i];
					if(!t.isWorking()) {
						tellerAvailable = true;
						t.lineUp((Customer) c);
						t.setWorking(true);
						//Create an event that represents a teller that is starting to work
						p = new Process(time, c.getService(), i, c.getNum());
						break;
					}
				}
				//if customer is sent to a teller, add process event to events queue
				if(tellerAvailable) events.add(p);
				//else add customer to waiting line
				else waitLine.add(c);
			}
			
			if(e.getEvent().equals("Process")) {
				//System.out.println(e);
				Process p = (Process) e;
				tellers[p.getTeller()].setWorking(true);
				Finish f = new Finish(p.getFinish(), p.getTeller(), p.getCustomer());
				events.add(f);
			}
			
			if(e.getEvent().equals("Finish")) {
				Finish f = (Finish) e;
				//System.out.println(e);
				tellers[f.getTeller()].setWorking(false);
				tellers[f.getTeller()].serve();
				if(!waitLine.isEmpty()) {
					Customer c = waitLine.remove();
					for(int i = 0; i < tellers.length; i++) {
						if(!tellers[i].isWorking()) {
							tellers[i].lineUp(c);
							tellers[i].setWorking(true);
							break;
						}
					}
					Process p = new Process(time, c.getService(), f.getTeller(), c.getNum());
					events.add(p);
					//Calculate time customer waited
					waits.add(time - c.getTime());
				}
			}	
		}
		
		//Calculate average customer wait time and time when last teller finished
		double avg = 0;
		for(int i = 0; i < waits.size(); i++) {
			avg += waits.get(i);
			//System.out.println(waits.get(i));
		}
		avg /= numCustomers;
		System.out.println("Average customer wait time was " + avg + ".");
		
		System.out.println("Single queue simulation ended at " + time + ".");
	}
	
	//print customers in the line, cuz why not
	public void customers() {
		//Queue for re-adding back into customers queue
		System.out.println("Customers in line:");
		Queue<Customer> q = new LinkedList<Customer>(); 
		while(!events.isEmpty()) {
			Customer c = (Customer) events.remove(); 
			q.add(c);
			System.out.println(c);
		}
		
		while(!q.isEmpty()) events.add(q.remove());
	}
	
	public void printTellers() {
		for(int i = 0; i < tellers.length; i++) {
			System.out.println(tellers[i]);
		}
	}
	
}
