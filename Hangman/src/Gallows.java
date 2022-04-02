import objectdraw.*;

public class Gallows {

	protected FilledRect base;
	protected FilledRect beam;
	protected FilledRect crossbeam;
	protected FilledRect rope;
	protected Man man;
	
	protected static final int BEAM_WIDTH = 10;

	
	public Gallows(double xPos, double yPos, DrawingCanvas canvas)
	{
		/* TODO: Initialize the instance variables that constitute the 
		 * frame of the gallows. The given (xPos, yPos) specifies the
		 * coordinates of the bottom left corner of the gallows.  
		 */
		man = new Man(xPos + BEAM_WIDTH * 16, yPos - BEAM_WIDTH * 25, canvas);
		//Initialize parts of gallows
		base = new FilledRect(xPos, yPos + BEAM_WIDTH, 117.5, BEAM_WIDTH, canvas);
		beam = new FilledRect(xPos + 59, yPos - BEAM_WIDTH - 295, BEAM_WIDTH, 295 + 2 * BEAM_WIDTH, canvas);
		crossbeam = new FilledRect(xPos + 59, yPos - BEAM_WIDTH - 295, 106, BEAM_WIDTH, canvas);
		rope = new FilledRect(xPos + BEAM_WIDTH * 16 - BEAM_WIDTH/2, 
							  yPos - BEAM_WIDTH * 25 - 50, BEAM_WIDTH, 50, canvas);
		//Hide man
		man.clear();
	}
	
	public void hang() {
		man.hang();
	}
	
	public boolean isAlive() {
		return man.isAlive();
	}
	
	public void clear() {
		/* TODO: Hide all of the elements of the 
		 * gallows, and clear the man.
		 */
		base.hide();
		beam.hide();
		crossbeam.hide();
		rope.hide();
		man.clear();
		
	}
}