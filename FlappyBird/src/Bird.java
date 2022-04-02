import java.awt.Image;
import objectdraw.*;

public class Bird extends ActiveObject{
	
	private static final double GRAVITY = 6;
	protected VisibleImage bird;
	protected boolean inGame;
	protected boolean alive;
	protected boolean flapping;
	protected int time;
	protected Image i;
	protected DrawingCanvas canvas;
	protected FlappyBird controller;
	
	public Bird(Image i, DrawingCanvas canvas, FlappyBird controller) {
		bird = new VisibleImage(i, 150, 330, canvas);
		this.canvas = canvas;
		this.i = i;
		this.controller = controller;
		bird.hide();
		bird.setWidth(bird.getWidth()/(double)9);
		bird.setHeight(bird.getHeight()/(double)9);
		bird.sendToFront();
		bird.show();
		inGame = false;
		alive = true;
		flapping = false;
		time = 0;
		start();
	}
	
	public void playGame() {
		inGame = true;
	}
	
	public void stopGame() {
		inGame = false;;
	}
	
	public void flap() {
		time = 1;
		flapping = true;
	}
	
	public void clear() {
		try {
			bird.removeFromCanvas(); 
			}
		catch(IllegalStateException e){
			
		}
	}
	
	public double getWidth() {
		return bird.getWidth();
	}
	
	public double getY( ) {
		return bird.getY();
	}
	
	public boolean overlaps(FilledRect pipe) {
		return bird.overlaps(pipe);
	}
	
	public void run() {
		while(alive) {
			bird.sendToFront();
//			i.flush();
//			bird.setImage(i);
			time = 1;
			while(bird.getY()+bird.getHeight() < 650) {
				if(flapping) {
					bird.move(0, -1);
					time++;
					if(time == 100) {
						time = 0;
						flapping = false;
					}
				} else {
					if(inGame) {
						bird.move(0, 0.5*GRAVITY*Math.pow(time/1000.0, 2));
						//bird.move(0, 10);
						time++;
					}
				}
				if(bird.getY()+bird.getHeight() >= 650) {
					break;
				}
				pause(1);
			}
			alive = false;
			controller.gameOver();
		}

	}
}
