import java.util.LinkedList;
import java.util.Queue;

import objectdraw.*;

public class AutoPlay extends ActiveObject {
	
	private int n;
	private Peg start;
	private Peg target;
	private Peg middle;
	private boolean running;
	private Text movesT;
	private int moves;
	private Queue<TurnRecord> optimal;

	//number of disks, peg 1, peg 3, peg 4
	//autoPlay(3, A, C, B)
	public AutoPlay(int n, Peg start, Peg target, Peg middle, Text movesT) {
		this.n = n;
		this.start = start;
		this.target = target;
		this.middle = middle;
		this.movesT = movesT;
		this.running = false;
		this.moves = 0;
		//start();
		//autoPlay(n, start, target, middle);
		optimal = new LinkedList<TurnRecord>();
		autoPlay(n, start, target, middle, true);
	}
	
	public void autoPlay(int n, Peg start, Peg target, Peg middle, boolean store) {
		if(n <= 0) return;
		else {
			//move n-1 disks to middle
	        autoPlay(n - 1, start, middle, target, store);

	        //move n'th disk to end
	        if(!store) {
	        	pause(2000);
	        	Disk d = start.remove();
		        target.add(d, true, new Slide(middle, d, n, true)); 
		        movesT.setText("Number of moves: " + ++moves);
	        }
	        else {
	        	target.add(start.remove(), false, null); 
	        	optimal.add(new TurnRecord(start, target));
	        }
	        //move rest of disks onto end
	        autoPlay(n - 1, middle, target, start, store);
	        
		}
		
	}
	
	public void commence() {
		running = true;
		start();
	}
	
	public boolean isAutoPlaying() {
		return running;
	}
	
	public Queue<TurnRecord> getOptimal() {
		return optimal;
	}
	
	public void run() {
		autoPlay(n, start, target, middle, false);
		running = false;
	}
	
}
