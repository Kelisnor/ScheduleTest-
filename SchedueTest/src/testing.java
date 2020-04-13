import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class testing {
	
	public static Schedule pop(ArrayList<String> preferedClasses,ArrayList<Class> allClasses) {
		//setup
		Schedule a = new Schedule();
		boolean[][] b = new boolean[8][8];
		ArrayList<ArrayList<String>> c = new ArrayList<>();
		ArrayList<ArrayList<String>> d = new ArrayList<>();
		ArrayList<String> blocks = new ArrayList<String>();
		blocks.add("A");blocks.add("B");blocks.add("C");blocks.add("D");blocks.add("E");blocks.add("F");blocks.add("G");blocks.add("H");
		for(int i=0;i<8;i++) {
			ArrayList<String> temp = new ArrayList<String>();
			d.add(temp);
		}
		for(int i=0;i<preferedClasses.size();i++)
			preferedClasses.set(i, preferedClasses.get(i).toLowerCase());
		
		
		
		
		
		
		//get the table
		for(int i=0;i<b.length;i++) {
			
			
			ArrayList<String> allBlocks = new ArrayList<String>();
			
			
			for(int k=0;k<allClasses.size();k++) {
				if(allClasses.get(k).getName().toLowerCase().equals(preferedClasses.get(i))&&allClasses.get(k).canAdd()) {
					allBlocks.add(allClasses.get(k).getBlock());
				}
			}
			
			
			for(int j=0;j<b[i].length;j++) {
				b[i][j]=allBlocks.contains(blocks.get(j));
			}
			
			
			c.add(allBlocks);
			for(int j=0;j<b[i].length;j++) {
				if(b[i][j]) {
					d.get(i).add(preferedClasses.get(j));
				}
			}
			
		}
		
		
		
		
		
		
		//list of total classes&blocks
		ArrayList<Integer> total1 = new ArrayList<Integer>();
		ArrayList<Integer> total2 = new ArrayList<Integer>();
		
		
		for(int i=0;i<b.length;i++) {
			int times = 0;
			for(int j=0;j<b[i].length;j++) 
				if(b[i][j]) 
					times++;
			total2.add(times);
		}
		
		
		for(int i=0;i<b.length;i++) {
			int times = 0;
			for(int j=0;j<b[i].length;j++) 
				if(b[j][i]) 
					times++;
			total1.add(times);
		}
		
		
		
		
		
		
		//populate the class
		boolean containLunch=false;
		boolean isG = false;
		boolean isH = false;
		int leftBlock = 8;
		ArrayList<Class> cl = new ArrayList<Class>();
		for(int i=0;i<8;i++)
			cl.add(null);
		ArrayList<String> blocks2 = new ArrayList<String>();
		for(int i=0;i<blocks.size();i++)
			blocks2.add(blocks.get(i));
          
		
		
		
		
		
		
		for(int i=0;i<leftBlock;i++) {
			int mtotal1 = minI(total1);
			Class temp = null;
			if(blocks2.get(mtotal1).equals("G"))
				isG=true;
			if(blocks2.get(mtotal1).equals("H"))
				isH=true;
			
			
			
			
			//the block contains more than one classes
			if(total1.get(mtotal1)>1) {
				//havn't finished
				ArrayList<String> tempClasses = new ArrayList<String>();
				ArrayList<Integer> tempblocks = new ArrayList<Integer>();
				for(int j=0;j<c.size();j++) {
					if(c.get(j).contains(blocks2.get(mtotal1))) {
						tempClasses.add(preferedClasses.get(j));
						tempblocks.add(total2.get(j));
					}
				}
				int tempindex = minI(tempblocks);
				 for(int j=0;j<allClasses.size();j++) {
					if(allClasses.get(j).getName().toLowerCase().equals(tempClasses.get(tempindex).toLowerCase())&&allClasses.get(j).getBlock().toUpperCase().equals(blocks2.get(mtotal1))&&allClasses.get(j).canAdd()) {
						temp=allClasses.get(j);
						break;
					}
				}
			}


			//the block only contains one class
			else if(total1.get(mtotal1)==1) {
				String tempClass = "";
				for(int j=0;j<c.size();j++) {
					if(c.get(j).contains(blocks2.get(mtotal1))) {
						tempClass = preferedClasses.get(j);
						break;
					}
				}
				
				for(int j=0;j<allClasses.size();j++) {
					if(allClasses.get(j).getName().toLowerCase().equals(tempClass.toLowerCase())&&allClasses.get(j).getBlock().toUpperCase().equals(blocks2.get(mtotal1))&&allClasses.get(j).canAdd()) {
						temp=allClasses.get(j);
						break;
					}
				}
			}
			
			
			
			//the block with no class
			else {
				temp=null;
			}
			
			
			
			
			
			
			
			
			
			
			//delete and re-setup			
			if(temp==null) {
				//warning: an empty block is created
				cl.set(blocks.indexOf(blocks2.get(mtotal1)),temp);
				for(int k=0;k<c.size();k++) {
					if(c.get(k).contains(blocks2.get(mtotal1))) {
						total2.set(k, total2.get(k)-1);
						c.get(k).remove(blocks2.get(mtotal1));
					}
				}
				total1.remove(mtotal1);
				blocks2.remove(mtotal1);
				d.remove(mtotal1);
				//System.out.print("?");
			}
			else {
				String[] classAndBlock = {temp.getName().toLowerCase(),temp.getBlock()};
				if(temp.getName().toLowerCase()=="lunch")
					containLunch = true;
				
				
				cl.set(blocks.indexOf(classAndBlock[1]), temp);
				
				for(int k=0;k<c.size();k++) {
					if(c.get(k).contains(classAndBlock[1])) {
						total2.set(k, total2.get(k)-1);
						c.get(k).remove(temp.getBlock());
					}
				}
				for(int k=0;k<d.size();k++) {
					if(d.get(k).contains(classAndBlock[0])) {
						total1.set(k, total2.get(k)-1);
						d.get(k).remove(classAndBlock[0]);
					}
				}
				
				int index = preferedClasses.indexOf(classAndBlock[0]);
				total2.remove(index);
				preferedClasses.remove(index);
				c.remove(index);
				total1.remove(mtotal1);
				blocks2.remove(mtotal1);
				d.remove(mtotal1);
				
				
			}
			
			//auto lunch
				if((!isH)&&(!(containLunch))) {
					for(int j=0;j<allClasses.size();j++) 
						if(allClasses.get(j).getBlock().toUpperCase().equals("G")&&allClasses.get(j).getName().toLowerCase().equals("lunch")) {
							cl.set(7, allClasses.get(j));
							break;
						}
					for(int k=0;k<c.size();k++) {
						if(c.get(k).contains("H")) {
							total2.set(k, total2.get(k)-1);
							c.get(k).remove("H");
						}
					}
					for(int k=0;k<d.size();k++) {
						if(d.get(k).contains("lunch")) {
							total1.set(k, total2.get(k)-1);
							d.get(k).remove("lunch");
						}
					}
					int index2 = blocks2.indexOf("H");
					total1.remove(index2);
					blocks2.remove(index2);
					d.remove(index2);
					i++;
					containLunch=true;
					isG=false;
				}
				
				else if((!isG)&&(!(containLunch))){
					for(int j=0;j<allClasses.size();j++) 
						if(allClasses.get(j).getBlock().toUpperCase().equals("G")&&allClasses.get(j).getName().toLowerCase().equals("lunch")) {
							cl.set(6, allClasses.get(j));
							break;
						}
					for(int k=0;k<c.size();k++) {
						if(c.get(k).contains("G")) {
							total2.set(k, total2.get(k)-1);
							c.get(k).remove("G");
						}
					}
					for(int k=0;k<d.size();k++) {
						if(d.get(k).contains("lunch")) {
							total1.set(k, total2.get(k)-1);
							d.get(k).remove("lunch");
						}
					}

					int index2 = blocks2.indexOf("G");
					total1.remove(index2);
					blocks2.remove(index2);
					d.remove(index2);
					i++;
					containLunch=true;
					isH=false;
				}
			
			
			
			
			
		}
		
		
		
		
		
		
		a.setA(cl.get(0));
		a.setB(cl.get(1));
		a.setC(cl.get(2));
		a.setD(cl.get(3));
		a.setE(cl.get(4));
		a.setF(cl.get(5));
		a.setG(cl.get(6));
		a.setH(cl.get(7));
		return a;
	}
	
	
	
	
	
	
	
	public static int minI(ArrayList<Integer> a) {
		int mini = 0;
		for(int i=0;i<a.size();i++)
			if(a.get(i)<a.get(mini))
				mini = i;
		return mini;
	}
}






