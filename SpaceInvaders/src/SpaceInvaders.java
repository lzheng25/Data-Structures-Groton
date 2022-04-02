import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Thought Questions
 * 
 * 1. Space Invaders was such a hot-selling game because it's just fun and can be really difficult. Back
 * then there wasn't much technology nor many video games so I imagine everyone would have loved a game 
 * where you control a spaceship, trying to kill aliens that are shooting at you. The difficulty ramps up
 * too as you survive longer (aliens get faster) so you have the drive to beat the game and show off
 * your high score.
 * 
 * 2. You could create a BaseBlock class that renders a FilledRect building block for the base and 
 * implements the Target interface, so that when a bullet hits it, the block is destroyed. To make the 
 * bigger bases, you could make a Base class that constructs a 2D array of BaseBlocks (much like the 
 * Invaders class creates an array of Aliens. Just like how Invaders has the getTargets method to return 
 * all the current targets (the alive aliens and alien bullets that are flying), we could create a similar 
 * getTargets method in the Base class to return all the remaining targets (in this case, BaseBlocks). 
 * Just like how we passed the Invaders into the Ship class and the Ship into the Invaders class so that 
 * they could set their target, we could pass in the Base class into both Ship and Invaders so that they 
 * could use the getTargets method to add the BaseBlocks to every bullet they fire.
 *
 * 3. You could create a UFO class that extends ActiveObject and creates a VisibleImage of a UFO. You 
 * would instantiate a UFO object when you hit the screen to play. The run method would contain a 
 * move(dx, dy) function to move the UFO across the screen. And since the UFO only appears periodically, 
 * you could put the move method under an if statement like if(Math.random()<0.05), so the code only runs 
 * when a randomly generated number between 0 and 1 is under 0.05, or a 5% chance to go across the screen. 
 * If it reaches x=0 or x=canvas.getWidth(), then you could invoke a kill() method on the BaseBlocks that 
 * kills all of them. Finally, since the UFO can only run when the bases are alive, you need to insert a 
 * BaseBlocks checkALive() method inside the if statement with the random number, so that the UFO cannot 
 * run when the bases are not alive.

 */

public class SpaceInvaders extends WindowController implements KeyListener {

	private static final long serialVersionUID = 1L;
	// Constants for the window
	private static final int HEIGHT=800;
	private static final int WIDTH = 800;
	
	protected Ship ship;
	protected Invaders invaders;
	protected static FilledRect background;
	protected Text title;
	protected Image shipImage;
	protected Image[] alienImages;
	protected boolean running;
	protected Image shieldImage;
	protected Image invincibleShip;
	protected Image multishotImage;
	protected Ship iShip;

	// remember whether a key is currently depressed
	private boolean keyDown;
	
	public void begin() {
		
		/* This code will make it so the window cannot be resized */
		
		Container c = this;
		while(! (c instanceof Frame)) {
			c = c.getParent();
		}
		((Frame)c).setResizable(false);
		
		background = new FilledRect(0,0,WIDTH, HEIGHT, canvas);
		background.setColor(Color.BLACK);
		background.sendToFront();
		background.hide();

		title = new Text("Click to play", canvas.getWidth()/2, canvas.getHeight()/2, canvas);
		title.setFontSize(24);
		title.moveTo(title.getX()-title.getWidth()/2, title.getY());
		
		shipImage = getImage("ship.png");
		

		alienImages = new Image[5];
		for(int i=1;i<=5;i++){
			alienImages[i-1] = getImage("invader" + i +".png");
		}
		
		shieldImage = getImage("shield.png");
		invincibleShip = getImage("invincibleShip.png");
		multishotImage = getImage("multishot.png");
		
		running = false;

		requestFocus();
		addKeyListener(this);
		canvas.addKeyListener(this);
	}
	

	
	public void onMouseClick(Location l) {
		if(!running){
			running = true;
			title.hide();
			background.show();
			ship = new Ship(shipImage, invincibleShip, invaders, this, canvas);
			invaders = new Invaders(alienImages, canvas, this, ship, ship, shieldImage, multishotImage);
			ship.setTarget(invaders);
		}
	}

	public void loss(){
		ship.clear();
		invaders.clear();
		title.setText("You lose! Score: " + invaders.getPoints());
		title.setColor(Color.RED);
		title.moveTo(canvas.getWidth()/2 - title.getWidth()/2, title.getY());
		title.show();
		running = false;
		
	}

	public void win(){
		ship.clear();
		invaders.clear();
		title.setText("You win! Score: " + invaders.getPoints());
		title.setColor(Color.GREEN);
		title.moveTo(canvas.getWidth()/2 - title.getWidth()/2, title.getY());
		title.show();
		running = false;
	}
	
	// Handle the arrow keys by telling the ship to go in the direction of the arrow.
	public void keyTyped(KeyEvent e)
	{
		if ( e.getKeyCode() == KeyEvent.VK_SPACE || 
				 e.getKeyCode() == KeyEvent.VK_UP ) {
	       
				// insert code to handle press of up arrow or space bar
				ship.shoot();
			}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
 
			// insert code to handle press of left arrow
			ship.setDirection(-1);

		} else if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			
			// insert code to handle press of right arrow		
			ship.setDirection(1);	
        }
	}

	// Remember that the key is no longer down.
	public void keyReleased(KeyEvent e)
	{
		keyDown = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT || 
                    e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			// insert code to handle key release (optional stopping of base)
			ship.setDirection(0);
		}
	}

	// Handle the pressing of the key the same as typing.
	public void keyPressed(KeyEvent e)
	{
		if (!keyDown)
		{
			keyTyped(e);
		}

		keyDown = true;
	}
	
    public static void main(String[] args) { 
        new SpaceInvaders().startController(WIDTH, HEIGHT); 
        String filepath = "tetris.wav";
        SoundEffect sound = new SoundEffect();
        sound.playSong(filepath);
	}
	
}
