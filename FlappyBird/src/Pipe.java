import java.awt.Color;
import java.awt.Image;
import java.awt.color.*;
import java.util.ArrayList;

import objectdraw.*;

public class Pipe extends ActiveObject{
	protected Bird b;
	protected FilledRect p1;
	protected FilledRect p2;
	protected boolean alive = true;
	protected FlappyBird controller;
	DrawingCanvas c;
	
	public Pipe(double x, double y, double height, double width, Bird b, DrawingCanvas canvas, FlappyBird controller) {
		p1 = new FilledRect(x, y, width, height, canvas);
		p2 = new FilledRect(x, height + 200, width, canvas.getHeight(), canvas);
		this.b = b;
		p1.setColor(new Color(118,189,46));
		p2.setColor(new Color(118,189,46));
		c = canvas;
		this.controller = controller;
		start();
	}
	
	public void hide() {
		p1.hide();
		p2.hide();
	}
	
	public void run() {
		while (p1.getX() + p1.getWidth()>= 0) {
			if(controller.getReady.isHidden()) {
				p1.move(-1, 0);
				p2.move(-1, 0);
				if (isGameOver()) {
					p1.removeFromCanvas();
					p2.removeFromCanvas();
					alive = false;
					controller.gameOver();
					System.out.println("DIE BUG");
					break;
			}
			}
			pause(5);

		}
		if (alive) {
			p1.removeFromCanvas();
			p2.removeFromCanvas();
		}
	}
	
	public boolean isGameOver() {
		if (b.overlaps(p1) || b.overlaps(p2)) {
			return true;
		}
		return false;
	}


}

