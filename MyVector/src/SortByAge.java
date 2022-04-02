import java.util.Comparator;

public class SortByAge implements Comparator<Student> {
	@Override
	public int compare(Student a, Student b) {
		// TODO Auto-generated method stub
		//return difference in age
		return a.getAge() - b.getAge();
	}
}