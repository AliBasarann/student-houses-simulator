import java.util.*;

import java.io.*;


public class project1main {
    
    public static void main(String args[]) throws FileNotFoundException{
    	Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		TreeSet<House> availableHouseList = new TreeSet<House>();
		TreeSet<House> unavailableHouseList = new TreeSet<House>();
		TreeSet<Student> studentList = new TreeSet<Student>();
		TreeSet<Student> allocatedStudentList = new TreeSet<Student>();
		TreeSet<Student> graduatedList = new TreeSet<Student>();
		
		while(in.hasNextLine()) {
			String line = in.nextLine();
			line = line.strip();
			line = line.replaceAll("\\s+", " ");
			String space = " ";
			String[] lineList = line.split(space);
			String temp = lineList[0];

			if ( (int) temp.charAt(0) == (int)"h".charAt(0)) {
				House house = new House(Integer.parseInt(lineList[1]), Integer.parseInt(lineList[2]), Double.parseDouble(lineList[3]));
				if (house.duration != 0)
					unavailableHouseList.add(house);
				else{
					availableHouseList.add(house);
				}
			}
			else if ((int) temp.charAt(0) == (int)"s".charAt(0)) {
				Student student = new Student(Integer.parseInt(lineList[1]), lineList[2], Integer.parseInt(lineList[3]), Double.parseDouble(lineList[4]));
				if (student.duration != 0)
					studentList.add(student);
				else{
					graduatedList.add(student);
				}

			}
		}

		
		
		//Iterator<Student> itrGraduated = graduatedList.iterator();
		
		while(studentList.size() + allocatedStudentList.size() != 0) {

			Iterator<House> itrAvailableHouse = availableHouseList.iterator();
			
			/*uygun ev ara bulursan 
			öğrenciyi yerleşenler listesine ekle  yerleşemeyenden çıkar 
			evi available dan unavailable yap
			bulamazsan devam et
			*/
			while(itrAvailableHouse.hasNext()) {
				House house = itrAvailableHouse.next();
				if (house.duration == 0) {
					for(Student student : studentList) {
						if(student.allocateStudent(house)) {
							unavailableHouseList.add(house);
							allocatedStudentList.add(student);
							studentList.remove(student);
							itrAvailableHouse.remove();;
							break;
						}else {continue;}
					}
				}else {
					continue;
				}
			}
			
			
			//yerleşmiş öğrenci için yılı azalt eğer öğrenci 0 oldu ise öğrenciyi grada koy yerleşenlerden çıkar
			Iterator<Student> itrAllocatedStudent = allocatedStudentList.iterator();
			while(itrAllocatedStudent.hasNext()) {
				Student student = itrAllocatedStudent.next();
				student.decreaseDuration();
				if(student.duration == 0){
					graduatedList.add(student);
					itrAllocatedStudent.remove();;
				}
			}
			//yerleşemeyen öğrencilerin yıllarını azalt, eğer öğrenci 0 oldu ise yerleşemeyenden çıkar grada at
			Iterator<Student> itrAvailableStudent = studentList.iterator();
			while(itrAvailableStudent.hasNext()) {
				Student student = itrAvailableStudent.next();
				student.decreaseDuration();
				if(student.duration == 0){
					graduatedList.add(student);
					itrAvailableStudent.remove();
				}
			}
			//unavailable houseların durationlarını 1 azalt eğer duration 0 ise unavailabledan available'a koy
			Iterator<House> itrUnavailableHouse = unavailableHouseList.iterator();
			while(itrUnavailableHouse.hasNext()){
				House house = itrUnavailableHouse.next();
				house.decreaseDuration();
				if(house.duration == 0){
					availableHouseList.add(house);
					itrUnavailableHouse.remove();
				}
			}
			for(House house : availableHouseList){
				house.decreaseDuration();
			}
		}
		
		for (Student grad : graduatedList) {
			if (grad.isAllocated == false){
			out.println(grad.name);
			}
		}
    }
    
}

