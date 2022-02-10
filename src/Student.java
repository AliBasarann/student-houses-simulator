
public class Student implements Comparable<Student>{
	
	public int id;
	public String name;
	public int duration;
	public double rating;
	public boolean isAllocated;
	
	public Student(int id, String name, int duration, double rating) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.rating = rating;
		this.isAllocated = false;
	}
	
	public boolean allocateStudent(House house) {
		if(house.rating >= this.rating && house.duration <= 0 && this.isAllocated == false) {
			this.isAllocated = true;
			house.duration = this.duration;
			return true;
		}else {return false;}
	}
	public void decreaseDuration() {
		this.duration -= 1;
	}
	
	public int compareTo(Student student) {
		return (this.id-student.id);
	}
}
