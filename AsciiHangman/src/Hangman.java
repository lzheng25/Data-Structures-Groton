import java.io.Console;
import java.util.Scanner;

/*
Thought Questions:
1. True. Because the division of integers in Java omits the remainder, a/7*7 returns the largest
multiple of 7 less than a. So if you subtract that from a, in a - a/7*7, it is same as the
modulus operator because you subtract the largest multiple of 7 from a.

2. Just subtract 1 to the sum right before modulo 7 to make the remainder 0 be Sunday.

3. The code counts for leap years by adding the integer quotient of the year divided by 4 
to the total sum. If the date falls before March, the leap day for that year hasn't 
arrived yet, so we need to subtract 1 to get rid of the additional day that the integer
quotient counted for.
*/

public class Hangman {
	
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
		
		//Initialize variables and create Gallows object called g
		Gallows g = new Gallows();
		boolean containsGuess = false;
		boolean victory = false;
		char[] puzzle = new char[letters.length];
		int counter = 0;
		
		//Create array of what you've guessed so far, called puzzle
		//Initialize as blank (underscores)
		for(int i = 0; i < puzzle.length; i++) {
			puzzle[i] = '_';
		}
		//Create an extra line of space
		System.out.println("");
		//Print out empty gallows
		System.out.print(g.toString());
		
		//Play game when still alive and when still haven't solved
		//If one of these conditions isn't met, it breaks out of the while loop
		while (g.isAlive() && !victory) {
			System.out.print("Puzzle to solve: ");
			
			//Print out puzzle
			//Pre: takes an array of char
			//Post: Prints out each char with spaces in between
			for (int i = 0; i < puzzle.length; i++) {
				System.out.print(puzzle[i] + " ");
			}
			
			//Skips to next line
			System.out.println("");
			System.out.print("Please guess a letter: ");
			//Sets the first letter of the input as the guess
			//Change to upper case so it is case insensitive
			char guess = s.nextLine().toUpperCase().charAt(0);
			
			//Show guess
			System.out.println(guess);
			
			//Check to see if guess matches one of the letters
			//Pre: takes in an array of char
			//Post: if match is found, updates puzzle, revealing correct letters
			//Post: updates containsGuess boolean according to whether the letter guessed is a match
			for (int i = 0; i < letters.length; i++) {
				if (letters[i] == guess) {
					//A match is found
					containsGuess = true;
					
					//Pre: takes the array of char
					/* Post: Replace the '_' in puzzle that corresponds 
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
			//Thus, victory. Breaks out of while loop
			//If not all replaced, no victory yet. Continues to next loop iteration
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
		
		//Game done. Print statements according to victory status
		if (victory) {
			System.out.println("Success!  Player 2 wins!");
		} else {
			System.out.println("Game over!  Player 1 wins!");
		}
		

	}

}