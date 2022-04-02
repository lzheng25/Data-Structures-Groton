import java.awt.Color;

import structure5.Assert;

/**
 * This class represents one creature on the board.
 * Each creature remembers its species, position, direction,
 * and the world in which it is living.
 * <p>
 * In addition, the Creature remembers the next instruction
 * out of its program to execute.
 * <p>
 * The creature is also responsible for making itself appear in
 * the WorldMap.  In fact, you should only update the WorldMap from
 * inside the Creature class. 
 */

public class Creature {
	
    /**
     * Create a creature of the given species, with the indicated
     * position and direction.  Note that we also pass in the
     * world-- remember this world, so that you can check what
     * is in front of the creature and to update the board
     * when the creature moves.
	 * @param species The species of the creature
	 * @param world The world in which the creature lives
	 * @param pos The position of the creature
	 * @param dir The direction the creature is facing
	 * @pre species, world, and pos must be non-null
	 * @pre pos must be within the bounds of world
	 * @pre dir must be one of: Position.NORTH, Position.SOUTH, Position.EAST
	 *                          or Positon.WEST
	 * @pre the world map must have been created
	 * @post creates a creature of species species in world world at position
	 *       pos facing direction dir
	 * @post initializes instance variables so that the creature knows what
	 *		 instruction to begin executing
	 * @post displays the creature on the world map
     */
	private Species species;
	private World<Creature> world;
	private Position pos;
	private int dir;
	private int step;
	private int memory;
	
    public Creature(Species species, World<Creature> world, Position pos, int dir) {
    	this.species = species;
    	this.world = world;
    	this.pos = pos;
    	this.dir = dir;
    	this.memory = 0;
    	step = 1;
    	WorldMap.displaySquare(pos, Character.toUpperCase(species.getName().charAt(0)), dir, species.getColor());
	}

    /**
     * Return the species of the creature.
     */
    public Species species() {
    	return species;
	}

    /**
     * Return the current direction of the creature.
     */
    public int direction() {
    	return dir;
    }

    /**
     * Return the position of the creature.
     */
    public Position position() {
    	return pos;
    }
    
    /**
     * Return the memory of the creature.
     */
    public int memory() {
    	return memory;
    }

    /**
     * Execute steps from the Creature's program until 
     * a hop, left, right, or infect instruction is executed.
	 * @post Creature takes one turn's worth of instructions
	 * @post display is updated to reflect movement of this creature
	 *
     */
    public void takeOneTurn() {
    	//Will set the boolean to false after hop, left, right, or infect to exit while loop
    	boolean execute = true;
    	//Loop through instructions of species and execute them
    	while(step <= species.programSize() && execute) {
    		Instruction currentStep = species.programStep(step);
    		Species victim;
    		Position adjacent = pos.getAdjacent(dir);
    		//System.out.println(step);
    		switch(currentStep.getOpcode()) {
    		case 1: //hop
    			//hop if square is inside grid and empty
    			if(world.inRange(adjacent) && world.get(adjacent) == null) {
    				WorldMap.displaySquare(pos, ' ', dir, species.getColor());
    				world.set(pos, null);
    	    		pos = pos.getAdjacent(dir);
    	    		world.set(pos, this);
    			}
    			execute = false;
    			break;
    		case 2: //left
    			dir=(dir+3)%4;
    			execute = false;
    			break;
    		case 3: //right
    			dir=(dir+1)%4;
    			execute = false;
    			break;
    		case 4: //infect
    			//For all cases check if adjacent square is in range of world
    			//And also check whether adjacent square is null (no creature) or not
    			//This prevents NullPointerException
    			if(world.inRange(adjacent) && world.get(adjacent) != null) {
    				victim = world.get(adjacent).species();
    	    		if(!victim.equals(species)) {
    	    			//Change species of step number of victim
    	    			world.get(adjacent).species = species;
    	    			if(currentStep.getAddress() == 0) world.get(adjacent).step = 1;
    	    			//Need to subtract -1 because step increments at end of while loop
    	    			else world.get(adjacent).step = currentStep.getAddress()-1;
    	    			//Display new square for victim to represent species change
    	    			WorldMap.displaySquare(adjacent, species.getName().charAt(0), world.get(adjacent).direction(), species.getColor());
    	    		}
    			}
    			execute = false;
    			break;
    		case 5: //ifempty
    			//Need Assert statement to make sure that it doesn't skip to a step the species doesn't have
    			Assert.pre(currentStep.getAddress() <= species.programSize(), "Step out of bounds");
    			if(world.inRange(adjacent) && world.get(adjacent) == null) {
    				step = currentStep.getAddress()-1;
    			
    			}
    			break;
    		case 6: //ifwall
    			Assert.pre(currentStep.getAddress() <= species.programSize(), "Step out of bounds");
    			if(!world.inRange(adjacent)) step = currentStep.getAddress()-1;
    			break;
    		case 7: //ifsame
    			Assert.pre(currentStep.getAddress() <= species.programSize(), "Step out of bounds");
    			if(world.inRange(adjacent) && world.get(adjacent) != null) {
    				victim = world.get(adjacent).species();
    				if(victim.equals(species)) {
    	    			step = currentStep.getAddress()-1;
    	    		}	
    			}	
    			break;
    		case 8: //ifenemy
    			Assert.pre(currentStep.getAddress() <= species.programSize(), "Step out of bounds");
    			if(world.inRange(adjacent) && world.get(adjacent) != null) {
    				victim = world.get(adjacent).species();
    				if(!victim.equals(species)) {
            			step = currentStep.getAddress()-1;
    				}
    			}
    			break;
    		case 9: //ifrandom
    			Assert.pre(currentStep.getAddress() <= species.programSize(), "Step out of bounds");
    			if(Math.random()<0.5) step = currentStep.getAddress()-1;
    			break;
    		case 10: //go
    			Assert.pre(currentStep.getAddress() <= species.programSize(), "Step out of bounds");
    			step = currentStep.getAddress()-1; //-1 bc step is incremented at end of while loop	
    			break;
    		case 11: //iftwoenemy
    			Assert.pre(currentStep.getAddress() <= species.programSize(), "Step out of bounds");
    			Position twoAhead = adjacent.getAdjacent(dir);
    			if(world.inRange(twoAhead) && world.get(twoAhead) != null) {
    				victim = world.get(twoAhead).species();
    				if(!victim.equals(species)) {
            			step = currentStep.getAddress()-1;
    				}
    			}
    			break;
    		//Case 12,13,14,15 are for extension 2
    		case 12: //ifeq
    			Assert.pre(currentStep.getAddress() <= species.programSize(), "Step out of bounds");
    			if(memory == currentStep.getTestCase()) step = currentStep.getAddress()-1;
    			break;
    		case 13: //inc
    			memory--;
    			break;
    		case 14: //dec
    			memory++;
    			break;
    		case 15: //set
    			memory = currentStep.getAddress();
    			break;
    		//Case 16 and 17 are for extension 1
    		case 16: //ifenemyleft
    			Assert.pre(currentStep.getAddress() <= species.programSize(), "Step out of bounds");
    			//Check left of creature
    			adjacent = pos.getAdjacent((dir+3)%4);
    			if(world.inRange(adjacent) && world.get(adjacent) != null) {
    				victim = world.get(adjacent).species();
    				if(!victim.equals(species)) {
            			step = currentStep.getAddress()-1;
    				}
    			}
    			break;
    		case 17: //ifenemyright
    			Assert.pre(currentStep.getAddress() <= species.programSize(), "Step out of bounds");
    			//Check right of creature
    			adjacent = pos.getAdjacent((dir+1)%4);
    			if(world.inRange(adjacent) && world.get(adjacent) != null) {
    				victim = world.get(adjacent).species();
    				if(!victim.equals(species)) {
            			step = currentStep.getAddress()-1;
    				}
    			}
    			break;
    		//Case 18 and 19 are for extension 3
    		case 18: //ifmemeq
    			Assert.pre(currentStep.getAddress() <= species.programSize(), "Step out of bounds");
    			//If adjacent creature's memory is v, go to n
    			if(world.inRange(adjacent) && world.get(adjacent) != null) {
    				if(world.get(adjacent).memory() == currentStep.getTestCase()) {
    					step = currentStep.getAddress()-1;
        			}
    			}
    			break;
    		case 19: //copymem
    			if(world.inRange(adjacent) && world.get(adjacent) != null) {
    				memory = world.get(adjacent).memory();
    			}
    			break;
    		}
    		step++;
		}
    	//After one turn is finished, update the creature's display
		WorldMap.displaySquare(pos, species.getName().charAt(0), dir, species.getColor()); 
    }
    
    // Test code
    public static void main(String[] args) {
    	//Test creature by creating a Rover creature. Try out takeOneTurn() and print out adjacent pos.
    	//Create appropriate variable to create a creature
    	WorldMap.createWorldMap(10, 10);
    	Species species = new Species("Rover.txt");
    	World<Creature> world = new World<Creature>(10, 10);
    	Position pos = new Position(3, 5);
    	Creature creature = new Creature(species, world, pos, 0);
    	Assert.condition(creature.species().equals(species), "Species not equal");
    	Assert.condition(creature.world.equals(world), "World not equal");
    	Assert.condition(creature.position().equals(pos), "Position not equal");
    	Assert.condition(creature.direction() == 0, "Direction not equal");
    	//Go through 30 steps and print out position and adjacent square to check if movement is normal
    	for(int step = 1; step < 31; step++) {
    		System.out.println("Step " + step);
    		System.out.print("Standing on " +creature.position());
        	System.out.println("Facing " +creature.position().getAdjacent(creature.direction()));
        	System.out.println("");
        	creature.takeOneTurn();
        	WorldMap.pause(100);
    	}
    }
}
