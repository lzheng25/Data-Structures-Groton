
import java.awt.Color;

import objectdraw.*;

/*
 * A class to represent a non-empty scribble
 */
public class NonEmptyScribble implements ScribbleInterface {

  // an edge line of the scribble
  private Line first;        

  // the rest of the scribble
  private ScribbleInterface rest; 

  public NonEmptyScribble(Line segment, ScribbleInterface theRest) {
    first = segment;
    rest = theRest;
  }

  /*
   * Returns true iff the scribble contains the point.
   */
  public boolean contains(Location point) {
    return (first.contains(point) || rest.contains(point));
  }

  /*
   * Move the scribble by xOffset in the x direction
   *    and yOffset in the y direction
   */
  public void move(double xOffset, double yOffset) {
    first.move(xOffset, yOffset);
    rest.move(xOffset, yOffset);
  }
  
  // Sets color of scribble
  public void setColor(String color) {
	  Color c = null;
	  switch(color) {
		  case "red":
			  c = Color.RED;
			  break;
		  case "blue":
			  c = Color.BLUE;
			  break;
		  case "green":
			  c = Color.GREEN;
			  break;
		  case "yellow":
			  c = Color.YELLOW;
			  break;
	  }
	  first.setColor(c);
	  rest.setColor(color);
  }
  
  public void erase() {
	  first.hide();
	  // recursion
	  rest.erase();
  }
  
  public void undo() {
	  first.show();
	  // recursion
	  rest.undo();
  }
  
  //return isHidden()
  public boolean isHidden() {
	  return first.isHidden();
  }

}
