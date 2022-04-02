import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MultiQueue {
	
	protected Queue<Customer> waitLine; //if no tellers available, put customer into waiting line
	protected Teller[] tellers; //array of tellers, easy to loop through
	protected EventQueue<Event> events; //initially only customers, will later contain process and finish events too
	protected ArrayList<Integer> waits; //wait time for each customer
	protected int numCustomers;
	
	public MultiQueue(int numCustomers, int numTellers) {
		this.waitLine = new LinkedList<Customer>();
		this.events = new EventQueue<Event>(new TimeComparator());
		this.tellers = new Teller[numTellers];
		this.waits = new ArrayList<Integer>();
		this.numCustomers = numCustomers;
		
		//Create customers
		//Create customers
		for(int i = 0; i < numCustomers; i++) {
			events.add(new Customer(i, 100, 20, false));
		}
		//Create tellers
		for(int i = 0; i < tellers.length; i++) {
			tellers[i] = new Teller(i);
		}
	}
	
	//Use same customer queue as singlequeue
	public MultiQueue(EventQueue<Event> c, int numTellers) {
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
				//Find the teller with the shortest line
				int min = tellers[0].size();
				int index = 0;
				for(int i = 1; i < tellers.length; i++) {
					if(tellers[i].size() < min) {
						min = tellers[i].size();
						index = i;
					}
				}
				//Put the customer in line
				tellers[index].lineUp(c);
				//If just one customer, create process event to serve him
				if(tellers[index].size() == 1) {
					Process p = new Process(time, c.getService(), index, c.getNum());
					events.add(p);
				}
			}
			
			if(e.getEvent().equals("Process")) {
				//System.out.println(e);
				Process p = (Process) e;
				Finish f = new Finish(p.getFinish(), p.getTeller(), p.getCustomer());
				events.add(f);
			}
			
			if(e.getEvent().equals("Finish")) {
				Finish f = (Finish) e;
				//System.out.println(e);
				//Finish serving the customer, remove them
				tellers[f.getTeller()].serve();
				//Check if more customers in the teller's line
				//If there is customer, create a process event for him
				if(tellers[f.getTeller()].size() > 0) {
					Customer c = tellers[f.getTeller()].peek();
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

		System.out.println("Multi queue simulation ended at " + time + ".");
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
