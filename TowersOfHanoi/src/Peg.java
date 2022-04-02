import java.awt.Color;
import java.util.Stack;
import objectdraw.*;

public class Peg {

  protected Stack<Disk> disks;
  protected FilledRect peg;
  protected int size;
  protected Disk[] arrdisks;
  protected int numPeg;

  public Peg(double x, double y, double pegH, double pegW, int i, DrawingCanvas c){
    peg = new FilledRect(x+20, y-70, pegW, pegH, c);
    this.disks = new Stack<Disk>();
    this.size = 0;
    numPeg = i;
    peg.sendToBack();
  }
  
  public void setDisks(Disk[] disks) {
        reset();
        this.arrdisks = disks;
        this.size = disks.length;
        for(int i=0;i<disks.length;i++){
            this.disks.push(disks[i]);
        }
  }

  public Slide add(Disk d, boolean autoPlay, Slide slide) {
	disks.push(d);
    // double yPos = getTop().getY();
    // d.moveTo()
    // d.moveTo(peg.getX()+peg.getWidth()/2.0 - d.getWidth()/2, peg.getY()+peg.getHeight()-((size+1)*d.getHeight()));
    if(slide != null && !slide.isUndoing()) {
    	slide = new Slide(this, d, size, autoPlay);
    	slide.commence();
    }
    size++;
    return slide;
    
  }

  public void add(Disk d){
    disks.push(d);
    d.moveTo(peg.getX()+peg.getWidth()/2.0 - d.getWidth()/2, peg.getY()+peg.getHeight()-((size+1)*d.getHeight()));
    size++;
  }

  public Disk remove() {
      if(size==0) return null;
    size--;
    return disks.pop();
  }

  public Disk getTop() {
      if(size==0) return null;
      return disks.peek();
  }

  public Disk containsDisk(Location l){
    if(getTop()!=null && getTop().contains(l)) return getTop();
    return null;
  }

  public boolean overlaps(Drawable2DInterface disk){
    return peg.overlaps(disk);
  }

  public int getNumDisks() {
    return size;
  }

  public int getSize(){
      return size;
  }
  
  public void reset() {
	    while(!disks.empty()) {
	      Disk d = disks.pop();
	      d.remove();
	    }
	    size = 0;
  }
  
  public Stack<Disk> getDisks() {
	  return disks;
  }
  
  public Disk[] getArrDisks() {
	  return arrdisks;
  }
  
  public FilledRect getRect() {
	  return peg;
  }
  
  public boolean isEmpty() {
	  return disks.isEmpty();
  }

  public String toString() {
    return Integer.toString(numPeg);
  }

}