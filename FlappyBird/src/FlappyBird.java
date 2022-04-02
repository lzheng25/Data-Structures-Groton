import java.util.ArrayList;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import objectdraw.*;

public class FlappyBird extends WindowController implements KeyListener {
	
	private static final double HEIGHT = 700;
	private static final double WIDTH = 600;
	
	protected VisibleImage background;
	protected Bird bird;
	protected Bird startBird;
	protected VisibleImage start;
	protected VisibleImage aiplay;
	protected VisibleImage logo;
	protected FilledRect orange;
	protected Text ai;
	protected boolean running;
	protected VisibleImage getReady;
	protected ScrollingBackground sb;
	
	protected int score = 0;
	protected Text scoreText;
	protected Text gameOverText;
	
	protected ArrayList<Pipe> pipeList;
	
	public void begin() {
		
		sb = new ScrollingBackground(getImage("flappy-bird-background.png"), HEIGHT, WIDTH, canvas);
	    startBird = new Bird(getImage("bird.png"), canvas, this);
	    start = new VisibleImage(getImage("start.png"), 125, 500, 150, 50, canvas);
	    logo = new VisibleImage(getImage("flappy-bird-logo.png"), 0, 0, canvas);
	    logo.moveTo(WIDTH/2-logo.getWidth()/2, 100);
	    
	    aiplay = new VisibleImage(getImage("start.png"), 325, 500, 150, 50, canvas);
	    orange = new FilledRect(aiplay.getX()+10, 510, 132, 27.5, canvas);
	    orange.setColor(new Color(224, 97, 25));
	    ai = new Text("AI PLAY", 0, 0, canvas);
	    ai.setColor(Color.WHITE);
	    ai.setFontSize(20);
	    ai.moveTo(orange.getX()+orange.getWidth()/2-ai.getWidth()/2, orange.getY()+orange.getHeight()/2-ai.getHeight()/2);
	    
	    scoreText = new Text(score, canvas.getWidth()/2, 50, canvas);
	    scoreText.setFontSize(30);
	    scoreText.moveTo((canvas.getWidth() / 2) - scoreText.getWidth()/2, 50);
	    scoreText.setColor(Color.WHITE);
	    
	    gameOverText = new Text("Game Over", canvas.getWidth()/2, 20, canvas);
	    gameOverText.setFontSize(30);
	    gameOverText.moveTo((canvas.getWidth() / 2) - gameOverText.getWidth()/2, 20);
	    gameOverText.setColor(Color.WHITE);
	    gameOverText.hide();
	    
	    getReady = new VisibleImage(getImage("get-ready.png"), 0, 0, 500, 333, canvas);
	    getReady.moveTo((WIDTH-getReady.getWidth())/2, (HEIGHT-getReady.getHeight())/2-30);
	    getReady.hide();
	    
	    running = false;
		requestFocus();
		addKeyListener(this);
		canvas.addKeyListener(this);
		
	}
	
	public void onMouseClick(Location l) {
		if(!running && start.contains(l)){
			startBird.clear();
			start.hide();
			aiplay.hide();
			orange.hide();
			ai.hide();
			logo.hide();
			gameOverText.hide();
			getReady.show();
		    bird = new Bird(getImage("bird.png"), canvas, this);
			running = true;
			pipeList = new ArrayList<Pipe>();
			Pipe p = new Pipe(canvas.getWidth(), 0, (int)(Math.random() * 300) + 20, bird.getWidth() * 1.5, bird, canvas, this);
			pipeList.add(p);
			score = 0;
			scoreText.setText(score);
		    scoreText.moveTo((canvas.getWidth() / 2) - scoreText.getWidth()/2, 50);
			scoreText.sendToFront();
		}	
	}
	
	public void gameOver(){
		running = false;
		bird.clear();
		for (int i = 0; i < pipeList.size(); i++) {
			pipeList.get(i).hide();
		}
		gameOverText.show();
		scoreText.setText("Score: " + score);
	    scoreText.moveTo((canvas.getWidth() / 2) - scoreText.getWidth()/2, 50);
		score = 0; 
		start.show();
		aiplay.show();
		orange.show();
		ai.show();
		logo.show();
	    startBird = new Bird(getImage("bird.png"), canvas, this);
	}

	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(running) {
			if(bird.getY() > 0 && e.getKeyCode() == KeyEvent.VK_SPACE) {
				getReady.hide();
				bird.playGame();
				bird.flap();
				if(pipeList.get(pipeList.size()-1).p1.getX()<bird.bird.getX()) {
					pipeList.add(new Pipe(canvas.getWidth(), 0, (int)(Math.random() * 300) + 20, bird.getWidth() * 1.5, bird, canvas, this));
					score++;
					scoreText.setText(score);
					scoreText.sendToFront();
				}
			}
		}
	}


	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	
	public static void main(String[] args) {
		new FlappyBird().startController((int)WIDTH, (int)HEIGHT+28); 
	}
	
}
