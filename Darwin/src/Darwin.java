import java.util.ArrayList;
import java.util.Collections;

/* Thought Questions
 * 
 * 1. To check if a species has completely eliminated the other, we loop through the creatures in the
 * ArrayList of creatures to check if their species are the same. If all are the same, then that species 
 * has won. So for our animation loop, instead of while(true), the loop will only run there is more than one
 * species on the grid. once we exit this loop, we add one win to the species. We can track wins using an
 * ArrayList and assign each index to one of the species. Now, to get an overall win percentage of one
 * species versus another, we need to run the animation loop many times for a large sample size. Let's say
 * 50 times for this example. To do this, we would just place the while loop inside a for loop that runs 50 times.
 * Finally, to calculate our win percentage for a species, get its number of wins from the ArrayList of wins
 * and divide it by the total number of rounds which is 50.
 * This all seems functional but it actually wouldn't guarantee an answer. In some scenarios, the animation
 * never ends. For example, in a battle between Rovers and Flytraps, we could end up with all the Flytraps
 * bunched up and stationary in the middle while a singular Rover is roaming around the edge of the world 
 * indefinitely. Here, it would be a never ending game of Darwin where no one species would come out as 
 * victorious, thus halting our program.
 * 
 * 2. Compiled languages run faster than interpreted languages since the source code is already precompiled, but
 * they run through errors in the code without stopping. Compiled languages can cause stack overflow/system crashes, 
 * and when those occur it’s harder to locate the errors.
 * Interpreted languages stop at errors but they run slower since the source code is compiled in real time. 
 * JIT compilers are both compilers and interpreters. When a function is called, they compile it and then run 
 * the compiled machine-code. Since the program is running off machine-code, it will be faster than a regular compiler. 
 * Furthermore, if a certain function has errors, the JIT compiler knows where the errors are in that specific function 
 * since they occur after that function is compiled. Thus, JIT compilers have the advantages of both compiled 
 * and interpreted languages.
 * 
 * 3. I do agree that to go statement is too primitive, and, through creating some of my own creatures, I realized
 * how messy and confusing my code can get with them. I think on a basic level to go statement works great for
 * Darwin. At the end of the txt file, you simply use "go 1" to loop back to the start which is extremely concise.
 * Flytrap is an excellent example of using the go statement in an easy way. However, as we build more complex creatures,
 * the go statement can get out of hand - even the rover creature is slightly confusing, especially since it has numerous
 * go statements. When I am creating my own creature, the go statement is very annoying because I need to change the address
 * of go because I added a few lines here and there. Furthermore, complex creatures can lead to do overuse of go, making the code
 * hard to decipher. And if you forget a go statement or give it the wrong address, it can be very hard to debug. 
 * Overall, I think the go statement is great for basic programs, but more complicated programs will inevitably result in the
 * overuse of the go statement, leading to unstructured, even illegible code.
 */
/**
* This class controls the simulation. The design is entirely up to
* you. You should include a main method that takes the array of
* species file names passed in and populates a world with species of 
* each type.
* <p>
* Be sure to call WorldMap.pause every time
* through the main simulation loop or else the simulation will be too fast
* and keyboard / mouse input will be slow. For example: 
* <pre>
*	public void simulate() {
*	for (int rounds = 0; rounds < numRounds; rounds++) {
*           giveEachCreatureOneTurn();
*           WorldMap.pause(100);
*         }
*	}
* </pre>
*/

class Darwin {

	/**
	* The array passed into main will include the arguments that 
	* appeared on the command line. For example, running "java 
	* Darwin Hop.txt Rover.txt" will call the main method with s 
	* being an array of two strings: "Hop.txt" and "Rover.txt". 
	*/
	public static void main(String s[]) {
		//Create 15x15 world
		World<Creature> world = new World<Creature>(15, 15);
		WorldMap.createWorldMap(15, 15);
		//ArrayList stores all creatures
		ArrayList<Creature> creatures = new ArrayList<Creature>();
		//Loop through all species and populate 10 of them randomly on the map
		for(String fileName : s) {
			//Create Species object for current species
			Species species = new Species(fileName);
			int numPopulated = 0;
			while(numPopulated < 10) {
				//Create a random position and direction for each of the 10 creatures
				Position pos = new Position((int) (Math.random()*14), (int) (Math.random()*14));
				int dir = (int) (Math.random()*4);
				//If random spot in world not taken, populate it with a creature
				if(world.get(pos) == null) {
					//Create creature with appropriate variables and add to ArrayList.
					//Add it to world and increment numPopulated
					Creature c = new Creature(species, world, pos, dir);
					creatures.add(c);
					world.set(pos, c);
					numPopulated++;
				}
			}
		}
		//Run animation forever. User closes window to quit
		//Loop through creatures, take one turn, and pause
		while(true) {
			//Shuffle ArrayList order so no creature has step order advantage
			Collections.shuffle(creatures);
	    	for(Creature creature : creatures) {
	    		creature.takeOneTurn();
	    	}
	    	WorldMap.pause(10);
		}
	}	
}
