import objectdraw.*;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Invaders extends ActiveObject {

    protected static final int ALIENS_PER_ROW = 7;
    protected static final int ALIEN_HEIGHT = 40;
    protected static final int ALIEN_WIDTH = 40;
    protected static final int ALIEN_GAP = 30;
    protected static final int dx = 40;
    protected static final int dy = 70;
    
    protected Alien[] aliens;
    protected Vector<Target> bullets;
    protected SpaceInvaders controller;
    protected Target ship;
    protected boolean running;
    protected Random random;
    protected int aliensAlive;
    protected Image[] images;
    protected Vector<Bullet> shots;
    protected Image shieldImage;
    protected Shield shield;
    protected DrawingCanvas canvas;
    protected Ship s;
    protected Vector<Shield> shields;
    protected Image multishotImage;
    protected Multishot multishot;

    protected boolean forward;
    protected boolean down;

    public Invaders(Image[] images, DrawingCanvas canvas, SpaceInvaders controller, Target ship, Ship s, Image shieldImage, Image multishotImage){

        aliens = new Alien[ALIENS_PER_ROW*images.length];
        bullets = new Vector<Target>();
        this.ship = ship;
        random = new Random();
        forward = true;
        running = true;
        this.controller = controller;
        this.images = images;
        shots = new Vector<Bullet>();
        this.shieldImage = shieldImage;
        this.canvas = canvas;
        this.s = s;
        this.multishotImage = multishotImage;
        //shields = new Vector<Shield>();

        for(int j=0;j< ALIENS_PER_ROW; j++) {
            for(int i=0;i<images.length;i++){
                aliens[j*images.length + i] = new Alien(images[i], (1+j)*ALIEN_GAP + j*ALIEN_WIDTH, 
                (1+i)*ALIEN_GAP + i*ALIEN_WIDTH, ALIEN_WIDTH, ALIEN_HEIGHT, this, canvas);
            }
        }
        aliensAlive = aliens.length;
        start();
    }

    public void clear(){
        for(Alien a:aliens) a.getBody().removeFromCanvas();
        running = false;
        for(Bullet b:shots) {
        	if(b.getBody().overlaps(SpaceInvaders.background)) b.removeBullet();
        }
//        for(Shield s:shields) {
//        	if(s.getBody().overlaps(SpaceInvaders.background)) s.remove();
//        }
    }


    public String getPoints(){
        int maxPoints = aliens.length * 10;
        int userPoints = (aliens.length-aliensAlive)*10;
        return userPoints + "/" + maxPoints;
    }

    public Target[] getTargets(){
        Vector<Target> targets = bullets;
        Collections.addAll(targets, aliens);
        return targets.toArray(new Target[0]);
    }

    // public void removekilledAliens(){
    //     int alive = 0;
    //     for(Alien a:aliens) if(a.isAlive()) alive++;
    //     Alien[] aliveAliens = new Alien[alive];
    //     int index = 0;
    //     for(Alien a:aliens) if(a.isAlive()) aliveAliens[index++] = a;
    //     aliens=aliveAliens;
    // }

    public void killed(int index){
        //aliens[index] = null;
        aliensAlive--;
    }

    public void shoot(){
        boolean shot = false;
        ArrayList<Integer> shootable = new ArrayList<Integer>();
        boolean newCol = false;
        if(invadersAreAlive()) {
            while(!shot){
            	for(int j=0;j< ALIENS_PER_ROW; j++) {
            		newCol = true;
                    for(int i=0;i<images.length;i++){
                    	if(aliens[j*images.length+i].checkAlive()) {
                    		shootable.add(j*images.length+i);
                    		if(!newCol && shootable.size()!=1) shootable.remove(shootable.size()-2);
                    	}
                    	newCol = false;	
                    }
            	}
            	int i = random.nextInt(shootable.size());
                shot = true;
                bullets.add(aliens[shootable.get(i)].shoot(ship));
                shots.add(aliens[shootable.get(i)].shoot(ship));
                //50% chance for alien to spawn shield.
                if(Math.random()<0.2) {
                	i = random.nextInt(shootable.size());
                	double x = aliens[shootable.get(i)].getX();
               		double y = aliens[shootable.get(i)].getY();
               		shield = new Shield(shieldImage, x, y, 5.0, s, canvas);
               		//shields.add(shield);
               	} else if(Math.random()>0.8) {
               		i = random.nextInt(shootable.size());
               		double x = aliens[shootable.get(i)].getX();
               	    double y = aliens[shootable.get(i)].getY();
               	    multishot = new Multishot(multishotImage, x, y, 5.0, s, canvas);
               	}
            }
        }
    }
//    public void shoot(){
//        boolean shot = false;
//            while(!shot){
//                int i = random.nextInt(aliens.length);
//                shot = true;
//                bullets.add(aliens[i].shoot(ship));
//        }
//    }
    
    public double getHighestY(){
        double highestY = 0;
        for(Alien a:aliens){
        	if(a.checkAlive()) {
        		double y = a.getY();
        		if(y>highestY) highestY = y;
        	}
        }
        return highestY;
    }

    public boolean invadersAreAlive(){
        return aliensAlive>0;
    }

    public void run(){
        int iterations = 0;
        while(running){
            pause(100000/(95 + iterations++));
            iterations++;
            if(down){
                for(Alien a: aliens) a.moveY(dy);
                down = false;
            }
            else if(forward){
                for(Alien a: aliens) a.moveX(dx);
                if(aliens[aliens.length-1].getX()+aliens[aliens.length-1].getWidth()+dx>800){
                    forward= false; 
                    down = true;
                }
            }
            else{
                for(Alien a: aliens) a.moveX(-1*dx);
                if(aliens[0].getX()-dx<0){
                    forward= true; 
                    down = true;
                }
            }

            if(running) shoot();

            bullets.removeIf(bullet -> !(bullet.checkAlive()));

            if(!invadersAreAlive()) { 
            	controller.win();
            }
            else if(getHighestY()>ship.getBody().getY()) {
            	controller.loss();
            }

        }
        
    }
}
    
