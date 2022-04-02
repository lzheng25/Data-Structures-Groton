import java.util.Arrays;
public class Man {
	
	/*  An ASCII Hangman looks like this:
	 *   O 
	 *  \|/
	 *  / \
	 */

	protected static final int MAX_INCORRECT = 6;
	protected int numIncorrect;
	protected char[] body;
	
	public Man() {
		/* TODO: Initialize the Man object */
		body = new char[] {' ', ' ', ' ', '\n', ' ', ' ', ' ', '\n', ' ', ' ', ' ', '\n'};
		numIncorrect = 0;
	}
	
	public boolean isAlive() {
		/* TODO: Check if the man is alive */
		if (numIncorrect < 6) {
			return true;
		}
		return false;
	}
	
	public void hang() {
		/* TODO: modify the man data to reflect
		 * a new incorrect guess. 
		 */
		char[] manParts = {'O', '|', '\\', '/', '/', '\\'};
		int[] indexOfParts = {1, 5, 4, 6, 8, 10};
		body[indexOfParts[numIncorrect]] = manParts[numIncorrect];
		numIncorrect++;
	}
	
	public String toString() {
		/* TODO: Render the man as a string. */
		String s = new String(body);
		return s;
	}
	
	protected char[] toCharArray() {
		/* TODO: Return the relevant data */
		return body;
	}
	
	/* Again, this main method is provided to assist
	 * with testing. 
	 */
	public static void main(String[] args) {
		Man m = new Man();
		for(int i=0; i<Man.MAX_INCORRECT; i++) {
			m.hang();
			System.out.println(m);
		}
	}
	
}