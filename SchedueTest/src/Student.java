import java.util.ArrayList;
import java.util.List;

public class Student {
	private String fName;
	private String lName;
	private Schedule sc;
	private ArrayList <String> pref;
	
	public Student(String f,String l,Schedule s, ArrayList <String> p) {
		fName =f;
		lName =l;
		sc = s;
		pref = p;
	}
	
	public String toString() {
		return "First: " +fName + " Last: " + lName + "\n\t\t\n" + sc.toString();
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public Schedule getSc() {
		return sc;
	}

	public void setSc(Schedule sc) {
		this.sc = sc;
	}
	
	
	
	
	
	public ArrayList<String> getPref() {
		return pref;
	}

	public void setPref(ArrayList<String> pref) {
		this.pref = pref;
	}

	public String listPrint() {
		String str="";
		for(int i =0; i < pref.size(); i++) {
			str =str + pref.get(i); 
		}
		
		return str;
	}
}
