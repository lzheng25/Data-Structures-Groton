import java.io.Console;
import java.util.Scanner;

//Alternating game. Ends when one player stumps the other. Prints the number of games it took for someone to win
public class Extension4 {
	
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
		boolean victory = true;
		char[] puzzle = new char[letters.length];
		int counter = 0;
		int gamesPlayed = 1;
		boolean gameDone = false;
		
		//Create array of what you've guessed so far, called puzzle
		//Initialize as blank (underscores)
		for(int i = 0; i < puzzle.length; i++) {
			puzzle[i] = '_';
		}
		
		//Create extra line of space and print out empty gallows
		System.out.println("");
		System.out.print(g.toString());
		
		//Play game when still alive or when still haven't solved
		while (victory) {
			//Print out prompt after every game/round completed
			if (gameDone) {
				//Increment games played
				gamesPlayed++;
				prompt = "Please enter a secret word: ";
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
				
				//Redefine the puzzle player needs to solve
				puzzle = new char[letters.length];
				for(int i = 0; i < puzzle.length; i++) {
					puzzle[i] = '_';
				}
				//Make new gallows and print it
				g = new Gallows();
				System.out.println(g.toString());
			}
			//Start a new game. Print empty puzzle
			gameDone = false;
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
			//If answer doesn't contain user's letter
			//Hang, and print hangman
			if (containsGuess == false) {
				System.out.println("");
				g.hang();
				System.out.print(g.toString());
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
				    //Prints whether it's player 1 or player 2's turn next
					if (gamesPlayed % 2 == 0) {
						System.out.println("Player 2 got it. Player 1, you turn next!");
					} else {
						System.out.println("Player 1 got it. Player 2, your turn next!");
					}
					gameDone = true;
				}
			}
			//Check if hangman still alive. If not, break out of loop
			if (!g.isAlive()) {
				victory = false;
				break;
			}

		}
		
		//Game done
		if (gamesPlayed % 2 == 1) {
			System.out.println("Player 1 wins in " + gamesPlayed + " games!");
		} else {
			System.out.println("Player 2 wins in " + gamesPlayed + " games!");
		}
		

	}

}