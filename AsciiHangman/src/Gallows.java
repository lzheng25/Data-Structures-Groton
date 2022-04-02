import java.util.Arrays;

public class Gallows {
	
	/* Gallows look like this:
	 *    ____
	 *   |    |
	 *   |    O
	 *   |   \|/
	 *   |   / \
	 * __|__
	 */
	
	protected Man man;
	protected char[] frame;

	public Gallows() {
		/* TODO: Initialize instance variables and 
		 * otherwise construct the Gallows object.
		 */
		man = new Man();
		frame = new char[60];
		for (int i = 0; i < frame.length; i++) {
			frame[i] = ' ';
			if ((i + 1) % 10 == 0) {
				frame[i] = '\n';
			}
		}
		makeCenterPost();
		makeBeam();
		makeBase();
		makeRope();
	}
	
	public void makeCenterPost() {
		/* TODO: Modify the frame to include
		 * the central post.
		 */
		frame[12] = '|';
		frame[22] = '|';
		frame[32] = '|';
		frame[42] = '|';
		frame[52] = '|';
	}
	
	public void makeBeam() {
		/* TODO: Modify the frame to include
		 * the top beam. 
		 */
		frame[3] = '_';
		frame[4] = '_';
		frame[5] = '_';
		frame[6] = '_';
	}
	
	public void makeBase() {
		/* TODO: Modify the frame to include the
		 * base of the gallows. 
		 */
		frame[50] = '_';
		frame[51] = '_';
		frame[53] = '_';
		frame[54] = '_';
	}
	
	public void makeRope() {
		/* TODO: Modify the frame to include the rope. */
		frame[17] = '|';
	}
	
	public void hang() {
		/* TODO: hang the hangman */
		man.hang();
		//Set manArray to body
		char[] manArray = man.toCharArray();
		//Set the body parts to the frame
		frame[27] = manArray[1];
		frame[37] = manArray[5];
		frame[36] = manArray[4];
		frame[38] = manArray[6];
		frame[46] = manArray[8];
		frame[48] = manArray[10];
	}
	
	public boolean isAlive() {
		/* TODO: Check if the hangman is alive */
		if (man.isAlive()) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		/* TODO: Render the hangman as a string
		 */
		String s = "";
		s = new String(frame);
		return s;
	}
	
	/* This code is included to allow you to test the
	 * Gallows independently from the Hangman code. 
	 */
	public static void main(String[] args) {
		Gallows g = new Gallows();
		System.out.println(g);
		for(int i=0; i< Man.MAX_INCORRECT; i++) {
			g.hang();
			System.out.println(g);
		}
	}
}