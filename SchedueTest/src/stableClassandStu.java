import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class stableClassandStu {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//these will return both lists of classes and students
		ArrayList<Class> cl = genClass();
		ArrayList<Student> stu = genStu(cl);
		
		
		for(int i=0;i<stu.size();i++) {
			Schedule a = testing.pop(stu.get(i).getPref(), cl);
			System.out.println(stu.get(i).getfName()+" "+stu.get(i).getlName());
			a.printSchedule();
			System.out.println("\n\n\n\n");
		}
		
		
	}
	
	public static ArrayList<Student> genStu(ArrayList<Class> cl) throws FileNotFoundException{
		int MAXSTU = 50;
		int MAXCL = cl.size()-2;
		
		//gets all the paths for the txt files to be used 
		URL suburl = stableClassandStu.class.getResource("subjects.txt");
		URL fnurl = stableClassandStu.class.getResource("fNames.txt");
		URL lsurl = stableClassandStu.class.getResource("lNames.txt");
		
		//for the subjects for classes
    	Scanner file = new Scanner(new File(suburl.getPath())).useDelimiter("\n");
    	String[] subjects =  take(file);
    	
    	//for the first names of students
    	 file = new Scanner(new File(fnurl.getPath())).useDelimiter("\n");
    	String[] fnames = take(file);
    	
    	//for the last names of students
    	 file = new Scanner(new File(lsurl.getPath())).useDelimiter("\n");
    	String[] lnames = take(file);
    	
		ArrayList<Student> stu = new ArrayList<Student>();
		
		//this makes the prefered list of the students
		int x = 0;
		ArrayList<String> pref = new ArrayList<String>();
		for(int i =0; i < MAXSTU;) {
			pref.add(cl.get(x).getName());
			x++;
			//x should be based on the amount of classes and will reset once the amount of classes has been reached
			if(x >= MAXCL) {
				x=0;
			}
			//the student objects will not be added to the arraylist until the prefered list is full
			if(pref.size() == 7) {
			pref.add("Lunch");
			stu.add(new Student(fnames[i], lnames[i], new Schedule(), pref ));
			pref = new ArrayList<String>();
			i++;
			}
		}
		//this just prints it out
		for(int i = 0; i < MAXSTU; i++) {
		//System.out.println(stu.get(i).getfName()+ " " + stu.get(i).getlName().replaceAll("\n", "")+ "\n" + " " +stu.get(i).listPrint()+"\n");
		}
		return stu;
	}
	
	
	public static ArrayList<Class> genClass() throws FileNotFoundException{
		int MAXCLASS = 25;
		
		//gets the path of the txt in the bin
		URL url = stableClassandStu.class.getResource("subjects.txt");
    	Scanner file = new Scanner(new File(url.getPath())).useDelimiter("\n");
    	
    	String[] subjects =  take(file);
    	//lunches automatically set to cap at 1000
    	Class gLunch = new  Class(1000, "Lunch", "G");
    	Class hLunch = new  Class(1000, "Lunch", "H");
    	
    	//setting up the array list of classes
		ArrayList<Class> cl = new ArrayList<Class>();
		String letter = "";
		int num;
		int x=0;
			
		for(int i = 0; i < MAXCLASS; i++) {
			
			//blocks are generated formatted but also are not very predictable
			if(i % 2 == 0) {
				if(i % 3 == 0) {
					letter = "A";
				}
				else if((i >= 7) && (i <10) || i > 17) {
					letter = "B";
				}
				else if(i <= 6) {
					letter = "C";
				}
				else {
					letter = "D";
				}
			}
			else {
				if(i % 3 == 0) {
					letter = "E";
				}
				else if((i > 10) && (i <15) || i >= 17) {
					letter = "F";
				}
				else if(i < 7) {
					letter = "G";
				}
				else {
					letter = "H";
				}
			}
			
			if(i%2==0) {
				num = 10;
			}
			else {
				num = 5;
			}
			
			cl.add(new Class(num,"" + subjects[i] + " ", letter));
		}
		cl.add(gLunch);
		cl.add(hLunch);
		
		return cl;
	}


	public static  String[] take(Scanner inFile1){
		
		String token1 = "\n";
	    ArrayList<String> temps = new ArrayList<String>();

	    // while loop
	    while (inFile1.hasNext()) {
	      // find next line
	      token1 = inFile1.next();
	      temps.add(token1);
	    }
	    inFile1.close();

	    String[] tempsArray = temps.toArray(new String[0]);


	    return tempsArray;
	}

}
