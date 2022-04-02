import java.util.Random;

import structure5.*;

public class CompPlayer implements Player{
	
	protected char player;
	protected int edits;
	
	public CompPlayer(char m) {
		this.player = m;
		this.edits = 0;
	}
	
	public int getEdits() {
		return edits;
	}
	
	public char getPlayer() {
		return player;
	}
	
	public Player play(GameTree node, Player opponent) {
		System.out.println(node.getBoard());

		if (node.getBoard().win(opponent.getPlayer()) || node.getChildren().isEmpty()) {
			System.out.println("");
			//Count number of edits
			if(node.removeMove()) edits++;
		    return opponent;
		} else {
	        
	        // Generate random move
	        Random r = new Random();
	        
	       //Execute random move
	        int random = r.nextInt(node.getChildren().size());
	        System.out.println("Computer "+ node.getBoard().moves(player).get(random));
	        node = node.getChildren().get(random);
		    }
	            
		//System.out.println(node.getBoard());
		return opponent.play(node, this);
	}
}	
