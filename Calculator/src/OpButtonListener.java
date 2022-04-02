/*
	OpButtonListener.java:  Operation button class - 1/97 Kim B. Bruce
	Fill in the missing code!!!
*/

import java.awt.event.*;

/**
	Class representing buttons with operators on them.
**/

public class OpButtonListener implements ActionListener {
	
	  protected char op;
	  protected State st;
	  
	  
	  public OpButtonListener(String op, State st) {
		this.op = op.charAt(0);
	    this.st = st;
	    
	  }
	  
	  public void actionPerformed(ActionEvent evt) {
	    this.st.doOp(op);
	  }
}