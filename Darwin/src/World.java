/**
 * This module includes the functions necessary to keep track of the creatures
 * in a two-dimensional world. In order for the design to be general, the
 * interface adopts the following design:
 * <p>1. The contents are unspecified objects.
 * <p>2. The dimensions of the world array are specified by the client. <p>
 * There are many ways to implement this structure. HINT:
 * look at the structure.Matrix class. You should need to add no more than 
 * about ten lines of code to this file.
 */
import structure.*;
public class World<E>
{
	
	/**
	 * This function creates a new world consisting of width 
	 * columns and height rows, each of which is numbered beginning at 0. 
	 * A newly created world contains no objects.
	 * @param w The width of the world that is to be created
	 * @param h The height of the world that is to be created
	 * @pre w > 0
	 * @pre h > 0
	 */
	
	private Matrix world;
	
	//Use Assert for all methods just in case we encounter errors later on
	//Comes in handy
	
	public World(int w, int h)  {
		//Use Matrix constructor
		Assert.pre(w>0&&h>0, "Width and height must be greater than 0");
		world = new Matrix(w, h);
	}

	/**
	 * Returns the height of the world.
	 */
	public int height() {
		//Use Matrix method
		return world.height();
	}

	/**
	 * Returns the width of the world.
	 */
	public int width() {
		//Use Matrix method
		return world.width();
	}

	/**
	 * Returns whether pos is in the world or not. 
	 * @pre pos is a non-null position. 
	 * @post returns true if pos is an (x,y) location in the bounds of
	 *       the board.
	 */
	boolean inRange(Position pos)  {
		return pos.getX()>=0 && pos.getY()>=0 && pos.getX()<world.width() && pos.getY()<world.height();
	}

	/**
	 * Sets a position on the board to contain c.
	 * @param c The object that is to be added
	 * @param pos Where c is to be added
	 * @pre pos is a non-null position on the board.
	 */
	public void set(Position pos, E c) {
		//Use Matrix method
		Assert.pre(inRange(pos), "Position out of range");
		world.set(pos.getX(), pos.getY(), c);
	}

	/**
	 * Return the contents of a position on the board. 
	 * @pre pos is a non-null position on the board.
	 */
	@SuppressWarnings("unchecked")
	public E get(Position pos) {
		//Cast object to E
		Assert.pre(inRange(pos), "Position out of range");
		return (E) world.get(pos.getX(), pos.getY());
	}
	
	// Test code to test if you can add objects to the world and define generic type
	public static void main(String[] args) {
		//Create World with generic type String
		World<String> w = new World<String>(10, 10);
		Assert.condition(w.width() == 10, "Width not the same");
		Assert.condition(w.height() == 10, "Height not the same");
		//World of (10, 10) has coordinates from 0 to 9
		//Create a position and add a string in that position in world
		Position p = new Position(5, 5);
		Assert.condition(w.inRange(p), "inRange() not working");
		Assert.condition(!w.inRange(new Position(20, 20)), "inRange() not working");
		System.out.println(w.inRange(p));
		String s = "hi";
		//If you try to add non-String types, it doesn't work which is good
		w.set(p, s);
		Assert.condition(w.get(p).equals("hi"), "Obtained String not equal to set String");
		System.out.println(w.get(p));
	}
}
