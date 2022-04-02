import java.util.Scanner;

/*
 * Thought Questions
 * 
 * 1. There are 6003 board positions for a 3x4 board. There are 215150 board positions for a 3x5 board.
 * 
 * 2. HER will win a lot more frequently on a 3x3 board since there the second player can always win when it makes 
 * 	  the right moves. After around 10 to 20 losses, HER is basically unstoppable and never loses. With larger boards, 
 *    it just takes longer for the players the learn the best moves (longer pruning process) but at some point either 
 *    one player will win more frequently than the other, or they can't beat each other consistently because both 
 *    have equally optimal moves. Only some boards are solved, like 3x3, where one dominates. For example, in 3x4 and
 *    3x5, HIM ends up winning a lot more. However, for 4x4 and 5x3, they are pretty much tied head to head.
 *    
 * 3. When the board is symmetric, which means when the left and right columns are identical on a 3 by 3 board, 
 *    it is more efficient to store one set of the children's moves because it is half the memory. For this to 
 *    work, the GameTree class needs to contain a boolean instance variable mirror, indicating whether or not it 
 *    is symmetric. With another helper method, called symmetric, if mirror is true, the node's children would 
 *    reference the left tree's children, and their mirror variable would also be true. Thus, the right sub 
 *    tree's reference to the right sub-sub tree would reference the left sub-sub tree, if symmetric. 
 *    Additionally, we need a method to print the mirrored version of a tree when mirror is true 
 *    by iterating through the columns for each row and swapping position i with size-i.
 *    
 *    e.g.
 *    -------						-------
 *       o o						 o o 
 *     o		is symmetric with 	     o
 *     * * *						 * * *
 *    -------						-------
 *    
 * 	  Only need to create GameTree object for one of them. The other would just be a reference to the other with
 * 	  mirror set to true.
 * 
 */

public class HexaPawn {
	
    public final static char WHITE = 'o';
    public final static char BLACK = '*';
    public final static char SPACE = ' ';
	
	public static void main(String args[]) {
		
//		Player hp1 = new HumanPlayer(WHITE);
//		Player hp2 = new HumanPlayer(BLACK);
//		Player rp1 = new RandPlayer(WHITE);
//		Player rp2 = new RandPlayer(BLACK);
//		Player cp1 = new CompPlayer(WHITE);
//		Player cp2 = new CompPlayer(BLACK);
//      System.out.println(hp1.play(new GameTree(), hp2).getPlayer() + " wins!");
		
		
		//Instructions, board size, and user selection
		Scanner s = new Scanner(System.in);
		System.out.println("Construct your HexaBoard:");
		System.out.print("How many rows? ");
		int row = s.nextInt();
		System.out.print("How many columns? ");
		int col = s.nextInt();
		System.out.println();
		
		System.out.println("Available Player Types: Human (H), Random (R), Computer (C)");
		System.out.println("White goes first. Black goes second.");
		System.out.print("Which player is white? H, R, or C? ");
		String p1 = s.next();
		System.out.print("Which player is black? H, R, or C? ");
		String p2 = s.next();
		System.out.println();
		
		System.out.print("Number of games: ");
		int numGames = s.nextInt();
		
		int whiteWins = 0;
		int blackWins = 0;
		Player white = null;
		Player black = null;
		//Create the two players
		switch(p1) {
			case "H":
				white = new HumanPlayer(WHITE);
				break;
			case "R":
				white = new RandPlayer(WHITE);
				break;
			case "C":
				white = new CompPlayer(WHITE);
				break;
		}
		
		switch(p2) {
		case "H":
			black = new HumanPlayer(BLACK);
			break;
		case "R":
			black = new RandPlayer(BLACK);
			break;
		case "C":
			black = new CompPlayer(BLACK);
			break;
	}
		
		GameTree gt = new GameTree(row, col);
		
		//play numGames games and count wins
		for(int i = 0; i < numGames; i++) {
			char m = white.play(gt, black).getPlayer();
			System.out.println(m + " wins!");
			if(m == WHITE) whiteWins++;
			else blackWins++;
			
		}
		
		//Print results
		System.out.println("White wins: " + whiteWins);
		System.out.println("Black wins: " + blackWins);
		System.out.println("White % win: " + (double) whiteWins / (whiteWins + blackWins));
		System.out.println("Black % win: " + (double) blackWins / (whiteWins + blackWins));
		if(white instanceof CompPlayer) {
			System.out.println("White computer made " + white.getEdits() + " edits!");
		}
		if(black instanceof CompPlayer) {
			System.out.println("Black computer made " + black.getEdits() + " edits!");
		}

	}
	
}
