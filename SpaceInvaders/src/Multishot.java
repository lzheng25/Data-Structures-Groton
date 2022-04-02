import objectdraw.*;
import java.awt.Image;

public class Multishot extends ActiveObject implements Consumable {
	protected boolean isConsumed;
	private static final int SHIELD_WIDTH = 60;
	private static final int SHIELD_HEIGHT = 60;
	protected VisibleImage multishot;
	protected double velocity;
	protected Ship ship;
	protected boolean flying;
	protected DrawingCanvas c;

	
	public Multishot(Image shieldImage, double x, double y, double velocity, Ship ship, DrawingCanvas c) {
		multishot = new VisibleImage(shieldImage, x-SHIELD_WIDTH, y, SHIELD_WIDTH, SHIELD_HEIGHT, c);
		this.c = c;
		this.velocity = velocity;
		this.ship = ship;

		isConsumed = false;
		start();
	}
	
	public void checkCollision(){
        if(multishot.overlaps(ship.getBody())){
        	ship.absorb(multishot);
        	isConsumed=true;
        }
    }

	@Override
	public boolean isConsumed() {
		// TODO Auto-generated method stub
		return isConsumed;
	}
	
	 public Drawable2DInterface getBody(){
		 return multishot;
	 }
	
	public void remove() {
		multishot.removeFromCanvas();
	}
	
    public void consume(){
    	isConsumed = true;
    }
	
	public void run(){
        while(!isConsumed){
            double currentY = multishot.getY();
            if(currentY>0&&currentY<c.getHeight()){
            	multishot.move(0,velocity);
                checkCollision();
            }
            else{
                consume();
            }
            pause(30);
       }
       if(multishot.overlaps(SpaceInvaders.background)) multishot.removeFromCanvas();
    }
}
