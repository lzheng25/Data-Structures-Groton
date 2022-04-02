import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import objectdraw.*;

/*
 * Thought Questions
 * 
 * 1. If you graph the two curves A and B, the parts where A lies under B is where program A takes less time
 * than program B. The two curves intersect at n = 0.001 and n = 29.718, and between those two points of intersection
 * A is always smaller than B, so A takes less time than B when 0.001 < n < 29.718. But we can ignore = 0.001
 * because it is pretty close to zero, so A takes less time than B as long is n is smaller than about 29.
 * 
 * 2. The time complexity of adding two Strings of length a and b is O(a+b). When combining Strings, you create
 * a new String then add in each of the characters from the two Strings into it. There are a+b total characters
 * to add, thus the time complexity is O(a+b).
 * 
 * 3. public int binary(int i) {
 * 		if(i == 0) return 0;
 * 	  	else return i % 2 + 10 * binary(i/2);
 *    }
 */

/*
 * A very simple drawing program.
 */
public class Scribbler extends WindowController
implements ActionListener {
  // User modes for what operation is selected.
  // We are using ints rather than boolean to allow for extension to
  // other modes
  private static final int DRAWING = 1;
  private static final int MOVING = 2;
  private static final int COLORING = 3;
  private static final int WINDOW_SIZE = 500;

  // the current scribble
  private ScribbleInterface currentScribble;

  // the collection of scribbles
  private ScribbleCollectionInterface scribbles;
  
  // the collection of erased scribbles
  private ScribbleCollectionInterface erasedScribbles;

  // stores last point for drawing or dragging
  private Location lastPoint;

  // the scribble that is selected
  private ScribbleInterface selectedScribble;

  // buttons that allow user to select modes
  private JButton setDraw, setMove, setErase, setColor, setUndo;

  // Choice JButton to select color
  private JComboBox chooseColor;

  // new color for scribble
  private Color newColor;

  // label indicating current mode
  private JLabel modeLabel;

  // the current mode -- drawing mode by default
  private int chosenAction = DRAWING;

  // create and hook up the user interface components
  public void begin() {

    setDraw = new JButton("Draw");
    setMove = new JButton("Move");
    setColor = new JButton("Color");

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(setDraw);
    buttonPanel.add(setMove);
    buttonPanel.add(setColor);

    chooseColor = new JComboBox();
    chooseColor.addItem("red");
    chooseColor.addItem("blue");
    chooseColor.addItem("green");
    chooseColor.addItem("yellow");

    setErase = new JButton("Erase last");
    JPanel choicePanel = new JPanel();
    choicePanel.add(setErase);
    // undo button
    setUndo = new JButton("Undo erase");
    choicePanel.add(setUndo);
    choicePanel.add(chooseColor);

    
    JPanel controlPanel = new JPanel(new GridLayout(3,1));
    modeLabel = new JLabel("Current mode: drawing");
    controlPanel.add(modeLabel);
    controlPanel.add(buttonPanel);
    controlPanel.add(choicePanel);

    Container contentPane = this.getContentPane();
    contentPane.add(controlPanel, BorderLayout.SOUTH);

    // add listeners
    setDraw.addActionListener(this);
    setMove.addActionListener(this);
    setErase.addActionListener(this);
    setColor.addActionListener(this);
    setUndo.addActionListener(this);

    // make the current scribble empty
    currentScribble = new EmptyScribble();
    // makes scribbles empty
    scribbles = new EmptyScribbleCollection();
    // makes erasedScribbles empty
    erasedScribbles = new EmptyScribbleCollection();
    
    this.validate();
  }

  /*
   * If in drawing mode then start with empty scribble.
   * If in moving mode then prepare to move.
   */
  public void onMousePress(Location point) {
    if (chosenAction == DRAWING) {
      // start with an empty scribble for drawing
      currentScribble = new EmptyScribble();

    } else if (chosenAction == MOVING) {
      // check if user clicked on a scribble
      selectedScribble = scribbles.scribbleSelected(point);
      
    } else if (chosenAction == COLORING) {
    	selectedScribble = scribbles.scribbleSelected(point);
      // check if there is a scribble selected
      if(selectedScribble != null) {
    	  // change color of selected scribble
    	  selectedScribble.setColor((String) chooseColor.getSelectedItem());
      }
    }

    // remember point of press for drawing or moving
    lastPoint = point;
  }

  /*
   * If in drawing mode, add a new segment to scribble.
   * If in moving mode then move it.
   */
  public void onMouseDrag(Location point) {
    if (chosenAction == DRAWING) {
      // add new line segment to current scribble
      Line newSegment = new Line(lastPoint, point, canvas);

      currentScribble =
      new NonEmptyScribble(newSegment, currentScribble);
    } else if (chosenAction == MOVING) {
      // move if there is a scribble selected
      if (selectedScribble != null) {
        // move selected scribble
        selectedScribble.move(point.getX() - lastPoint.getX(),
          point.getY() - lastPoint.getY());
      }
    }
    // update for next move or draw
    lastPoint = point;
  }

  public void onMouseRelease(Location point) {
    // Add code when have collection of scribbles
	  // Update scribbles collection, only if in drawing mode
	  // we don't want all clicks to be added
	  if(chosenAction == DRAWING) {
		  scribbles = new NonEmptyScribbleCollection(currentScribble, scribbles);
	  }
  }

  /*
   * Set mode according to JButton pressed.
   */
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == setDraw) {
      chosenAction = DRAWING;
      modeLabel.setText("Current mode: drawing");
    } else if (e.getSource() == setMove) {
      chosenAction = MOVING;
      modeLabel.setText("Current mode: moving");
    } else if (e.getSource() == setColor) {
      chosenAction = COLORING;
      modeLabel.setText("Current mode: coloring");
    } else if (e.getSource() == setErase) {
    	// erase and add scribble to erasedScribbles if not null
    	ScribbleInterface s = scribbles.erase();
    	if(s != null) {
            erasedScribbles = new NonEmptyScribbleCollection(s, erasedScribbles);
    	}
    } else if (e.getSource() == setUndo) {
    	// undo and add scribble back to scribbles if not null
    	ScribbleInterface s = erasedScribbles.undo();
    	if(s != null) {
        	scribbles = new NonEmptyScribbleCollection(s, scribbles);
    	}
    }
  }
  
  public static void main(String[] args) { 
      new Scribbler().startController(WINDOW_SIZE, WINDOW_SIZE);
	}
}
