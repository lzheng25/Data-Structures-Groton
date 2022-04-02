import java.util.ArrayList;

import structure5.AbstractIterator;

public class Iterator extends AbstractIterator{

	private ArrayList<Integer> v;
	private long counter;
	private static int n;
	
	public Iterator(ArrayList<Integer> v) {
		this.v = v;
		n = v.size();
	}
	
	@Override
	public ArrayList<Integer> get() {
		// TODO Auto-generated method stub
		ArrayList<Integer> subset = new ArrayList<Integer>();

		for(int i = 0; i < n; i++) {
			if((counter & (1L << i)) > 0) {
				subset.add(v.get(i));
			}
		}
		return subset;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return counter + 1 <= Math.pow(2, n);
	}

	@Override
	public ArrayList<Integer> next() {
		// TODO Auto-generated method stub
		ArrayList<Integer> temp = (ArrayList<Integer>) get();
		counter++;
		return temp;
	}

	@Override
	//Reset subset counter
	public void reset() {
		// TODO Auto-generated method stub
		counter = 0;
	}
	
	public long getCounter() {
		return counter;
	}

}
