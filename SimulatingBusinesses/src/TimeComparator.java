import java.util.Comparator;

public class TimeComparator implements Comparator<Event> {

	@Override
	//Sort by time
	public int compare(Event e1, Event e2) {
		// TODO Auto-generated method stub
		if(e1.getTime() - e2.getTime() == 0) {
			//Finish events get first priority
			if(e1.getEvent().equals("Finish")) {
				return -2;
			}
			if(e2.getEvent().equals("Finish")) {
				return 2;
			}
			//Process event gets second priority
			if(e1.getEvent().equals("Process")) {
				return -1;
			}
			if(e2.getEvent().equals("Process")) {
				return 1;
			}
		}
		//Customer arrival is lowest priority
		return e1.getTime() - e2.getTime();
	}

}
