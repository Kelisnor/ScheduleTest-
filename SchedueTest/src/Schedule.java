import java.util.ArrayList;

public class Schedule {
	Class a;
	Class b;
	Class c;
	Class d;
	Class e;
	Class f;
	Class g;
	Class h;
	public Schedule(Class a, Class b, Class c, Class d, Class e, Class f, Class g, Class h) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
	}
	
	public Schedule() {
		a=null;
		b=null;
		c=null;
		d=null;
		e=null;
		f=null;
		g=null;
		h=null;
	}

	public Class getA() {
		return a;
	}

	public void setA(Class a) {
		this.a = a;
	}

	public Class getB() {
		return b;
	}

	public void setB(Class b) {
		this.b = b;
	}

	public Class getC() {
		return c;
	}

	public void setC(Class c) {
		this.c = c;
	}

	public Class getD() {
		return d;
	}

	public void setD(Class d) {
		this.d = d;
	}

	public Class getE() {
		return e;
	}

	public void setE(Class e) {
		this.e = e;
	}

	public Class getF() {
		return f;
	}

	public void setF(Class f) {
		this.f = f;
	}

	public Class getG() {
		return g;
	}

	public void setG(Class g) {
		this.g = g;
	}

	public Class getH() {
		return h;
	}

	public void setH(Class h) {
		this.h = h;
	}
	
	public void printSchedule() {
		ArrayList<String> className = new ArrayList<String>();
		if(a==null)
			className.add("empty");
		else
			className.add(a.getName());
		if(b==null)
			className.add("empty");
		else
			className.add(b.getName());
		if(c==null)
			className.add("empty");
		else
			className.add(c.getName());
		if(d==null)
			className.add("empty");
		else
			className.add(d.getName());
		if(e==null)
			className.add("empty");
		else
			className.add(e.getName());
		if(f==null)
			className.add("empty");
		else
			className.add(f.getName());
		if(g==null)
			className.add("empty");
		else
			className.add(g.getName());
		if(h==null)
			className.add("empty");
		else
			className.add(h.getName());
		
		for(int i=0;i<className.size();i++)
			System.out.println(className.get(i));
		
		
	}
	
}
