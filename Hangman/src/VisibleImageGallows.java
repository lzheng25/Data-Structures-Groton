import objectdraw.DrawingCanvas;
import objectdraw.FilledRect;
import objectdraw.VisibleImage;

import java.awt.Color;
import java.awt.Image;

public class VisibleImageGallows {

	protected FilledRect base;
	protected FilledRect beam;
	protected FilledRect crossbeam;
	protected FilledRect rope;
	protected VisibleImageMan visibleImageMan;
	
	protected static final int BEAM_WIDTH = 10;

	//Make gallows dark wood color instead of black
	//Make rope rope color
	//Elongate gallows to the right for more space
	public VisibleImageGallows(double xPos, double yPos, VisibleImage head, VisibleImage body, VisibleImage leftArm, VisibleImage rightArm, VisibleImage leftLeg, VisibleImage rightLeg, DrawingCanvas canvas)
	{
		/* TODO: Initialize the instance variables that constitute the 
		 * frame of the gallows. The given (xPos, yPos) specifies the
		 * coordinates of the bottom left corner of the gallows.  
		 */
		visibleImageMan = new VisibleImageMan(xPos + BEAM_WIDTH * 21, yPos - BEAM_WIDTH * 25, head, body, leftArm, rightArm, leftLeg, rightLeg, canvas);
		//Initialize parts of gallows
		//Make dark wood color with RGB
		//Make rope color with RGB
		Color wood = new Color(71, 46, 11);
		Color ropeColor = new Color(218,184,136);
		base = new FilledRect(xPos, yPos + BEAM_WIDTH, 117.5, BEAM_WIDTH, canvas);
		base.setColor(wood);
		beam = new FilledRect(xPos + 59, yPos - BEAM_WIDTH - 295, BEAM_WIDTH, 
				              295 + 2 * BEAM_WIDTH, canvas);
		beam.setColor(wood);
		beam.sendToBack();
		crossbeam = new FilledRect(xPos + 59, yPos - BEAM_WIDTH - 295,
				                   106 + BEAM_WIDTH * 5, BEAM_WIDTH, canvas);
		crossbeam.setColor(wood);
		crossbeam.sendToBack();
		rope = new FilledRect(xPos + BEAM_WIDTH * 21 - BEAM_WIDTH/2, 
							  yPos - BEAM_WIDTH * 25 - 55, BEAM_WIDTH, 55, canvas);
		rope.setColor(ropeColor);
		//Hide man
		visibleImageMan.clear();
	}
	
	public void hang() {
		visibleImageMan.hang();
	}
	
	public boolean isAlive() {
		return visibleImageMan.isAlive();
	}
	
	public void clear() {
		/* TODO: Hide all of the elements of the 
		 * gallows, and clear the man.
		 */
		base.hide();
		beam.hide();
		crossbeam.hide();
		rope.hide();
		visibleImageMan.clear();
		
	}
}