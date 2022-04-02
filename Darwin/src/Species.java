/**
 * The individual creatures in the world are all representatives of some
 * species class and share certain common characteristics, such as the species
 * name and the program they execute. Rather than copy this information into
 * each creature, this data is recorded once as part of the description for
 * a species and then each creature can simply include the appropriate species
 * pointer as part of its internal data structure.
 * <p>
 * 
 * To encapsulate all of the operations operating on a species within this
 * abstraction, this provides a constructor that will read a file containing
 * the name of the creature and its program, as described in the earlier part
 * of this assignment. To make the folder structure more manageable, the
 * species files for each creature are stored in a subfolder named Creatures.
 * Thus, creating the Species for the file Hop.txt will causes the program to
 * read in "Creatures/Hop.txt".
 * 
 * <p>
 * 
 * Note: The instruction addresses start at one, not zero.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import structure.*;
public class Species {
	
	/**
	 * Create a species for the given file. 
	 * @param fileName the name of the file containing the data for the species
	 * @pre fileName exists in the Creature subdirectory.
	 */
	
	private ArrayList<String> program;
	private ArrayList<Instruction> instructions;
	
	public Species(String fileName) {
		//Insert file lines into ArrayList
		program = new ArrayList<String>();
		File f = new File("Creatures/"+fileName);
		try {
			Scanner s = new Scanner(f);
			//Add lines to program before period or empty line
			while(s.hasNextLine()) {
				String line = s.nextLine();
				//If reach a period or line space, stop adding to ArrayList
				if(line.equals(".") || line.equals("")) break;
				program.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			System.exit(1);
		}
		
		instructions = new ArrayList<Instruction>();
		//After creating program ArrayList, need to convert every line into instructions ArrayList
		//Loop through all step lines (ignore first 2 lines)
		for(int i = 1; i <= program.size()-2; i++) {
			String step = program.get(i+1);
			String[] parts = step.split(" ");
			int address = 0;
			int testNum = 0;
			
			//Get address in form of integer
			if(parts.length == 2) {
				address = Integer.parseInt(parts[1]);
			} else if (parts.length == 3) {
				address = Integer.parseInt(parts[2]);
				testNum = Integer.parseInt(parts[1]);
			}
			//Convert function into opcode. Use upperCase for standardization
			switch(parts[0].toUpperCase()){
			case "HOP":
				instructions.add(new Instruction(1, address));
				break;
			case "LEFT":
				instructions.add(new Instruction(2, address));
				break;
			case "RIGHT":
				instructions.add(new Instruction(3, address));
				break;
			case "INFECT":
				instructions.add(new Instruction(4, address));
				break;
			case "IFEMPTY":
				instructions.add(new Instruction(5, address));
				break;
			case "IFWALL":
				instructions.add(new Instruction(6, address));
				break;
			case "IFSAME":
				instructions.add(new Instruction(7, address));
				break;
			case "IFENEMY":
				instructions.add(new Instruction(8, address));
				break;
			case "IFRANDOM":
				instructions.add(new Instruction(9, address));
				break;
			case "GO":
				instructions.add(new Instruction(10, address));
				break;
			case "IFTWOENEMY":
				instructions.add(new Instruction(11, address));
				break;
			case "IFEQ":
				instructions.add(new Instruction(12, testNum, address));
				break;
			case "INC":
				instructions.add(new Instruction(13, address));
				break;
			case "DEC":
				instructions.add(new Instruction(14, address));
				break;
			case "SET":
				instructions.add(new Instruction(15, address));
				break;
			case "IFENEMYLEFT":
				instructions.add(new Instruction(16, address));
				break;
			case "IFENEMYRIGHT":
				instructions.add(new Instruction(17, address));
				break;
			case "IFMEMEQ":
				instructions.add(new Instruction(18, testNum, address));
				break;
			case "COPYMEM":
				instructions.add(new Instruction(19, address));
				break;
			default :
				instructions.add(new Instruction(0, address));
				break;
			}
		}
	}
	

	/**
	 * Return the name of the species.
	 */
	public String getName() {
		//First line
		return program.get(0);
	}

	/**
	 * Return the color of the species.
	 */
	public String getColor() {
		//Second line
		return program.get(1);
	}

	/**
	 * Return the number of instructions in the program.
	 */
	public int programSize() {
		//-2 for name and color
		return program.size()-2;
	}

	/**
	 * Return an instruction from the program. 
	 * @pre 1 <= i <= programSize().
	 * @post returns instruction i of the program.
	 */
	public Instruction programStep(int i) {
		//Split each line into an Array
		Assert.pre(1<=i && i<=programSize(), "Step invalid.");
		return instructions.get(i-1);
	}

	/**
	 * Return a String representation of the program.
	 */
	public String programToString() {
		//Print each line
		String s = "";
		for(int i = 1; i <= program.size(); i++) {
			//Add line numbers for clarity
			//Also add line space at the end
			s += "Line " + i + ": ";
			s += program.get(i-1);
			s += '\n';
		}
		return s;
	}
	
	// Test code
	public static void main(String[] args) {
		//Print out contents of Flytrap species to test
		Species flytrap = new Species("Flytrap.txt");
		ArrayList<String> program = flytrap.program;
		//Check if our methods print out correctly using Assert
		Assert.condition(program.get(0).equals(flytrap.getName()), "Names not equal");
		System.out.println(flytrap.getName());
		Assert.condition(program.get(1).equals(flytrap.getColor()), "Colors not equal");
		System.out.println(flytrap.getColor());
		Assert.condition(program.size()-2==(flytrap.programSize()), "Program size not equal");
		System.out.println(flytrap.programSize());
		//Print out all steps and check correctness using Assert
		for(int i = 1; i <= flytrap.programSize(); i++) {
			String step = flytrap.programStep(i).toString();
			int opCode = flytrap.programStep(i).getOpcode();
			//If it's infect it adds a 0, so we need to remove that
			//because  Flytrap.txt don't contain 0
			if(opCode==4) {
				step = step.substring(0, step.length()-2);
			}
			Assert.condition(step.equals(program.get(i+1)), "Steps not equal");
			System.out.println(flytrap.programStep(i));
		}
		System.out.println(flytrap.programToString());
	}
}
