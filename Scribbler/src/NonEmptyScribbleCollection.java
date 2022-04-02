import objectdraw.*;

public class NonEmptyScribbleCollection implements ScribbleCollectionInterface {

  ScribbleInterface newScribble;
  ScribbleCollectionInterface rest;
 
  
  public NonEmptyScribbleCollection(ScribbleInterface addedScribble,
                                    ScribbleCollectionInterface theRest) {
	  newScribble = addedScribble;
	  rest = theRest;
  }

  // pre:
  // post: returns the scribble that contains the point;
  //    if none contain it, returns an empty scribble
  public ScribbleInterface scribbleSelected(Location point) {	  
	  // check for newest non-null and non-hidden scribble
	  if(newScribble == null) return null;
	  else if (!newScribble.isHidden() && newScribble.contains(point)) return newScribble;
	  // if not newest scribble, check for the rest -> recursion
	  else return rest.scribbleSelected(point);
  }

  // pre:
  // post: returns the first scribble in the list;
  //   returns null if the list is empty.
  public ScribbleInterface getFirst() {
    return newScribble;   // change if necessary!
  }

  // pre:
  // post: returns the list of scribbles excluding the first.
  //   returns an empty scribble collection if the list is empty.
  public ScribbleCollectionInterface getRest() {
    return rest;   // change if necessary!
  }
  
  public ScribbleInterface erase() {
	  ScribbleInterface temp = null;
	  //Only erase if there is something to erase
	  if(newScribble != null) {
		  // Erase most recent scribble
		  temp = newScribble;
		  newScribble.erase();
		  // Set most recent scribble to first of rest
		  newScribble = rest.getFirst();
		  // Set rest to rest of rest (not including first)
		  rest = rest.getRest();
	  } 
	  return temp;
  }
  
  public ScribbleInterface undo() {
	  ScribbleInterface temp = null;
	//Only undo if there is something to undo
	  if(newScribble != null) {
		  // Undo most recently erased scribble
		  temp = newScribble;
		  newScribble.undo();
		  // Set most recent scribble to first of rest
		  newScribble = rest.getFirst();
		  // Set rest to rest of rest (not including first)
		  rest = rest.getRest();
	  }
	  return temp;
  }
}