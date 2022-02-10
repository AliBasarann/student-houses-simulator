
public class House implements Comparable<House>{

	public int id;
	public int duration;
	public double rating;
	
	public House(int id, int duration, double rating) {
		this.id = id;
		this.duration = duration;
		this.rating  = rating;
	}
	
	public void decreaseDuration() {
		if(this.duration != 0){
		this.duration -= 1;
		}
	}
	public int compareTo(House house) {
		return (this.id-house.id);
	}
}
