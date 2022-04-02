
public class Finish implements Event {
	
	private int time;
	protected int tellerNum;
	protected int customerNum;
	
	public Finish(int time, int t, int c) {
		this.time = time;
		this.tellerNum = t;
		this.customerNum = c;
	}
	
	public int getTeller() {
		return tellerNum;
	}
	
	public int getCustomer() {
		return customerNum;
	}
	
	public String toString() {
		return "Finish| End: " + time +
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
		return "Finish";
	}
	
}
