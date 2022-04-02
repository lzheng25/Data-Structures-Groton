
import objectdraw.*;

/*
 * Class representing an empty scribble.
 */
public class EmptyScribble implements ScribbleInterface {

  public EmptyScribble() {
  }

  // point is never in an empty scribble!
  public boolean contains(Location point) {
    return false;
  }

  // nothing to move, so do nothing!
  public void move(double xOffset, double yOffset) {
  }
  
  //nothing to color, so do nothing!
  public void setColor(String color) {
  }
  
  //nothing to erase, so do nothing!
  public void erase() {
  }
  
//nothing to undo, so do nothing!
  public void undo() {
  }
  
  //nothing to check, so return true, as if line not there
  public boolean isHidden() {
	  return true;
  }

}