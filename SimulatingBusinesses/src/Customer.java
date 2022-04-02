import java.util.Random;

public class Customer implements Event {
	
	private int arrival; //when they arrive
	private int service; //how long they spend at teller
	private int wait; //how long waited in queue
	private int startTime; //when they arrive at a teller
	private int n; //n'th customer that arrived
	
	public Customer(int n, int arr, int ser, boolean same) {
		this.wait = -1;
		this.startTime = -1;
		this.n = n;
		
		Random r = new Random();
		
		if(!same) {
			this.arrival = r.nextInt(arr) + 1;
			this.service = r.nextInt(ser) + 1;
		} else {
			this.arrival = arr;
			this.service = ser;
		}
		
	}
	
	public void setWait(int w) {
		wait = w;
	}
	
	public int getWait() {
		return wait;
	}
	
	public void start(int t) {
		this.startTime = t;
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return arrival;
	}
	
	public int getService() {
		// TODO Auto-generated method stub
		return service;
	}
	
	public String toString() {
		String s = "Customer " + n + "| Arrival time: " + arrival + "; Service time: " + service;
		if(startTime > -1) s +=	"; Service start time: " + startTime;
		if(wait > -1) s +=	"; Queue wait time: " + wait;
		return s;
	}
	
	//no need size method for customers. just for teller
	public int size() {
		return -1;
	}
	
	public Customer clone() {
		return new Customer(this.n, this.arrival, this.service, true);
	}
	
	public String getEvent() {
		return "Customer";
	}
	
	public int getNum() {
		return n;
	}
	
}
