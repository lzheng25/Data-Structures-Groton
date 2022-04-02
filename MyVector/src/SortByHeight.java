import java.util.Comparator;

public class SortByHeight implements Comparator<Student> {
	@Override
	public int compare(Student a, Student b) {
		// TODO Auto-generated method stub
		//return difference in height
		return a.getHeight() - b.getHeight();
	}
}