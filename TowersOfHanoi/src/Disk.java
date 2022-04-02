import java.awt.Color;

import objectdraw.*;

public class Disk extends ActiveObject{

    protected FilledRect disk;
    private Color one;
    private Color two;
    private int numSteps;
    private boolean victory;
    private Color[] gradient;
    private boolean bicolor;
    

    // private static final double DISK_HEIGHT = 40;
    // Pre: 3 <= n <= 7
    public Disk(int numDisks, int i, double x, double y, double height, double width, DrawingCanvas canvas, boolean bicolor) {
        disk = new FilledRect(x, y, height, width, canvas);
        disk.setColor(new Color(0, 170, 255/numDisks * i));
        victory = false;
        this.bicolor = bicolor;
        
        //Create an array of Colors representing gradient bw Color one and Color two
        //one = new Color(0, 255, 0);
        //two = new Color(0, 0, 255);
        //Gradient goes from green to blue with the colors above
        
       // one = new Color(255/7*i, 255/7*i, 255/7*i);
       // two = new Color(255/7*(7-i), 155/7*(7-i), 0/7*(7-i));
        
        one = new Color(0/7*i, 255/7*(7-i), 255/7*(7-i));
        two = new Color(0/7*i, 255/8*(i+1), 0/8*(i+1));
        
        getGradient(one, two);
    	
        
//        numSteps = 250;
//        
//        int r1 = one.getRed();
//        int g1 = one.getGreen();
//        int b1 = one.getBlue();
//        int a1 = one.getAlpha();
//
//        int r2 = two.getRed();
//        int g2 = two.getGreen();
//        int b2 = two.getBlue();
//        int a2 = two.getAlpha();
//
//        int newR = 0;
//        int newG = 0;
//        int newB = 0;
//        int newA = 0;
//
//        gradient = new Color[numSteps];
//        double iNorm;
//        for (int j = 0; j < numSteps; j++) {
//          iNorm = j / (double) numSteps; // a normalized [0:1] variable
//          newR = (int) (r1 + iNorm * (r2 - r1));
//          newG = (int) (g1 + iNorm * (g2 - g1));
//          newB = (int) (b1 + iNorm * (b2 - b1));
//          newA = (int) (a1 + iNorm * (a2 - a1));
//          gradient[j] = new Color(newR, newG, newB, newA);
//        }
        start();
        
    }
    
    public void getGradient(Color one, Color two) {
    	
    	
        
        numSteps = 250;
        
        int r1 = one.getRed();
        int g1 = one.getGreen();
        int b1 = one.getBlue();
        int a1 = one.getAlpha();

        int r2 = two.getRed();
        int g2 = two.getGreen();
        int b2 = two.getBlue();
        int a2 = two.getAlpha();

        int newR = 0;
        int newG = 0;
        int newB = 0;
        int newA = 0;

        gradient = new Color[numSteps];
        double iNorm;
        for (int j = 0; j < numSteps; j++) {
          iNorm = j / (double) numSteps; // a normalized [0:1] variable
          newR = (int) (r1 + iNorm * (r2 - r1));
          newG = (int) (g1 + iNorm * (g2 - g1));
          newB = (int) (b1 + iNorm * (b2 - b1));
          newA = (int) (a1 + iNorm * (a2 - a1));
          gradient[j] = new Color(newR, newG, newB, newA);
        }
    }

    public boolean contains(Location l) {
        return disk.contains(l);
    }
    
    public Color getColorOne() {
    	return one;
    }

    public double getHeight() {
        return disk.getHeight();
    }

    public double getWidth() {
        return disk.getWidth();
    }

    public FilledRect getRect() {
        return disk;
    }

    public void moveTo(Location point) {
        disk.moveTo(point);
    }

    public void moveTo(double x, double y){
        disk.moveTo(x, y);
    }
    
    public void remove() {
    	disk.removeFromCanvas();
    }
    
    public void setColor(Color c) {
    	disk.setColor(c);
    	if(c.equals(Color.RED)) {
    		one = new Color(255, 0, 0);
    		two = new Color(255, 0, 160);
    	} else if (c.equals(Color.BLUE)) {
    		one = new Color(0, 0, 255);
    		two = new Color(0, 160, 255);
    	}
    	getGradient(one, two);
    }
    
    public Color getColor() {
    	return disk.getColor();
    }
    
    public void win() {
    	victory = true;
    }
    
    public double getY() {
    	return disk.getY();
    }
    
    
    public void run() {
    	//Loop through gradient back and forth so animation is smooth
    	int i = 0;
    	boolean forward = true;
    	while(!victory) {
    		disk.setColor(gradient[i]);
    		if(forward) {
    			i++;
    			if(i==numSteps) {
    				forward = false;
    				i--;
    			}
    		} else {
    			i--;
    			if(i==-1) {
    				forward = true;
    				i++;
    			}
    		}
    		//pause 0.01 seconds
    		pause(10);
    	}
    }

}