import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
 * Thought Questions
 * 
 * 1. The code sorts v in reverse order. If we look inside RevComparator, we see that it returns the negative of base.compare, which sorts
 * in ascending order, meaning that RevComparator will sort in reverse order. Also, the name RevComparator suggests that it sorts in a 
 * reverse order.
 * 
 * 2. You need to create protected data types to sort the Vector in a specific way. Instantiate the 
 * field(s) in the RevComparator constructor. Then, you need to write a method or methods based on the number of
 * fields you have that modify the field according to how you want to sort. This modifies the 
 * return statement of the compare method.
 * 
 */


public class Sort {
	
	private static MyVector<Student> students;
	
	public static void main(String[] args) {
		
		//Create MyVector of Strings
		students = new MyVector<Student>();
		Scanner s = new Scanner(System.in);
		if (args.length>0) {
			File data = new File(args[0]);
			try {
				//If file found, put the lines in the file into MyVector
				s = new Scanner(data);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//If cannot find file, print error message
				System.out.println("No such file.");
				System.exit(1);
			}
		}
		//As long as there are lines left, create Student objects and add them to students
		//For some reason first item is buggy, so skip over the first line
		int counter = 0;
		while(s.hasNextLine()) {
			if(counter == 0) {
				s.nextLine();
			} else {
				//Split each line into array, each element separated by comma
				String[] elements = s.nextLine().split(",");
				//Create Student objects with array
				//first element is name; second age; third height; fourth sport
				students.add(new Student(elements[0], Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), elements[3]));
			}
			counter++;
		}
		
		//Start sorting in different ways
		System.out.println("Sort by name:");
		sort(new SortByName());
		PrintStream ps = new PrintStream(System.out);
		//Print out column labels with formatting
		//%10s means 10 spaces
		ps.printf("%10s%10s%10s%15s", "Name", "Age", "Height", "Sport\n");
		ps.printf("%10s%10s%10s%15s", "=====", "====", "=======", "======\n");
		//Format print from Student class
		for(Student i : students) i.print();
		System.out.println();
		
		System.out.println("Sort by age:");
		sort(new SortByAge());
		//Print out column labels with formatting
		ps.printf("%10s%10s%10s%15s", "Name", "Age", "Height", "Sport\n");
		ps.printf("%10s%10s%10s%15s", "=====", "====", "=======", "======\n");
		for(Student i : students) i.print();
		System.out.println();
		
		System.out.println("Sort by height:");
		sort(new SortByHeight());
		//Print out column labels with formatting
		ps.printf("%10s%10s%10s%15s", "Name", "Age", "Height", "Sport\n");
		ps.printf("%10s%10s%10s%15s", "=====", "====", "=======", "======\n");
		for(Student i : students) i.print();
		System.out.println();
		
		System.out.println("Sort by sport:");
		sort(new SortBySport());
		//Print out column labels with formatting
		ps.printf("%10s%10s%10s%15s", "Name", "Age", "Height", "Sport\n");
		ps.printf("%10s%10s%10s%15s", "=====", "====", "=======", "======\n");
		for(Student i : students) i.print();		
	}

	public static void sort(Comparator<Student> c) {
		mergeSort(0, students.size()-1, c);
		//selectionSort(c);
	}
	
	//Keep on halving until the array is of length one, then merge
	public static void mergeSort(int start, int end, Comparator<Student> c) {
		if(start < end) {
			int mid = (start+end)/2;
			mergeSort(start, mid, c);
			mergeSort(mid + 1, end, c);
			merge(start, mid, end, c);
		}
		
	}

	public static void merge(int start, int mid, int end, Comparator<Student> c) {
		//Look at the vector in halves
		int start2 = mid + 1;
		
		if(c.compare(students.get(mid), students.get(start2)) <=0) {
			return;
		}
		while(start <= mid && start2 <= end) {
			//If first value  is smaller than start2, increment start
			if(c.compare(students.get(start), students.get(start2)) <= 0) {
				start++;
			} else {
				//Else, move value of start2 to the front, shift rest up one
				Student temp = students.get(start2);
				int ind = start2;
				
				while(ind != start) {
					students.set(ind, students.get(ind-1));
					ind--;
				}
				//Increment and move onto next element
				students.set(start,  temp);
				start++;
				mid++;
				start2++;
			}
		}
	}
	
	//Selection Sort
	public static void selectionSort(Comparator<Student> c) {
		int minIndex;
		//Compare current value with the rest
		for(int i = 0; i < students.size() - 1; i++) {
			minIndex = i;
			//Find the index of the smallest value
			for(int j = i + 1; j < students.size(); j++) {
				if(c.compare(students.get(j), students.get(minIndex)) <= 0) {
					minIndex = j;
				}
			}
			//swap smallest value with current value
			Student temp = students.get(minIndex);
			students.set(minIndex, students.get(i));
			students.set(i, temp);
		}
	}
}
