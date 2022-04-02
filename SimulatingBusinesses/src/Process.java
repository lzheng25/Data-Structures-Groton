
public class Process implements Event {
	
	protected int time;
	protected int tellerNum;
	protected int customerNum;
	protected int service;
	
	public Process(int time, int service, int t, int c) {
		this.tellerNum = t;
		this.customerNum = c;
		this.time = time;
		this.service = service;
	}
	
	public int getTeller() {
		return tellerNum;
	}
	
	public int getCustomer() {
		return customerNum;
	}
	
	public int getFinish() {
		return time + service;
	}

	public String toString() {
		return "Process| Start: " + time + 
				"; Service: " + service + 
				"; Teller: " + tellerNum + 
				"; Customer: " + customerNum;
	}
	
	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return time;
	}

	@Override
	public String getEvent() {
		// TODO Auto-generated method stub
		return "Process";
	}
}
