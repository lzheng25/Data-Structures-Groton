
public class BitVector {
	protected boolean data[];
    protected int size;
    
    public static void main(String args[]) {
    	BitVector bv = new BitVector();
    	bv.add(true);
    }
    
    public BitVector() {
    	size = 0;
    	data = new boolean[10];
    }
    
    public boolean get(int index) {
    	if (index >= 0 && index < size()){
    		return data[index];
    	} else {
    		throw new ArrayIndexOutOfBoundsException("Array index out of bounds"); 
    	}
    }
    
    public boolean set(int index, boolean b) {
    	if (index >= 0 && index < size()){
    		boolean old = data[index];
    		data[index] = b;
    		return old;
    	} else {
    		throw new ArrayIndexOutOfBoundsException("Array index out of bounds"); 
    	}
    }
    
    public int size() {
    	return size;
    }
    
    public void add(boolean b) {
    	ensureCapacity(size + 1);
    	data[size] = b;
    	size++;
    }
    
    public void add(int index, boolean b) {
    	if (index < 0 || index > size()) {
    		throw new ArrayIndexOutOfBoundsException("Array index out of bounds"); 
    	}
    	
    	ensureCapacity(size + 1);
    	for (int i = size; i > index; i--) {
    		data[i] = data[i-1];
    	}
    	data[index] = b;
    	size++;
    }
    
    public boolean remove(int index) {
    	boolean removed = get(index);
    	for (int i = index; i < size - 1; i++) {
    		data[i] = data[i + 1];
    	}
    	data[size] = false;
    	return removed;
    }
    
    public boolean isEmpty() {
    	return size() == 0;
    }
    

    public void ensureCapacity(int minCapacity) {
    	if (minCapacity > data.length) {
    		int newSize = data.length * 2;
        	boolean[] newData = new boolean[newSize];
        	for (int i = 0; i < data.length; i++) {
        		newData[i] = data[i];
        	}
        	data = newData;
    	}
    }
}
