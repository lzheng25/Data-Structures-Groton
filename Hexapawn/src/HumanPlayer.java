import java.util.Scanner;
import structure5.*;
import java.util.Iterator;

public class HumanPlayer implements Player{
	
	protected char player;
	
	public HumanPlayer(char m) {
		this.player = m;
	}
	
	public char getPlayer() {
		return player;
	}
	
	public int getEdits() {
		return 0;
	}
	
	public Player play(GameTree node, Player opponent) {
		System.out.println(node.getBoard());
		
		if (node.getBoard().win(opponent.getPlayer()) || node.getChildren().isEmpty()) {
			System.out.println("");
		    return opponent;
		} else {
	        Iterator<HexMove> moveIter = node.getBoard().moves(player).iterator();
	        int moveCounter = 0;
		    while (moveIter.hasNext()) {
		    	System.out.println(moveCounter +". "+ moveIter.next());
		    	moveCounter++;
		    	}
	            
		    Scanner s = new Scanner(System.in);
		    int i = s.nextInt();
		    Assert.condition(i < node.getChildren().size() && i >= 0, "Out of Range");
		    node = node.getChildren().get(i);
		    }
	            
		//System.out.println(node.getBoard());
		return opponent.play(node, this);
	}
}	
