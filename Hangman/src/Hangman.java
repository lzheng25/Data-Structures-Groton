import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
 * Thought Questions:
 * 1. onMouseEnter() is invoked when the mouse enters the canvas. It's purpose is for when
 * you only want to show something when the user's mouse is in the canvas. For example if
 * Java program is playing an animation or a video, you can use onMouseEnter() to display
 * a pause/play button when the mouse enters the canvas.
 *    onMouseExit() is invoked when the mouse exits the canvas. It's purpose is for when
 * you only want to hide something when the user's mouse is outside the canvas. For example 
 * if Java program is playing an animation or a video like in the previous example, you can
 * use onMouseExit() to hide the pause/play button when the mouse exits the canvas.
 *    onMouseMove() is invoked when the mouse button has been moved on the canvas. It's
 * purpose is for when you want to show/hide something when the mouse is moving. For example
 * if you are playing a video on YouTube, you may have noticed that the play/pause tab goes
 * away after a few seconds. So you can use onMouseMove() to detect that the mouse moved on
 * canvas and redisplay the play/pause tab. If you are designing a space invaders game, you
 * might want to move the ship wherever the mouse is moved to on the canvas, so you can use
 * onMouseMove() here too.
 * 
 * 2. Rect is the public parent/super class for FramedRect. Rect serves as the base 
 * class (provides basic methods) for all the classes related to rectangles like FramedRect 
 * and FilledRect. Rect might have basic methods for getting/setting the height and width for a
 * rectangle. As mentioned earlier, FramedRect is a type of rectangle so it makes sense that 
 * it inherits all the methods inside Rect. FramedRect can also add its own methods like
 * setting the color or move the FramedRect's position.
 * 
 * 3. removeFromCanvas() permanently removes an object from the canvas whereas hide()
 * temporarily hides an object from the canvas. You would use removeFromCanvas() if you
 * only want to display an object on the canvas once and never use it again after. You have
 * to create new object if want to display it again. You can use hide() and show() to make 
 * the object disappear/appear without removing the object completely. 
*/
import objectdraw.*;
public class Hangman extends WindowController  implements KeyListener{

	protected String word = "";
	protected char[] letters;
	protected char[] puzzleLetters;
	protected boolean setup;
	protected int lettersRemaining;
	protected int playerNum = 0;
	//Used to print underscores in puzzle
	private String puzzleText = "";
	boolean flag = false;
	
	// GUI Elements
	protected Text label;
	protected Text buttonText;
	protected FramedRect button;
	protected Text puzzle;
	protected Gallows gallows;
	
	protected static final int WINDOW_SIZE = 600;
	protected static final int TEXT_OFFSET = 10;
	protected static final int PUZZLE_OFFSET = 120;
	protected static final int BUTTON_WIDTH = 200;
	protected static final int BUTTON_HEIGHT = 40;

	
    public void begin()
    {
            // Get ready to handle key focuses
            requestFocus();
            addKeyListener(this);
            canvas.addKeyListener(this);
            
            // Set up the GUI for Player to enter the target word.
            label = new Text("Player " + getPlayerNum() + ", please enter a word.", TEXT_OFFSET, TEXT_OFFSET, canvas);
            label.setFontSize(20);
            
            setup = true;
            
            button = new FramedRect(
            		WINDOW_SIZE/2 - BUTTON_WIDTH/2,
            		WINDOW_SIZE/2 - BUTTON_HEIGHT,
            		BUTTON_WIDTH,
            		BUTTON_HEIGHT,
            		canvas);
            button.setColor(Color.RED);
            button.hide();
            
            buttonText = new Text("Click when finished.", 
            		button.getX() + BUTTON_WIDTH/2, 
            		button.getY() + BUTTON_HEIGHT/2, 
            		canvas);
            buttonText.move(buttonText.getWidth()/-2.0, buttonText.getHeight()/-2.0);
            buttonText.hide();
            
            puzzle = new Text("Puzzle to Solve: ", WINDOW_SIZE/2, WINDOW_SIZE - PUZZLE_OFFSET, canvas);
            puzzle.setFontSize(30);
    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());

    }
 
    
    // Required by KeyListener Interface.
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e)
    {
    	if(setup) {
    		if(word.isEmpty()) puzzle.setText("Puzzle to Solve: ");
    		char letter = Character.toUpperCase(e.getKeyChar());
    		if (Character.isLetter(letter)) {
	    		/* TODO: Update the puzzle text with the letter
	    		 * that was just entered.
	    		 */
    			//Reset word and add letter to word
    			word += String.valueOf(letter);
    			//For each letters pressed, add underscore and space to puzzle
    			puzzleText = "";
    			for (int i = 0; i < word.length(); i++) {
    				puzzleText += "_ ";
    			}
    			puzzle.setText("Puzzle to Solve: " + puzzleText);
    			//Display new puzzle containing new underscores and spaces
    			//Reposition
	    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
	    		
	    		if(word.length() == 1) {
	    			button.show();
	    			buttonText.show();
	    		}
    		} else if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE && ! word.isEmpty()) {
    			/* TODO: Add logic to process the delete key having 
    			 * been pressed, adjusting the position of the puzzle
    			 * text accordingly.  Hide the "Click when finished" button 
    			 * if the word has been deleted entirely. 
    			 */
    			word = word.substring(0, word.length() - 1);
    			//Redefine underscore with new word length
    			puzzleText = "";
    			for (int i = 0; i < word.length(); i++) {
    				puzzleText += "_ ";
    			}
    			//Change text
    			puzzle.setText("Puzzle to Solve: " + puzzleText);
    			//Reposition
	    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
    			
    			//Hide "Click when finished" button if word is empty
    			if (word.isEmpty()) {
    				button.hide();
    				buttonText.hide();
    			}
    		}
    	} else if (gallows.isAlive() ){ 
    		char guessedLetter = Character.toUpperCase(e.getKeyChar());
    		/* TODO: Add logic to check if the letter
    		 * is in the word. Update the guess word
    		 * if the letter is found, otherwise hang
    		 * the man.
    		 */
    		//If letters contains guessLetter, then replace correct letters in puzzleLetters
    		for (int i = 0; i < letters.length; i++) {
    			if (letters[i] == guessedLetter) {
    				//Set flag to true
    				flag = true;
    				//Update guess word
    				updateGuessWord(guessedLetter);
    				//Break out of for loop since guess word updated
    				break;
    			} else {
        			//so far word doesn't contain guessedLetter, flag stays false.
        			flag = false;
    			} 
    		}
    		if (!flag) {
    			gallows.hang();
    		}
    		if (gallows.isAlive()) {
    			//Set puzzleText to what puzzleLetters should print
    			puzzleText = "";
    			for (int i = 0; i < puzzleLetters.length; i++) {
    				puzzleText += puzzleLetters[i];
    				puzzleText += " ";
    			}
    			//Display new puzzle containing correctly guessed letters (word), if any
    			puzzle.setText("Puzzle to Solve: " + puzzleText);
    		} else {
    			//The other player loses, so increment PlayerNum
    	   		playerNum ++;
    			label.setText("Game over! Player " + getPlayerNum() + " wins.");
    			//Set puzzleText to the answer
    			puzzleText = "";
    			for (int i = 0; i < letters.length; i++) {
    				puzzleText += Character.toLowerCase(letters[i]);
    			}
    			//Display new puzzle containing correctly guessed letters (word), if any
    			puzzle.setText("The word was: " + puzzleText);
    		}
			
    	}
    }
    
    public void onMousePress(Location point) {
    	if (button.contains(point) && !button.isHidden() && ! word.isEmpty()) {
    		/* TODO:  Add logic to exit setup mode and
    		 * start gameplay
    		 */
    		//Hide button and its text. Next player's turn. Exit setup
    		setup = false;
       		playerNum ++;
    		button.hide();
			buttonText.hide();
    		//Rewrite label at the top
    		label.setText("Player " + getPlayerNum() + ", please type a key to guess a "
            + "letter");
            label.setFontSize(20);
            //Create gallows
            requestFocus();
            gallows = new Gallows(WINDOW_SIZE/4.0, WINDOW_SIZE * 2.0/3, canvas);

    		//Initialize letters and puzzleLetters char[]
    		//Set letters to word that user need to guess
    		letters = word.toCharArray();
       		//Set puzzleLetters to underscores
    		puzzleLetters = new char[letters.length];
    		for (int i = 0; i < letters.length; i++) {
    			puzzleLetters[i] = '_';
    		}
    	}
    }
    
    public int getPlayerNum() {
    	//Pre: takes playerNum's value
    	//Post: returns player 1 or 2 depending on playerNum's value
    	if (playerNum % 2 == 0) return 1;
    	return 2;
    }
	
  
    
    public void updateGuessWord(char guessedLetter) {
    	/* TODO:  Add logic to update the guessed word.
    	 * Also include logic to test if the puzzle has
    	 * been solved (allowing the user to enter a new
    	 * word for their opponent if the puzzle is complete). 
    	 */
    	//Pre: Takes in puzzleLetters
    	//Post: Updates puzzleLetters with any correct guesses
    	lettersRemaining = 0;
    	for (int i = 0; i < puzzleLetters.length; i++) {
			if (letters[i] == guessedLetter) {
				puzzleLetters[i] = guessedLetter;
			}
			if (puzzleLetters[i] == '_') {
				lettersRemaining++;
			}
		}
    	
    	//If no letters to be guessed, user has won.
    	if (lettersRemaining == 0) {
    		//Clear gallows
    		gallows.clear();
    		//Rewrite label at the top
    		label.setText("Congratulations! You solved the puzzle. Enter a new word.");
            //Setup true so user can enter new word
            setup = true;
            //Make word empty
            word = "";
    	}
    }
    
    public static void main(String[] args) { 
        new Hangman().startController(WINDOW_SIZE, WINDOW_SIZE); 
	}
	
}