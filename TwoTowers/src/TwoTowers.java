import java.util.ArrayList;

/*
 * Thought Questions
 * 
 * 1. The best solution to the 15 block problem is using blocks 4, 5, 6, 10, 11, 12, and 13. The height of one
 * block is the square root of its surface area, and since one unit is 1/10 of an inch, we find a blocks height by
 * dividing the number to 10 then taking its square root. After adding all them up, the total height is 6.3986, which
 * is the closest possible combination to 6.3987 (half height of all the blocks combined).
 * 
 * 2. To implement randomSubset, you need to generate a random number between 0 and 2^n - 1, where n is the
 * number of blocks, instead of the traditional counter in my current Iterator class. You would generate this
 * random number every time in the get method and use this number to calculate the subset. The possibility of
 * repeats is negligible since we would only use  randomSubset when n is very large.
 */

public class TwoTowers {
	
	private static int n = 15;
	
	public static void main(String[] args) {
		
		//Create blocks and dad n blocks to it
		ArrayList<Integer> blocks = new ArrayList<Integer>();
		System.out.println("We have " + n + " blocks of surafce areas 1 to " +
							n + ". Each unit is one-tenth of an inch.");
		
		for(int i = 1; i <= n; i++) {
			blocks.add(i);
		}
		
		//Print total height and half height
		double totalHeight = totalHeight(blocks);
		System.out.println("Total height of blocks is " + totalHeight + " inches.");
		
		double halfHeight = totalHeight/2.0;
		System.out.println("Block subset height cannot exceed " + halfHeight + " inches.");
		System.out.println();
		
		long iteration = 0;
		
		Iterator i = new Iterator(blocks);
		ArrayList<Integer> firstBlocks = i.next();
		
		double maxHeight = totalHeight(firstBlocks);
		ArrayList<Integer> bestBlocks = firstBlocks;
		
		double secondHeighest = maxHeight;
		ArrayList<Integer> secondBestBlocks = firstBlocks;
		
		while(i.hasNext()) {
			//System.out.println(i.next());
			ArrayList<Integer> currentBlocks = i.next();
			double currentSubsetHeight = totalHeight(currentBlocks);
			
			//If height > second highest and < highest, then height is the second highest
			if(currentSubsetHeight > secondHeighest && currentSubsetHeight < maxHeight) {
				secondHeighest = currentSubsetHeight;
				secondBestBlocks = currentBlocks;
			}
			
			//If height is smaller than the half height (the limit) and > highest, then height is the highest
			//Get the counter
			if(currentSubsetHeight < halfHeight && currentSubsetHeight > maxHeight) {
				bestBlocks = currentBlocks;
				maxHeight = currentSubsetHeight;
				iteration = i.getCounter();
			}
			
			
		}
		
		System.out.println("Best solution:");
		System.out.println("The best solution of blocks with " + n + " blocks is " + bestBlocks +
							" with a height of " + maxHeight + " inches.");
		System.out.println("The best solution was found on the " + iteration + "th iteration, so " + (iteration-1) + 
				" in binary gives us the best subset.");
		System.out.println("The tower is " + (halfHeight - maxHeight) + " away from half height.");
		System.out.println("The two towers differ by " + (totalHeight - 2 * maxHeight) + " inches.");
		
		System.out.println();
		
		System.out.println("Second best solution:");
		System.out.println("The second best solution of blocks with " + n + " blocks is " + secondBestBlocks +
				   " with a height of " + secondHeighest + " inches.");
		System.out.println("The tower is " + (halfHeight - secondHeighest) + " away from half height.");
		System.out.println("The two towers differ by " + (totalHeight - 2 * secondHeighest) + " inches.");
		System.out.println("    --> Same as in the textbook, proving that it's the second best.");
	}
	
	//Return the sum of the square roots of the elements, then divided by ten
	//Need square root because the numbers are the surface areas of the blocks
	//Divide by ten after because 1 unit is 1/10 of an inch
	public static double totalHeight(ArrayList<Integer> v) {
		double d = 0;
		for(int i : v) {
			d += Math.sqrt((double)i)/10.0;
		}
		return d;
	}

}
