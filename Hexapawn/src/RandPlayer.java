import java.util.Random;

import structure5.*;

public class RandPlayer implements Player{
	
	protected char player;
	
	public RandPlayer(char m) {
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
	        
	        // Generate random move
	        Random r = new Random();
	        
	        //Execute random move
	        int random = r.nextInt(node.getChildren().size());
	        System.out.println("Random "+ node.getBoard().moves(player).get(random));
	        node = node.getChildren().get(random);
		    }
	            
		//System.out.println(node.getBoard());
		return opponent.play(node, this);
	}

}	
