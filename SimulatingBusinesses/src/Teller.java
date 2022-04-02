import java.util.LinkedList;
import java.util.Queue;

public class Teller implements Event {
	
	protected Queue<Customer> customers;
	protected int time; //total time serving people
	private int n; //n'th teller
	private boolean working;
	
	public Teller(int n) {
		customers = new LinkedList<Customer>();
		this.time = 0; 
		this.n = n;
		working = false;
	}
	
	public String toString() {
		String s = "Teller| ";
		Queue<Customer> q = new LinkedList<Customer>();
		while(!customers.isEmpty()) {
			Customer c = (Customer) customers.remove();
			q.add(c);
			s += c + "; ";
		}
		
		while(!q.isEmpty()) customers.add(q.remove());
		s += "Teller Time: " + time;
		return s;
	}
	
	//serve a customer and return the amount of time the customer waited in line for
	public int serve(int time) {
		int wait = 0;
		if(!customers.isEmpty()) {
			Customer c = (Customer) customers.remove();
			wait = time - c.getTime();
			this.time += c.getService();
		}
		return wait;
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return time;
	}
	
	public void lineUp(Customer c) {
		customers.add(c);
	}
	
	//serve customer, essentially removing
	public void serve() {
		customers.remove();
	}
	
	public Customer peek() {
		return customers.peek();
	}
	
	public int size() {
		return customers.size();
	}
	
	public String getEvent() {
		return "Teller";
	}
	
	public int getNum() {
		return n;
	}
	
	public void setWorking(boolean working) {
		this.working = working;
	}
	
	public boolean isWorking() {
		return working;
	}
	
}
