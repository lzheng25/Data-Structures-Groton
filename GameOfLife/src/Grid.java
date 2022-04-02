import java.awt.Color;
import objectdraw.*;

public class Grid extends ActiveObject {

	protected FilledRect[][] grid;
	protected FramedRect[][] borders;
	protected int box_size;
	protected boolean running;
	protected static final int XOFFSET=8;
	protected static final int YOFFSET=8;
	
	public Grid(int window_size, int box_size, DrawingCanvas canvas) {
		this.box_size = box_size;
		grid = new FilledRect[(window_size-2*YOFFSET)/box_size][(window_size-2*XOFFSET)/box_size];
		borders = new FramedRect[(window_size-2*YOFFSET)/box_size][(window_size-2*XOFFSET)/box_size];
		for(int row=0; row<grid.length; row++) {
			for(int col=0; col<grid[0].length; col++) {
				grid[row][col] = new FilledRect(col*box_size+XOFFSET, row*box_size+YOFFSET, box_size, box_size, canvas);
				grid[row][col].setColor(Color.WHITE);
				borders[row][col] = new FramedRect(col*box_size+XOFFSET, row*box_size+YOFFSET, box_size, box_size, canvas);
			}
		}
		running = false;
    	this.start();
	}
	
	/* TODO: Update this method to return the cell in which the
	 * given point resides. 
	 */
	public Cell getCell(Location point) {
		//Assign point's coordinates to x and y doubles.
		double x = point.getX();
		double y = point.getY();
		//If clicked outside of grid, return null
		if (x >= 8.0 && x <= (double) (8.0 + grid[0].length * box_size) && y >= 8.0 && y <= (double) (8.0 + grid.length * box_size)) {
			//Now it's in the grid, find row and col. Then return the Cell
			//row goes down; col goes to the right
			int col = (int) ((x - 8.0) / (double) box_size);
			int row = (int) ((y - 8.0) / (double) box_size);
			return new Cell(row, col);
		}
		return null;
	}
	
	/* TODO: Update this method to make a black cell white or a 
	 * white cell black.  Also return the cell that you toggled. 
	 */
	public Cell toggle(Location point) {
		//Get Cell of point and toggle it
		Cell c = this.getCell(point);
		this.toggle(c.getRow(), c.getCol());
		return c;
	}
	
	/* TODO: Given a row and column in the grid, switch the
	 * color of the cell at that position. 
	 */
	public void toggle(int row, int col) {
		if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length) {
			//If the cell is black, turn white. Vice versa
			if (grid[row][col].getColor() == Color.WHITE) {
				grid[row][col].setColor(Color.BLACK);
			} else {
				grid[row][col].setColor(Color.WHITE);
			}
		}	
	}
	
	public void toggleRunning() {
		running = !running;
	}
	
	/* TODO: Return true if the cell at the given row and col is alive.
	 * NB: row and col may be values that are outside the grid. 
	 * Cells outside the grid are not alive. 
	 */
	protected boolean isAlive(int row, int col) {
		//If row and col is not valid, return false
		if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length) {
			//If the cell is black, return true.
			//If the cell is white, return false;
			return grid[row][col].getColor() == Color.BLACK;
		}
		//Return false if given row col not valid
		return false;
	}
	
	/* TODO: Return the number of alive cells that are adjacent
	 * to the given row and col. 
	 */
	protected int liveNeighbors(int row, int col) {
		int liveNeighbors = 0;
		//Loop through the cells around the given row and col
        for(int r = -1; r < 2; r++) {
            for(int c = -1; c < 2; c++) {
            	/* If the cell is not the cell with the given row and col, and it is
            	 * alive, then increment liveNeighbors
            	 */
            	if ((r != 0 || c != 0) && isAlive(row + r, col + c)) {
            		liveNeighbors++;
            	}
            }
        }
		return liveNeighbors;
	}
	
	
	/* TODO: Set all of the cells in the grid to WHITE/off/dead
	 */
	public void clear() {
		//Loop through all cells and change color to white
		for(int row = 0; row < grid.length; row++) {
			for(int col = 0; col < grid[0].length; col++) {
				grid[row][col].setColor(Color.WHITE);
			}
		}
	}
	
	/* TODO: Set a given cell to BLACK/alive/on if it is within
	 * the grid. 
	 */
	private void on(int row, int col) {
		//If row and col is valid, set cell's color to black
		if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length) {
			grid[row][col].setColor(Color.BLACK);
		}
	}
	
	/* Mystery method.  Figure out when it gets used and why it's 
	 * interesting.  
	 */
	public void gliderGun(int row, int col) {
		on(row,col);
		on(row,col+1);
		on(row+1,col);
		on(row+1,col+1);
		on(row,col+10);
		on(row+1,col+10);
		on(row+2,col+10);
		on(row+3,col+11);
		on(row-1,col+11);
		on(row-2,col+12);
		on(row-2,col+13);
		on(row+4,col+12);
		on(row+4,col+13);
		on(row+1,col+14);
		on(row-1,col+15);
		on(row+3,col+15);
		on(row,col+16);
		on(row+1,col+16);
		on(row+2,col+16);
		on(row+1,col+17);
		on(row,col+20);
		on(row,col+21);
		on(row-1,col+20);
		on(row-1,col+21);
		on(row-2,col+20);
		on(row-2,col+21);
		on(row-3,col+22);
		on(row+1,col+22);
		on(row-3,col+24);
		on(row+1,col+24);
		on(row-4,col+24);
		on(row+2,col+24);
		on(row-1,col+34);
		on(row-2,col+34);
		on(row-1,col+35);
		on(row-2,col+35);

	}
	
	public void run() {
		while(true) {
			if(running) {
				// TODO:  Insert the logic to play the Game of Life
				//Create 2D boolean. true means toggle color in nextgen.
				boolean[][] nextgen = new boolean[grid.length][grid[0].length];
				//pre: empty 2D boolean of same dimensions as grid
				//post: the 2D boolean is returned with true representing change in color
				//		and false representing no change in color
				//loop through each cell and test
				//only need to return true since boolean's default value is false
				for(int row = 0; row < grid.length; row++) {
					for(int col = 0; col < grid[0].length; col++) {
						int liveNeighbors = this.liveNeighbors(row, col);
						if (isAlive(row, col)) {
							//If Alive but is over-populated/lonely, it should die in nextgen.
							//Set to true
							if (liveNeighbors >= 4 || liveNeighbors <= 1) {
								nextgen[row][col] = true;
							}
						}
						//Since not alive, set to true if cell has 3 live neighbors.
						else if (liveNeighbors == 3) {
							nextgen[row][col] = true;
						}
					}
				}
				//Loop through all cells of nextgen
				//If true, toggle color
				for (int row = 0; row < nextgen.length; row++) {
					for (int col = 0; col < nextgen[0].length; col++) {
						if (nextgen[row][col]) {
							toggle(row, col);
						}
					}
				}
			}
			pause(100);	
		}
	}
}