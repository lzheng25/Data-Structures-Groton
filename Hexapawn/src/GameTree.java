import structure5.Vector;

public class GameTree {
	
	private HexBoard current;
	private char player;
	protected Vector<GameTree> possibleMoves;
	protected GameTree parent;
	protected static int size = 0;
	
    public final static char WHITE = 'o';
    public final static char BLACK = '*';
    public final static char SPACE = ' ';
    
    public GameTree() {
    	
    	this(new HexBoard(3,3), null, WHITE);
    	
    	
    }
    
    public GameTree(int a, int b) {
    	
    	this(new HexBoard(a,b), null, WHITE);
    	
    }
	
	public GameTree(HexBoard current, GameTree parent, char player) {
		
		this.current = current;
		this.player = player;
		this.parent = parent;
		size++;
	
		Vector<HexMove> moves = current.moves(player);
		possibleMoves = new Vector<GameTree>();
		
		if(current.win(HexBoard.opponent(player))) return;
		//For each possible move, create a GameTree that carries out the move and add to Vector of children
		else {
			for(int i = 0; i < moves.size(); i++) {
				possibleMoves.add(new GameTree(new HexBoard(current,(HexMove)moves.elementAt(i)), this, HexBoard.opponent(player)));
			}	
		}
		
	}
	
	public int nodes() {
		return size;
	}
	
	public HexBoard getBoard() {
		return current;
	}
	
	public Vector<GameTree> getChildren() {
		return possibleMoves;
	}
	
	//Remove the previous move that was made by player from Vector of possible moves
	//Return true if tree actually changed
	//Return false if node was already removed or nothing to remove
	public boolean removeMove() {
		if(parent != null && parent.parent != null) {
			if(parent.parent.getChildren().contains(parent)) {
				parent.parent.getChildren().remove(parent);
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		GameTree mother = new GameTree(3,3);

		System.out.println(mother.nodes());
	}
	
}
