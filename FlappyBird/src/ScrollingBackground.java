import java.awt.Image;
import java.util.ArrayList;
import objectdraw.*;

public class ScrollingBackground extends ActiveObject{
	
	protected Image image;
	protected VisibleImage background;
	protected double width;
	protected double height;
	protected DrawingCanvas canvas;
	protected boolean nextBackgroundCycle;
	protected ArrayList<Pipe> pipeList;
	
	public ScrollingBackground(Image i, double h, double w, DrawingCanvas canvas) {
		image = i;
		background = new VisibleImage(i, -40, 0, i.getWidth(canvas)*h/i.getHeight(canvas), h, canvas);
		height = h;
		width = w;
		this.canvas = canvas;
		nextBackgroundCycle = false;
		start();
	}
	
	public ScrollingBackground(Image i, double h, double w, double x, DrawingCanvas canvas) {
		image = i;
		background = new VisibleImage(i, x-52, 0, i.getWidth(canvas)*h/i.getHeight(canvas), h, canvas);
		height = h;
		width = w;
		this.canvas = canvas;
		nextBackgroundCycle = false;
		background.sendToBack();
		start();
	}
	
	public void clear() {
		background.removeFromCanvas();;
	}
	
	public void run() {
		long pauseStart = System.currentTimeMillis();
		while( background.getX() + background.getWidth() >= 0) {
			long pauseEnd = System.currentTimeMillis();
			background.move((pauseEnd - pauseStart)* (-0.2), 0);
			if(!nextBackgroundCycle && background.getX() + background.getWidth() <= width) {
				background.sendBackward();
				new ScrollingBackground(image, height, width, 
						background.getX() + background.getWidth(), canvas);
				nextBackgroundCycle = true;
			}
			pauseStart = System.currentTimeMillis();
			pause(5);
		}
		background.removeFromCanvas(); //remove once out of window to conserve memory
		
	}
	
}
