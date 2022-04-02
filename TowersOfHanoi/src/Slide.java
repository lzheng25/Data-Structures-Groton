import objectdraw.*;


//This makes the disk slide from the top of the peg to the bottom
public class Slide extends ActiveObject {
	
	private FilledRect peg;
	private FilledRect disk;
	private int size;
	private boolean autoPlay;
	private boolean running;
	
	public Slide(Peg peg, Disk disk, int size, boolean autoPlay) {
		this.peg = peg.getRect();
		this.disk = disk.getRect();
		this.size = size;
		this.autoPlay = autoPlay;
		this.running = false;
		
		//start();
	}
	
	//d.moveTo(peg.getX()+peg.getWidth()/2.0 - d.getWidth()/2, peg.getY()+peg.getHeight()-((size+1)*d.getHeight()));
	
	public void commence() {
		this.running = true;
		start();
	}
	
	public boolean isUndoing() {
		return running;
	}
	
	public void run() {
		//if reset, disks glide directly to bottom of first peg
		//if autoplay mode, then disk glides to top of peg
		if(autoPlay) {
			//get vector of path from disk to top of peg
			double y = (peg.getY() - disk.getY()) / 600.0;
			double x = ((peg.getX()+peg.getWidth()/2-disk.getWidth()/2) - disk.getX())  / 600.0;
			while(disk.getY() >= peg.getY()) {
				disk.move(x, y);
				pause(1);
			}
		} else {
			disk.moveTo(this.peg.getX()+this.peg.getWidth()/2.0 - this.disk.getWidth()/2, this.peg.getY());
		}
		//move until it hits the bottom
		while(disk.getY() < peg.getY()+peg.getHeight()-((size+1)*disk.getHeight())) {
			disk.move(0, 1);
			pause(1);
		}
		running = false;
	}	
	
		
		//Bouncing
		
//		while(disk.getY() > peg.getY()+peg.getHeight()-((size+2)*disk.getHeight())) {
//			disk.move(0, -1);
//			pause(1.1);
//		}
//		while(disk.getY() < peg.getY()+peg.getHeight()-((size+1)*disk.getHeight())) {
//			disk.move(0, 1);
//			pause(1.1);
//		}
//		
//		double bounce = disk.getHeight()/2;
//		while(disk.getY() > peg.getY()+peg.getHeight()-((size+2)*disk.getHeight())+bounce) {
//			disk.move(0, -1);
//			pause(1.1);
//		}
//		while(disk.getY() < peg.getY()+peg.getHeight()-((size+1)*disk.getHeight())) {
//			disk.move(0, 1);
//			pause(1.1);
//		}
//		
//		bounce/=2;
//		while(disk.getY() > peg.getY()+peg.getHeight()-((size+2)*disk.getHeight())+bounce) {
//			disk.move(0, -1);
//			pause(1.1);
//		}
//		while(disk.getY() < peg.getY()+peg.getHeight()-((size+1)*disk.getHeight())) {
//			disk.move(0, 1);
//			pause(1.1);
//		}

}
