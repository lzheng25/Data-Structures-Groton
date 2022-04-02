import java.util.Comparator;

public class SortByName implements Comparator<Student> {
	@Override
	public int compare(Student a, Student b) {
		// TODO Auto-generated method stub
		//return value when names are compared
		return a.getName().compareTo(b.getName());
	}
}
