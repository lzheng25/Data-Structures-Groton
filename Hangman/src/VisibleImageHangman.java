import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Image;

import objectdraw.*;
public class VisibleImageHangman extends WindowController  implements KeyListener{

	protected String word = "";
	protected char[] letters;
	protected char[] puzzleLetters;
	protected boolean setup;
	protected int lettersRemaining;
	protected int playerNum = 0;
	//Used to print underscores in puzzle
	private String puzzleText = "";
	boolean flag = false;
	protected VisibleImage head;
	protected VisibleImage body;
	protected VisibleImage leftArm;
	protected VisibleImage rightArm;
	protected VisibleImage leftLeg;
	protected VisibleImage rightLeg;
	protected VisibleImage win;
	protected VisibleImage lose;
	
	// GUI Elements
	protected Text label;
	protected Text buttonText;
	protected FramedRect button;
	protected Text puzzle;
	protected VisibleImageGallows visibleImageGallows;
	
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
    		
    		//Redefine bodyparts[] with visible image. Pass into Gallows. Reposition in Man
    		//Hide them
    		head = new VisibleImage(getImage("head.png"), 320, 50, 80, 80, canvas);
    		body = new VisibleImage(getImage("body.png"), 320, 130, 80, 80, canvas);
    		leftArm = new VisibleImage(getImage("leftArm.png"), 0, 0, 50, 50, canvas);
    		rightArm = new VisibleImage(getImage("rightArm.png"), 0, 0, 50, 50, canvas);
    		leftLeg = new VisibleImage(getImage("leftLeg.png"), 0, 0, 30, 30, canvas);
    		rightLeg = new VisibleImage(getImage("rightLeg.png"), 0, 0, 30, 30, canvas);
    		head.hide();
    		body.hide();
    		leftArm.hide();
    		rightArm.hide();
    		leftLeg.hide();
    		rightLeg.hide();
    		
    		//Create winning and losing gifs. Hide them
    		win = new VisibleImage(getImage("celebrate.gif"), 50, 90, 500, 300, canvas);
    		lose = new VisibleImage(getImage("lose.gif"), 100, 50, 200, 120, canvas);
    		win.hide();
    		lose.hide();
    		
    }
 
    
    // Required by KeyListener Interface.
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e)
    {
    	if(setup) {
    		//Hide win and lose gif
    		win.hide();
    		lose.hide();
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
    	} else if (visibleImageGallows.isAlive() ){ 
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
    			visibleImageGallows.hang();
    		}
    		if (visibleImageGallows.isAlive()) {
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
    			label.setText("Game over! Player " + getPlayerNum() + " wins. You made LeBron sad.");
    			//Set puzzleText to the answer
    			puzzleText = "";
    			for (int i = 0; i < letters.length; i++) {
    				puzzleText += Character.toLowerCase(letters[i]);
    			}
    			lose.show();
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
    		//Hide button and its text. Player 2's turn. Exit setup
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
            visibleImageGallows = new VisibleImageGallows(WINDOW_SIZE/4.0, WINDOW_SIZE * 2.0/3, head, body, leftArm, rightArm, leftLeg, rightLeg, canvas);

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
    		visibleImageGallows.clear();
    		//Rewrite label at the top
    		label.setText("Congratulations! You solved the puzzle. Enter a new word.");
    		//Display win gif
    		win.show();
            //Setup true so user can enter new word
    		
            setup = true;
            //Make word empty
            word = "";
    	}
    }
    
    public static void main(String[] args) { 
        new VisibleImageHangman().startController(WINDOW_SIZE, WINDOW_SIZE); 
	}
	
}