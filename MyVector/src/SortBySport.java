import java.util.Comparator;

public class SortBySport implements Comparator<Student> {
	@Override
	public int compare(Student a, Student b) {
		// TODO Auto-generated method stub
		//return value when sports are compared
		return a.getSport().compareTo(b.getSport());
	}
}
