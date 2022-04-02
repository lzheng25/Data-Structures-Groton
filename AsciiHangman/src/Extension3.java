import java.io.Console;
import java.util.Scanner;

//User can type a whole word to guess the answer whenever they think they know it
public class Extension3 {
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
		int wordCheck = 0;
		
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
			System.out.print("Please guess a letter or the whole word: ");
			//Take input at String, and also take first character as a letter guess
			String wordGuess = s.nextLine().toUpperCase();
			char guess = wordGuess.charAt(0);
			wordCheck = 0;
			
			//Store the input as String also and check if it's same length as secret word.
			//If not same length, treat as a letter (take charAt(0)). Go to else statement
			//If same length as secret word, check if it's the same as secret word.
			//If same, then victory; if different, then count as wrong letter.
			if (wordGuess.length() == letters.length) {
				//Check if same word
				//Show word guess
				System.out.println(wordGuess);
				for (int k = 0; k < letters.length; k++) {
					if (wordGuess.charAt(k) == letters[k]) {
						wordCheck++;
					}
				}
				//If all letters same, set puzzle to the answer (secret word).
				//Victory, so break
				if (wordCheck == letters.length) {
					puzzle = letters.clone();
					victory = true;
					break;
				} else {
					//Word not same
					//hang and print hangman
					g.hang();
					System.out.println(g.toString());
				}
			} else {
				//Treat as a letter since not same length
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