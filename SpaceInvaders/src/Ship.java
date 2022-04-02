import objectdraw.*;
import java.awt.*;
import java.util.Vector;

public class Ship extends ActiveObject implements Target {
    
    private static final int SHIP_WIDTH = 50;
    private static final int SHIP_HEIGHT = 50;
    private static final int SHIP_SPEED = 3;
    
    VisibleImage ship;
    VisibleImage iShip;
    DrawingCanvas canvas;
    Invaders invaders;
    SpaceInvaders controller;
    boolean alive;
    boolean clear;
    double velocity;
    protected Vector<Bullet> bullets;
    protected boolean invincible;
    protected Image image;
    protected Image invincibleShip;
    protected boolean multishot;
	protected int multishotCount;
	protected boolean b;
	private Text ammo;
    
    public Ship(Image image, Image invincibleShip, Invaders invaders, SpaceInvaders controller, DrawingCanvas canvas){
        ship = new VisibleImage(image, canvas.getWidth()/2.0, canvas.getHeight()-100, SHIP_WIDTH, SHIP_HEIGHT, canvas);
        this.image = image;
        this.canvas=canvas;
        this.invincibleShip = invincibleShip;
        velocity=0;
        alive=true;
        clear = false;
        this.invaders = invaders;
        this.controller = controller;
        bullets = new Vector<Bullet>();
        invincible = false;
        multishot = false;
        multishotCount = 0;
        ammo = new Text("4 multishots left", canvas.getWidth()/2.0, canvas.getHeight()-100, canvas);
       // ammo.moveTo(ship.getX()+ship.getWidth()/2-ammo.getWidth()/2, ship.getY()+ship.getHeight()+10);
        ammo.setFontSize(17);
        ammo.setColor(Color.CYAN);
        ammo.hide();
        start();
    }
    
    public void setDirection(int direction){
        if(direction == 1) velocity = SHIP_SPEED;
        else if(direction == -1) velocity = -1*SHIP_SPEED;
        else velocity = 0;
    }

    public void clear(){
        clear = true;
        ammo.removeFromCanvas();
        if(ship.overlaps(SpaceInvaders.background)) ship.removeFromCanvas();
        for(Bullet b:bullets) {
        	if(b.getBody().overlaps(SpaceInvaders.background)) b.removeBullet();
        }
    }

    public void shoot(){
    	if(!clear) {
			bullets.add(new Bullet(ship.getX()+ship.getWidth()/2, ship.getY(), -10, invaders.getTargets(), Color.WHITE, canvas));
			if(multishot && multishotCount > 0) {
				bullets.add(new Bullet(ship.getX()+ship.getWidth()/2, ship.getY(), -4, -10, invaders.getTargets(), Color.WHITE, canvas));
				bullets.add(new Bullet(ship.getX()+ship.getWidth()/2, ship.getY(), 4, -10, invaders.getTargets(), Color.WHITE, canvas));
				multishotCount--;
				if(multishotCount>1) ammo.setText(multishotCount+" multishots left");
				else if(multishotCount==1) ammo.setText("1 multishot left");
				else {
					ammo.hide();	
				}
			}
	        String filepath = "laser.wav";
	        SoundEffect sound = new SoundEffect();
	        sound.playMusic(filepath);
    	}
    }

    //methods for Target interface
    public void kill(FilledRect b){
    	b.removeFromCanvas();
    	if(invincible) {
    		alive = true;
    		turnOffInvincibility();
    	} else {
    		alive = false;
    	}
        //ship.removeFromCanvas();
    }

    public boolean checkAlive(){
        return alive;
    }
    
    public void consume(VisibleImage powerUp) {
    	turnOnInvincibility();
    }
    
    public boolean invincibilityStatus() {
    	if(invincible) {
    		turnOffInvincibility();
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void turnOnInvincibility() {
    	//If invincible the ship should have a little shield in front
    	if(!invincible) {
        	invincible = true;
//    		double x = ship.getX();
//        	double y = ship.getY();
       		//ship = new VisibleImage(invincibleShip, x-25, y, SHIP_WIDTH+50, SHIP_HEIGHT+10, canvas); 
       		ship.setImage(invincibleShip);
       		ship.setSize(SHIP_WIDTH+50, SHIP_HEIGHT+10);
       		ship.move(-25, 0);
    	}
    	invincible = true;
    }
    
    public void turnOffInvincibility() {
//    	double x = ship.getX();
//    	double y = ship.getY();
//		if(ship.overlaps(SpaceInvaders.background)) ship.removeFromCanvas();
    	ship.setImage(image);
		ship.move(20, 0);
		ship.setSize(SHIP_WIDTH, SHIP_HEIGHT);
    	invincible = false;
    }
    
    public void absorb(VisibleImage powerUp) {
    	multishotOn();
    }
    
    public void multishotOn() {
    	multishot = true;
    	multishotCount = 4;
		ammo.setText("4 multishots left");
		ammo.moveTo(ship.getX()+ship.getWidth()/2-ammo.getWidth()/2, ship.getY()+ship.getHeight()+10);
		ammo.show();
    }
    
    public void multishotOff() {
    	multishot = false;
    	multishotCount = 0;
    } 
    // public boolean checkImpact(FilledRect bullet){
    //     return ship.overlaps(bullet);
    // }

    public Drawable2DInterface getBody(){
        return ship;
    }

    public void setTarget(Invaders invaders){
        this.invaders = invaders;
    }
    
//    public void removeFromCanvas() {
//    	ship.removeFromCanvas();
//    }

    public void run(){
        while(alive || invincible){
            double currentX = ship.getX();
            if((currentX+velocity)>0 && (currentX+velocity+ship.getWidth())<canvas.getWidth()){
            	ship.move(velocity, 0);
            	ammo.move(velocity, 0);
            }
            if(clear) break;
            pause(15);
        }
        if(!alive && !invincible) controller.loss();
    }


}


