import java.util.ArrayList;
import java.util.Scanner;

public class TextLife {
    /* Checks to see if line contains only periods and plusses
    * Also checks to make sure the length of line is cols
    * Returns the length of the line, or exits the program
    * if input is invalid.
    */
    public static int validateLine(char[] line, int cols) {
      if (cols!=-1&&line.length != cols) {
        System.out.println("Unequal number of lines and columns.");
        System.exit(1);
      }
      for (char c: line) {
        if ((c!='.') && (c!='+')) {
          System.out.println("Encountered invalid symbol.");
          System.exit(1);
        }
      }
      return line.length;
    }
    /* Given an array list of character arrays, prints the
    * board to the console
    */
    public static void printBoard(ArrayList<char[] > board) {
      for(char[] c : board) System.out.println(new String(c));
    }
    /* Given a board and a row and column (not necessarily on
    * the board), returns true if the cell is on, false otherwise.
    */
    public static boolean isAlive(ArrayList<char[]> board,
                                  int row,
                                  int col) {
          return (col >= 0
          && row >= 0
          && row < board.size()
          && col < board.get(0).length
          && board.get(row)[col]== '+');
    }
    /* Given a board and a position on it, returns '+'
    * if the cell should be turned on in the next iteration
    * of the game, and '.' otherwise.  Rules are as follows:
    * 1. Every live cell with 2 or 3 neighbors survives
    * 2. Every live cell with 4+ neighbors dies of overpopulation
    * 3. Every live cell with 0 or 1 neighbor dies of isolation
    * 4. Each empty cell next to exactly 3 neighbors turns alive
    */
    public static char getStatus(ArrayList<char[]> board,
                                int row,
                                int col) {
        int n = 0;
        for(int l = -1; l < 2; l++) {
          for(int m = -1; m < 2; m++) {
            if ((l != 0||m != 0) && isAlive(board, row+l, col+m)) {
                n++;
            }
          }
        }
        if(n >= 4 || n <= 1){
          return '.';
        } else if (n == 3){
          return '+';
        } else return board.get(row)[col];
    }
    /* Given a board, generate a new board that represents the
    * next iteration of the game
    */
    public static ArrayList<char[]> getNextIteration(ArrayList<char[]> board) {
          ArrayList<char[]> nextGen = new ArrayList<char[]>(board.size());
          for(int r = 0; r < board.size(); r++){
            char[] current = board.get(r);
            char[] blank = new char[current.length];
            for(int c = 0; c < current.length; c++){
              blank[c] = getStatus(board, r, c);
            }
            nextGen.add(blank);
          }
          return nextGen;
    }
    /* The main method should prompt for a board and a number
    * of iterations to play.  It should then play the specified
    * number of iterations, printing the outcome on the console.
    */
    public static void main(String[] args) {
      Scanner s = new Scanner(System.in);
      System.out.println("Enter the number of iterations to play: ");
      int iterations = s.nextInt();
      s.nextLine();
      System.out.println("Enter the initial board configuration.");
      System.out.println("Use periods for dead cells and plus signs for live ones.");
      System.out.println("Do not include spaces and use Control+D when done:");
      ArrayList<char[]> board = new ArrayList<char[]>();
      int lineSize = -1;
      //fill board
      while(s.hasNextLine()){
        char[] line = s.nextLine().toCharArray();
        if(board.size()==0){
          lineSize = line.length;
          board.add(line);
        }
        else{
          validateLine(line, lineSize);
          board.add(line);
        }
      }
      //simulate iterations
      for(int i=0;i<iterations;i++){
        System.out.println("After " + (i+1) + " iterations:");
        board = getNextIteration(board);
        printBoard(board);
      }
    }
}