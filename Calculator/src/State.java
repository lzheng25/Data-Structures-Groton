/*
 * ???
 */

import structure.*;
import javax.swing.*;
/**
 * Class representing the internal state of the calculator. It is responsible
 * for keeping track of numbers entered and performing operations when buttons
 * are clicked.  It tells the display what number to show.
 */
public class State {
	
	protected JLabel calcDisplay; // Display on which results are written
	// add other instance variables
	protected int number;
	protected boolean processing;
	protected Stack memory;
	// ...
	
	public State(JLabel display) {
		// add code
		processing = false;
		memory = new StackList();
		calcDisplay = display;
	   
	}

	/**
	 * User clicked on a digit button ...
	 */
	public void addDigit(int value) {
		// add your code
		if (!processing) {
		    number= value;
		    processing = true;
		} else {
		    number= 10 * number+ value;
		} 
		calcDisplay.setText("" + number);
	}

	/**
	 *User has clicked on operator button ...
	 */
	public void doOp(char op) {
		// add your code
		// check for errors
		boolean error = false;
		//
	    if (processing)
	      enter();
	    if (memory.size() >= 2) {
	      Integer second = (Integer) memory.pop();
	      Integer first = (Integer)memory.pop();
	      int result = 0;
	      switch(op) {
	      	case '+':
	      		result = first + second;
	      		break;
	      	case '-':
	      		result = first - second;
	      		break;
	      	case '*':
	      		result = first * second;
	      		break;
	      	case '/':
	      		//can't divide by 0
	      		if (second != 0) result = first / second;
	      		else error = true;
	      }
	      
	      memory.push(result);
	      calcDisplay.setText(Integer.toString(result));
	      number= result;
	    
	    } else error = true;
	    
	    //if error, reset
	    if(error) {
	      calcDisplay.setText("Error");
	      memory.clear();
	      processing = false;
	    } 
	}

	/**
	 * User clicked on enter button ...
	 */
	public void enter() {
		// add your code
		memory.push(number);
	    processing = false;
	}

	/**
	 * User clicked on clear key ...
	 */
	public void clear() {
		// add your code
		processing = false;
	    calcDisplay.setText("0");
		memory.clear();
	    
	}

	/**
	 * User clicked on pop key ...
	 */
	public void pop() {
		// add your code
		String display;
	    if(!processing && !memory.isEmpty()) memory.pop();
	    
	    if(memory.isEmpty()) {
	      display = "0";
	      number= 0;
	    } else {
	      display = memory.peek().toString();
	      number = (Integer)memory.peek();
	    }
	    processing = false;
	    calcDisplay.setText(display);
	    
	}

}
