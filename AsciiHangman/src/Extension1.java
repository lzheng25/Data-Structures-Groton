import java.io.Console;
import java.util.Scanner;

//Displays incorrect letters that the user has already guessed
public class Extension1 {
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
		char[] incorrectGuessesList = new char[6];
		int incorrectGuesses = 0;
		
		//Create array of what you've guessed so far, called puzzle
		//Initialize as blank (underscores)
		for(int i = 0; i < puzzle.length; i++) {
			puzzle[i] = '_';
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
				//Adds incorrect guess to array containing incorrect guesses
				incorrectGuessesList[incorrectGuesses] = guess;
				//Print incorrect guesses so far, with commas in between
				System.out.print("Incorrect guesses: ");
				for (int k = 0; k <= incorrectGuesses; k++) {
					System.out.print(incorrectGuessesList[k]);
					//Prints comma if not the last incorrect guess
					if (k != incorrectGuesses) {
						System.out.print(", ");
					}
				}
				//Go to next line
				System.out.println("");
				incorrectGuesses++;
				
			}
			
			//Checks if you got all the letters
			//If all underscores replaced, it means you got the whole word
			//Thus, victory
			//If not all replaced, no victory yet
			counter = 0;
			for (int i = 0; i < puzzle.length; i++) {
				if(puzzle[i] != '_') {
					counter++;
				}
				if (counter == puzzle.length) {
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