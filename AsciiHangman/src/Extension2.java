import java.io.Console;
import java.util.Scanner;

//Allows the word that the user needs to guess to be multiple words
public class Extension2 {
	public static void main(String[] args) {
		System.out.println("Welcome to the ASCII Version of Hangman!");
		
		Console c = System.console();
		Scanner s = new Scanner(System.in);
		char[] letters;
		
		String prompt = "Please enter a secret word: ";
		if(c != null) {
			letters = c.readPassword(prompt);
			for(int i=0; i<letters.length; i++) {
				letters[i] = Character.toUpperCase(letters[i]);
			}
		} else {
			System.out.println("For best results, please run this from the command line.");
			System.out.print(prompt);
			letters = s.nextLine().trim().toUpperCase().toCharArray();
			for(int i=0; i<10000; i++) System.out.println();
		}
		
		// TODO: Write the code to play Hangman here.
		
		Gallows g = new Gallows();
		boolean containsGuess = false;
		boolean victory = false;
		char[] puzzle = new char[letters.length];
		int counter = 0;
		int numSpaces = 0;
		
		//Create array of what you've guessed so far, called puzzle
		//Initialize as blank (underscores)
		for(int i = 0; i < puzzle.length; i++) {
			puzzle[i] = '_';
			//Makes the space that separates the words (replaces the underscore)
			if (letters[i] == ' ') {
				puzzle[i] = ' ';
			}
		}
		
		//Count number of spaces in puzzle
				for (int j = 0; j < puzzle.length; j++) {
					if (puzzle[j] == ' ') {
						numSpaces++;
					}
				}
		
		System.out.println(g.toString());
		
		//Play game when still alive or when still haven't solved
		while (g.isAlive() && !victory) {
			System.out.print("Puzzle to solve: ");
			//Print out puzzle
			for (int i = 0; i < puzzle.length; i++) {
				System.out.print(puzzle[i] + " ");
			}
			//Skips to next line
			System.out.println("");
			System.out.print("Please guess a letter: ");
			char guess = s.nextLine().toUpperCase().charAt(0);
			
			//Show guess
			System.out.println(guess);
			
			//Check to see if guess matches one of the letters
			for (int i = 0; i < letters.length; i++) {
				if (letters[i] == guess) {
					//A match is found
					containsGuess = true;
					/* Replace the blanks in puzzle that corresponds 
					   to the correctly guessed letter.
					   Useful if the word contains multiple of same letter */
					for (int j = 0; j < letters.length; j++) {
						if (letters[j] == guess) {
							puzzle[j] = guess;
						}
					}
					//Break out of loop so user can guess another letter
					break;
				} else {
					//No match found for current element, continue onto next elements
					containsGuess = false;
				}
			}
			//Answer doesn't contain user's letter
			//Hang, and print hangman
			if (containsGuess == false) {
				g.hang();
				System.out.println(g.toString());
			}
			
			//Checks if you got all the letters
			//Since the answer can be many words, victory is achieved in a diff way
			//If 2 words, then there is 1 space, which means counter needs to be 1
			//less than length of puzzle for victory. Counter needs to be numSpaces less
			//than length of puzzle
			//Counter goes up if not underscore and not a space
			counter = 0;
			for (int i = 0; i < puzzle.length; i++) {
				if(puzzle[i] != '_' && puzzle[i] != ' ') {
					counter++;
				}
				if (counter == puzzle.length - numSpaces) {
					victory = true;
				} else {
					victory = false;
				}
			}
			
		}
		
		//Game done
		if (victory) {
			System.out.println("Success! Player 2 wins!");
		} else {
			System.out.println("Game over! Player 1 wins!");
		}
		

	}

}