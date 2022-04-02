import java.awt.Color;
import java.awt.Container;
import javax.swing.JRootPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objectdraw.*;

/*  Thought Questions
 *  1. When I run the simulation after hitting "g," the animations seems to be 2 gliders going
 *  back and forth from their stations. The 2 gliders, when they meet in the middle, also 
 *  produce these bullets that fly off at a 45 degree angle down. This animation is cool
 *  because it's an infinite recurring pattern of the 2 gliders going back and forth and
 *  creating bullets. These bullets are very interesting to because it actually flies forever
 *  if the grid was infinite. The pattern is significant because it is infinitely creating new 
 *  black squares, new life, from a finite number of cells out of thin air by just following those 3 rules.
 *  
 *  2. Since all the cells change concurrently, the entire grid needs to be updated each life cycle. As you
 *  can see in run() of Grid.java, a new boolean array is created for every generation of life so the game
 *  takes up more and more memory after each turn.
 *  
 *  3. A con of making Cell class be responsible for FilledRect and FramedRect objects is that it takes 
 *  more memory since each box in the grid is represented as a Cell object. One pro is that we can use 
 *  FilledRect methods with the Cell objects. For example, with the original getCell method we needed to 
 *  do some arithmetic to get the row and col of the click then create the respective Cell object. However,
 *  this new way allows us to use use contains() method for each Cell then return the Cell that contains 
 *  the click's location. It's much simpler and runs faster.
 *  
 *  4. Currently, the implementation uses a finite grid to store each cell and its state. To make the board
 *  infinite, instead of storing a grid (that includes both alive cells and dead cells) you could store an 
 *  ArrayList of Cell objects that are alive. Each Cell object's row/col could then just act like its 
 *  coordinates instead of slots in a grid, so they could extend past the limits of a grid and achieve an 
 *  infinite board. The method to check the state of a next turn would then have to be changed to loop 
 *  through the ArrayList of cells and check all the surrounding "coordinates" for each cell. For the next 
 *  generation, the alive cells, and any "newly born" cells could then all be added to a new ArrayList.
 */

public class GameOfLife extends WindowController implements KeyListener {
	
	protected static final int WINDOW_SIZE = 616;
	protected static final int BOX_SIZE = 15;
	protected Cell lastToggledCell;
	
	protected Grid grid;
	
	public void begin() {
		int yoffset = 0;
		
		/* The coordinate system of the grid is thrown off slightly by
		 * the existence of the system menu bar.  The code below figures out
		 * the height of the menu bar. The call to resize at the end of this
		 * method takes this offset into account when making the whole grid
		 * visible. 
		 */
		Container c = this;
		while(! (c instanceof JRootPane)) {
			yoffset += (int)(c.getParent().getY());
			c = c.getParent();
		}
		grid = new Grid(WINDOW_SIZE, BOX_SIZE, canvas);
        requestFocus();
        addKeyListener(this);
        canvas.addKeyListener(this);
        lastToggledCell = null;
        resize(WINDOW_SIZE, WINDOW_SIZE + yoffset);
	}
	
	public void onMousePress(Location point) {
		/* TODO: Toggle the cell that was clicked on
		 * and keep track of what cell you just 
		 * changed. 
		 */
		lastToggledCell = grid.toggle(point);
	}
	
	public void onMouseDrag(Location point) {
		/* TODO: Toggle the cell under the mouse if
		 * it wasn't the last cell to be toggled. 
		 */
		//Set c to the cell at given point.
		Cell c = grid.getCell(point);
		//If c is an actual cell that is not lasToggledCell, toggle the cell and set it
		//to lastToggledCell
		if (c != null && !c.equals(lastToggledCell)) {
			lastToggledCell = grid.toggle(point);
		}
	}
	
    // Required by KeyListener Interface.
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e)
    {
    	char letter = e.getKeyChar();
    	if(letter == 'g' && lastToggledCell != null) {
    		grid.gliderGun(lastToggledCell.getRow(), lastToggledCell.getCol());
    	} else if (letter == 'c') {
    		/* TODO: Clear the grid */
    		grid.clear();
    	}
    	else {
    		/* TODO: Toggle whether the grid is running */
    		grid.toggleRunning();
    	}
    }
	
    public static void main(String[] args) { 
        new GameOfLife().startController(WINDOW_SIZE, WINDOW_SIZE); 
	}
    
}