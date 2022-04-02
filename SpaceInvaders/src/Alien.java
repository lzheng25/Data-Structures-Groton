import objectdraw.*;
import java.awt.Image;
import java.awt.Color;

public class Alien implements Target {
    VisibleImage alien;
    protected boolean alive;
    DrawingCanvas canvas;
    int alienIndex;
    Invaders invaders;

    public Alien(Image image, double x, double y, double width, double height, Invaders invaders, DrawingCanvas canvas){
        alien = new VisibleImage(image, x, y, width, height, canvas);
        alive = true;
        this.canvas = canvas;
        this.invaders = invaders;
    }

    public void moveX(double dx){
        alien.move(dx, 0);
    }

    public void moveY(double dy){
        alien.move(0, dy);
    }

    public boolean isAlive(){return alive;}

    public Bullet shoot(Target t){
        return new Bullet(getX()+getWidth()/2, getY()+getHeight(), 10, t, Color.RED, canvas);
    }

    // public boolean checkImpact(FilledRect bullet){
    //     return alien.overlaps(bullet);
    // }

    public Drawable2DInterface getBody(){
        return alien;
    }

    public boolean checkAlive(){
        return alive;
    }

    //methods for Target interface
    public void kill(FilledRect b){
        alive = false;
        alien.hide();
        invaders.killed(alienIndex);
        String filepath = "point.wav";
        SoundEffect sound = new SoundEffect();
        sound.playMusic(filepath);
    }
    
    public double getX(){return alien.getX();}
    public double getY(){return alien.getY();}
    public double getHeight(){return alien.getHeight();}
    public double getWidth(){return alien.getWidth();}

	@Override
	public boolean invincibilityStatus() {
		// TODO Auto-generated method stub
		return false;
	}

}
