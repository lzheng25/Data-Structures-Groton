import objectdraw.*;
import java.awt.Image;

public class Shield extends ActiveObject implements Consumable {
	protected boolean isConsumed;
	private static final int SHIELD_WIDTH = 80;
	private static final int SHIELD_HEIGHT = 80;
	protected VisibleImage shield;
	protected double velocity;
	protected Ship ship;
	protected boolean flying;
	protected DrawingCanvas c;

	
	public Shield(Image shieldImage, double x, double y, double velocity, Ship ship, DrawingCanvas c) {
		shield = new VisibleImage(shieldImage, x-SHIELD_WIDTH, y, SHIELD_WIDTH, SHIELD_HEIGHT, c);
		this.c = c;
		this.velocity = velocity;
		this.ship = ship;

		isConsumed = false;
		start();
	}
	
	public void checkCollision(){
        if(shield.overlaps(ship.getBody())){
        	ship.consume(shield);
        	isConsumed=true;
        }
    }

	@Override
	public boolean isConsumed() {
		// TODO Auto-generated method stub
		return isConsumed;
	}
	
	 public Drawable2DInterface getBody(){
		 return shield;
	 }
	
	public void remove() {
		shield.removeFromCanvas();
	}
	
    public void consume(){
    	isConsumed = true;
    }
	
	public void run(){
        while(!isConsumed){
            double currentY = shield.getY();
            if(currentY>0&&currentY<c.getHeight()){
            	shield.move(0,velocity);
                checkCollision();
            }
            else{
                consume();
            }
            pause(30);
       }
       if(shield.overlaps(SpaceInvaders.background)) shield.removeFromCanvas();
    }
}
