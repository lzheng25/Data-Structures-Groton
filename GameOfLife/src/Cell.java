
public class Cell {

	protected int row;
	protected int col;
	
	/* TODO: Initialize instance variables */
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/* TODO: Write the following accessor methods */
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	/* @returns true if o is a Cell and o has the same
	 * row and col as the current cell
	 * Hint: Recall that java has an instanceof keyword
	 */
	public boolean equals(Object o) {
		//o is an instance of Cell, cast o to a Cell object
		if (o instanceof Cell) {
			Cell c = (Cell) o;
			//return true if the row and col are the same for the compared cells
			return (row == c.getRow() && col == c.getCol());
		}
		//return false if not o isn't instance of Cell
		return false;
	}
}