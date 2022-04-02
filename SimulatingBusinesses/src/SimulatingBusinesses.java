import java.util.Scanner;

/*
 * Thought Questions
 * 
 * 1. The single queue technique always processes customers as fast or faster than the multi queue technique.
 * 	  Multi queue only takes as short as single queue when the customers happen to enter the shortest line that also
 * 	  finishes the earliest; otherwise, it always takes longer than single queue.
 * 
 * 2. The average wait time for single queue is always shorter or as short as multi queue's wait time. In single
 * 	  queue, the tellers are constantly busy whenever there are still customers to serve, minimizing wait time.
 * 	  But for multi queue, there are scenarios where some tellers are not serving customers even when there are 
 * 	  customers in line, so wait time is unnecessarily increased.
 * 
 * 3. No it does not. To implement this, just loop through the array of tellers to see if a line has two or more
 *    customers than another. If it does, remove the extra customer (remove customer at last index on Teller's 
 *    ArrayList) and line him up in a shorter line. I can still use an ArrayList to store customers in a teller's 
 *    line to simulate the ability to jump between lines.
 *    
 * 4. If lines were dedicated to serving customers with service times within a specific range, the wait time would 
 * 	  most likely increase. The line serving customers with short service times will certainly have a shorter wait
 * 	  time than usual; however, since all the customers with long service times go to the one teller at the end, the 
 *    wait will pile up quickly and result in a very long wait time in that line. If you calculate the total average 
 *    wait time across the lines, it will probably end up being longer than the usual multi queue technique, as the
 *    lines in multi queue are much more balanced, with customers of both short and long service times in the same
 *    line.
 */

public class SimulatingBusinesses {
	
	public static void main(String[] args) {
		
		EventQueue<Event> c1 = new EventQueue<Event>(new TimeComparator());
		EventQueue<Event> c2 = new EventQueue<Event>(new TimeComparator());
		
		Scanner s = new Scanner(System.in);
		System.out.print("How many customers? ");
		int numCustomers = s.nextInt();
		System.out.print("How many tellers? ");
		int numTellers = s.nextInt();
		System.out.print("Arrival time range? ");
		int arrival = s.nextInt();
		System.out.print("Service time range? ");
		int service = s.nextInt();
		
		//Create customers
		System.out.println("Customers In Line:");
		for(int i = 0; i < numCustomers; i++) {
			Customer c = new Customer(i, arrival, service, false);
			Customer d = c.clone();
			c1.add(c);
			c2.add(d);
			//System.out.println(c);
		}
				
		SingleQueue sq = new SingleQueue(c1, numTellers);
		MultiQueue mq = new MultiQueue(c2, numTellers);
		System.out.println("Single queue simulation results:");
		sq.simulate();
		System.out.println("");
		System.out.println("Multi queue simulation results:");
		mq.simulate();

	}
	
}
