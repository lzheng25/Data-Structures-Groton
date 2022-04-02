
public class TurnRecord {
    
	private Peg from;
    private Peg to;
    
    //Records the pegs that the disk was moved from and to
    public TurnRecord(Peg from, Peg to){
        this.from = from;
        this.to = to;
    }

    public Peg getFrom(){
        return from;
    }

    public Peg getTo(){
        return to;
    }
    
    public boolean equals(TurnRecord other) {
    	return other.from == from && other.to == to;
    }
    
    public String toString() {
    	return from.toString() + " " + to.toString();
    }
}
