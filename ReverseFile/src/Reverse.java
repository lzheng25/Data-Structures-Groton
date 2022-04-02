import java.io.*;
import java.util.*;

/*  Thought Questions
 *  3.4 The setSize() method could be useful if you want to insert values in positions after
 *  the first index. For example, if you use setSize(4) you can then insert values into
 *  index 3 right away, skipping 0, 1, and 2. If you didn't use setSize() you can only insert
 *  values into index 0. You can set the size of a vector without using this method. You can
 *  use the add method to add an object to the ArrayList, which only increments the size by
 *  one each time. That's why setSize() is useful, so you can avoid needing to set
 *  values for indexes 0 and 1 before setting the value for index 2.
 * 
 *  2. The primary advantage of having a special purpose vector is because storing only 
 *  booleans in BitVector requires less memory than storing Objects in a vector, improving 
 *  performance. Special purpose vectors also help centralize the type of data and is more 
 *  efficient.
 * 
 *  3. When you add an element to an array of length n, you need to make a new array of length
 *  n + 1 and copy over the n elements from the original array. Thus, you copy over one 
 *  element when increasing array length from 1 to 2; four from 4 to 5. So when you grow an 
 *  array incrementally from length 0 to n, the total number of elements you need to copy over
 *  is 1 + 2 + 3 + ... + (n - 2) + (n - 1). The sum of this sequence is n(n - 1) / 2. But as 
 *  n becomes very large, the -1 and the 1/2 become negligible, thus simplifying the number
 *  of elements you need to copy over to n^2.
 */

public class Reverse {
	public static void main(String[] args) {
		//Create ArrayList of Strings
		ArrayList<String> lines = new ArrayList<String>();
		Scanner s = new Scanner(System.in);
		if (args.length>0) {
			File data = new File(args[0]);
			try {
				//If file found, put the lines in the file into lines ArrayList
				s = new Scanner(data);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//If cannot find file, print error message
				System.out.println("No such file.");
				System.exit(1);
			}
		}
		while(s.hasNextLine()) {
			lines.add(s.nextLine());
		}
		//Keep on receiving lines into ArrayList while there is a next line
		//Loop through lines backwards, from end to start
		for(int i = lines.size() - 1; i >= 0; i--) {
			//Separate the line into words at every space
			//Put each word into the array. array resets after every line automatically
			String[] words = lines.get(i).split(" ");
			//pre: take in array of strings
			//post: print out each element in reverse
			for(int j = words.length - 1; j >= 0; j--) {
				System.out.print(words[j] + " ");
			}
			//Create line space after each file line
			System.out.println("");
		}		
	}
}
