import java.awt.Color;
import objectdraw.*;

public class Bullet extends ActiveObject implements Target {
    FilledRect bullet;
    DrawingCanvas canvas;
    Target[] targets;
    double velocity;
    private final int WIDTH = 4;
    private final int HEIGHT = 12;
    protected boolean flying;
    protected int canvasHeight;
    protected double velX;

    public Bullet(double x, double y, double velocity, Target[] targets, Color color, DrawingCanvas canvas){
        bullet = new FilledRect(x-WIDTH/2, y, WIDTH, HEIGHT, canvas);
        bullet.setColor(color);
        this.targets = targets;
        this.velocity = velocity;
        velX = 0;
        flying = true;
        canvasHeight = canvas.getHeight();
        start();
    }

    public Bullet(double x, double y, double velocity, Target target, Color color, DrawingCanvas canvas){
        this(x, y, velocity, new Target[1], color, canvas);
        velX = 0;
        targets[0] = target;
    }
    
    public Bullet(double x, double y, double velX, double velY, Target[] targets, Color color, DrawingCanvas canvas) {
    	bullet = new FilledRect(x-WIDTH/2, y, WIDTH, HEIGHT, canvas);
        bullet.setColor(color);
        this.targets = targets;
        velocity = velY;
        this.velX = velX;
        flying = true;
        canvasHeight = canvas.getHeight();
        start();
    }
    
    //Shield powerup
//    public Bullet(double x, double y, double velocity, Target target, Image i, DrawingCanvas canvas) {
//    	bullet = new VisibleImage(i, x-20, y, 40, 40);
//    }

    public void checkCollision(){
        for(Target target: targets){
            if(target==null || !target.checkAlive()) continue;
            if(bullet.overlaps(target.getBody())){
            	flying=false;
            	boolean b = target.invincibilityStatus();
            	if(!b) target.kill(bullet);
            	else if(b) bullet.removeFromCanvas();
            }
        }
    }
    
    public void removeBullet() {
    	bullet.removeFromCanvas();
    }

    public void kill(FilledRect b){
        flying = false;
    }

    public Drawable2DInterface getBody(){
        return bullet;
    }

    // public boolean checkImpact(FilledRect impact){
    //     return impact.overlaps(bullet);
    // }

    public boolean checkAlive(){
        return flying;
    }

    public void run(){
        while(flying){
            double currentY = bullet.getY();
            if(currentY>0&&currentY<canvasHeight){
                bullet.move(velX*4/5,velocity*4/5);
                checkCollision();
            }
            else{
                this.kill(bullet);
            }
            pause(22);
        }
        
        if(bullet.overlaps(SpaceInvaders.background)) bullet.removeFromCanvas();
    }

	@Override
	public boolean invincibilityStatus() {
		// TODO Auto-generated method stub
		return false;
	}

}
