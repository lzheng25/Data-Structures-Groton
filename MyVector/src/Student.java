import java.io.PrintStream;

public class Student {
	private String name;
	private int age;
	private int height;
	private String sport;
	
	//Constructor for Student, represents each row of our file
	public Student(String name, int age, int height, String sport) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.sport = sport;
	}
	
	
	//Simple get methods
	public String getName() { 
		return name;
	}
	
	public int getAge() { 
		return age;
	}
	
	public int getHeight() { 
		return height;
	}
	
	public String getSport() { 
		return sport;
	}
	
	//Used for printing students
	public void print() {
		//return "Name: " + name + ", Age: " + age + ", Height: " + height + ", Sport: " + sport;
		//format printing
		//s for springs, d for integers
		System.out.printf("%10s%10d%10d%15s", name, age, height, sport + "\n");
	}
	
}
