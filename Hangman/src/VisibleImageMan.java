import objectdraw.DrawableInterface;
import objectdraw.DrawingCanvas;
import objectdraw.FramedOval;
import objectdraw.VisibleImage;
import objectdraw.Line;
import java.awt.Image;

public class VisibleImageMan {
	protected FramedOval head;
	
	protected VisibleImage body;
	protected VisibleImage leftArm;
	protected VisibleImage rightArm;
	protected VisibleImage leftLeg;
	protected VisibleImage rightLeg;
	
	protected DrawableInterface[] bodyParts;
	
	protected static final int MAX_INCORRECT = 6;
	protected static final int HEAD_SIZE = 80;
	protected static final int BODY_SIZE = 80;
	protected static final int ARM_LENGTH = 50;
	protected int numIncorrect;
	
	//Use images instead of lines for hangman
	public VisibleImageMan(double xPos, double yPos, VisibleImage head, VisibleImage body, VisibleImage leftArm, VisibleImage rightArm, VisibleImage leftLeg, VisibleImage rightLeg, DrawingCanvas canvas) {
		/* TODO: Initialize all of the man's body parts.
		 * Then, use the clear method to hide them all. 
		 * The given (xPos, yPos) specifies the coordinates
		 * for the top of the man's head.
		 */
		
		//Move and set sizes of body parts
		head.moveTo(310, 140);
		head.setSize(100, 100);
		body.moveTo(235, 157);
		body.setSize(347, 330);
		body.sendBackward();
		leftArm.moveTo(187, yPos + 2);
		leftArm.setSize(300, 180);
		rightArm.moveTo(237, yPos + 2);
		rightArm.setSize(300, 180);
		leftLeg.moveTo(295, yPos + 220);
		leftLeg.setSize(120, 90);
		rightLeg.moveTo(312, yPos + 220);
		rightLeg.setSize(120, 90);
		
		//Initialize bodyParts as an a array of DrawableInterface
		bodyParts = new DrawableInterface[6];
		//[0] = head, [1] = body, [2] = leftArm, [3] = rightArm
		//[4] = leftLeg, [5] = rightLeg
		bodyParts[0] = head;
		bodyParts[1] = body;
		bodyParts[2] = leftArm;
		bodyParts[3] = rightArm;
		bodyParts[4] = leftLeg;
		bodyParts[5] = rightLeg;

		//Hide all body parts
		this.clear();
		//Initialize numIncorrect = 0 when new Man is created
		numIncorrect = 0;
	}
	
	private Image getImage(String string) {
		// TODO Auto-generated method stub
		return null;
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