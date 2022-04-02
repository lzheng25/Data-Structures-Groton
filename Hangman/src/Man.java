import objectdraw.*;

public class Man {
	protected FramedOval head;
	protected DrawableInterface[] bodyParts;
	
	protected static final int MAX_INCORRECT = 6;
	protected static final int HEAD_SIZE = 80;
	protected static final int BODY_SIZE = 80;
	protected static final int ARM_LENGTH = 50;
	protected int numIncorrect;

	public Man(double xPos, double yPos, DrawingCanvas canvas) {
		/* TODO: Initialize all of the man's body parts.
		 * Then, use the clear method to hide them all. 
		 * The given (xPos, yPos) specifies the coordinates
		 * for the top of the man's head.
		 */
		
		//Initialize bodyParts as an a array of DrawableInterface
		bodyParts = new DrawableInterface[6];
		head = new FramedOval(xPos - HEAD_SIZE/2, yPos, HEAD_SIZE, HEAD_SIZE, canvas);
		
		//[0] = head, [1] = body, [2] = leftArm, [3] = rightArm
		//[4] = leftLeg, [5] = rightLeg
		bodyParts[0] = head;
		bodyParts[1] = new Line(xPos, yPos + HEAD_SIZE, xPos, yPos + HEAD_SIZE + BODY_SIZE, canvas);
		bodyParts[2] = new Line(xPos, yPos + HEAD_SIZE + 30, xPos - 50, yPos + HEAD_SIZE + 10, canvas);
		bodyParts[3] = new Line(xPos, yPos + HEAD_SIZE + 30, xPos + 50, yPos + HEAD_SIZE + 10, canvas);
		bodyParts[4] = new Line(xPos, yPos + HEAD_SIZE + 80, xPos - 50, yPos + HEAD_SIZE + 130, canvas);
		bodyParts[5] = new Line(xPos, yPos + HEAD_SIZE + 80, xPos + 50, yPos + HEAD_SIZE + 130, canvas);
		
		//Hide all body parts
		this.clear();
		//Initialize numIncorrect = 0 when new Man is created
		numIncorrect = 0;
	}
	
	public void clear() {
		/* TODO: Hide all of the man's body parts */
		for (int i = 0; i < bodyParts.length; i++) {
			bodyParts[i].hide();
		}
		
	}
	
	public void hang() {
		/* TODO: Hang the man */
			bodyParts[numIncorrect].show();
			numIncorrect++;
		}
	
	public boolean isAlive() {
		/* TODO: Return true if the man is not fully
		 * hanged.  Otherwise, return false. 
		 */
		if (numIncorrect < MAX_INCORRECT) {
			return true;
		}
		return false;
	}
	
}