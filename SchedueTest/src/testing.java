import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class testing {
	
	public static Schedule pop(ArrayList<String> preferedClasses,ArrayList<Class> allClasses) {
		//setup
		
		//the final schedule that the function returns
		Schedule finalSchedule = new Schedule();
		
		//the big table 
		boolean[][] table = new boolean[8][8];
		
		//all the blocks in each classes e.g. {{A,B,C}} , used to refresh the function
		ArrayList<ArrayList<String>> blocksInClasses = new ArrayList<>();
		
		//all the classes in each blocks e.g. {{math,literature}} , used to refresh the function
		ArrayList<ArrayList<String>> classesInBlocks = new ArrayList<>();
		for(int i=0;i<8;i++) {
			ArrayList<String> temp = new ArrayList<String>();
			classesInBlocks.add(temp);}
		
		//an array list contains all eight blocks
		ArrayList<String> blocks = new ArrayList<String>();
		blocks.add("A");blocks.add("B");blocks.add("C");blocks.add("D");blocks.add("E");blocks.add("F");blocks.add("G");blocks.add("H");
		
		//set all the Strings in preferedClasses to lower case
		for(int i=0;i<preferedClasses.size();i++)
			preferedClasses.set(i, preferedClasses.get(i).toLowerCase());
		
		
		
		
		
		
		//get the table
		for(int i=0;i<table.length;i++) {
			
			//a temperate array list used to complete blocksOfClasses
			ArrayList<String> allBlocks = new ArrayList<String>();
			
			
			for(int k=0;k<allClasses.size();k++) 
				if(allClasses.get(k).getName().toLowerCase().equals(preferedClasses.get(i))&&allClasses.get(k).canAdd()) 
					allBlocks.add(allClasses.get(k).getBlock());
			
			
			for(int j=0;j<table[i].length;j++) {
				table[i][j]=allBlocks.contains(blocks.get(j));
			}
			
			
			blocksInClasses.add(allBlocks);
			for(int j=0;j<table[i].length;j++) {
				if(table[i][j]) {
					classesInBlocks.get(i).add(preferedClasses.get(j));
				}
			}
			
		}
		
		
		
		
		
		
		
		//total blocks number in each classes
		ArrayList<Integer> total1 = new ArrayList<Integer>();
		
		//total class number of each blocks
		ArrayList<Integer> total2 = new ArrayList<Integer>();
		
		//initialize the two array list
		for(int i=0;i<table.length;i++) {
			int times = 0;
			for(int j=0;j<table[i].length;j++) 
				if(table[i][j]) 
					times++;
			total2.add(times);
		}
		for(int i=0;i<table.length;i++) {
			int times = 0;
			for(int j=0;j<table[i].length;j++) 
				if(table[j][i]) 
					times++;
			total1.add(times);
		}
		
		
		
		
		
		
		
		
		boolean containLunch=false;
		boolean isG = false;
		boolean isH = false;
		int leftBlock = 8;
		
		//final list of classes that student need to take
		ArrayList<Class> finalClasses = new ArrayList<Class>();
		for(int i=0;i<8;i++)
			finalClasses.add(null);
		
		//another array list that contains all blocks, used to refresh the function
		ArrayList<String> blocks2 = new ArrayList<String>();
		for(int i=0;i<blocks.size();i++)
			blocks2.add(blocks.get(i));
          
		
		
		
		
		
		//populate the class
		for(int i=0;i<leftBlock;i++) {
			
			
			//find the index of blocks that contains least number classes
			int mtotal1 = minI(total1);
			
			//the class that will later be insert into the final classes list
			Class temp = null;
			
			//judge if the class is G/H block, used to be the condition of the auto lunch part
			if(blocks2.get(mtotal1).equals("G"))
				isG=true;
			if(blocks2.get(mtotal1).equals("H"))
				isH=true;
			
			
			
			
			//firstly, find the block that contains least number of classes
			//secondly, according to the number of classes that blocks have, discuss by situation
			
			
			
			//the block contains more than one classes
			if(total1.get(mtotal1)>1) {
				
				//put all the class names and blocks of that block in two lists
				ArrayList<String> tempClasses = new ArrayList<String>();
				ArrayList<Integer> tempblocks = new ArrayList<Integer>();
				for(int j=0;j<blocksInClasses.size();j++) {
					if(blocksInClasses.get(j).contains(blocks2.get(mtotal1))) {
						tempClasses.add(preferedClasses.get(j));
						tempblocks.add(total2.get(j));
					}
				}
				
				//then find the class with least number of blocks
				int tempindex = minI(tempblocks);
				
				//locate the specific class in the list of allClasses using class name and block and put into the final classes list
				 for(int j=0;j<allClasses.size();j++) {
					if(allClasses.get(j).getName().toLowerCase().equals(tempClasses.get(tempindex).toLowerCase())&&allClasses.get(j).getBlock().toUpperCase().equals(blocks2.get(mtotal1))&&allClasses.get(j).canAdd()) {
						temp=allClasses.get(j);
						break;
					}
				}
			}


			//the block only contains one class
			else if(total1.get(mtotal1)==1) {
				
				//find the name of that class
				String tempClass = "";
				for(int j=0;j<blocksInClasses.size();j++) {
					if(blocksInClasses.get(j).contains(blocks2.get(mtotal1))) {
						tempClass = preferedClasses.get(j);
						break;
					}
				}
				
				//locate the specific class in the list of allClasses using class name and block and put into the final classes list
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
				finalClasses.set(blocks.indexOf(blocks2.get(mtotal1)),temp);
				for(int k=0;k<blocksInClasses.size();k++) {
					if(blocksInClasses.get(k).contains(blocks2.get(mtotal1))) {
						total2.set(k, total2.get(k)-1);
						blocksInClasses.get(k).remove(blocks2.get(mtotal1));
					}
				}
				total1.remove(mtotal1);
				blocks2.remove(mtotal1);
				classesInBlocks.remove(mtotal1);
				//System.out.print("?");
			}
			else {
				String[] classAndBlock = {temp.getName().toLowerCase(),temp.getBlock()};
				if(temp.getName().toLowerCase()=="lunch")
					containLunch = true;
				
				
				finalClasses.set(blocks.indexOf(classAndBlock[1]), temp);
				
				for(int k=0;k<blocksInClasses.size();k++) {
					if(blocksInClasses.get(k).contains(classAndBlock[1])) {
						total2.set(k, total2.get(k)-1);
						blocksInClasses.get(k).remove(temp.getBlock());
					}
				}
				for(int k=0;k<classesInBlocks.size();k++) {
					if(classesInBlocks.get(k).contains(classAndBlock[0])) {
						total1.set(k, total2.get(k)-1);
						classesInBlocks.get(k).remove(classAndBlock[0]);
					}
				}
				
				int index = preferedClasses.indexOf(classAndBlock[0]);
				total2.remove(index);
				preferedClasses.remove(index);
				blocksInClasses.remove(index);
				total1.remove(mtotal1);
				blocks2.remove(mtotal1);
				classesInBlocks.remove(mtotal1);
				
				
			}
			
			
			
			//auto lunch
			
			//lunch for G block
			if((!isH)&&(!(containLunch))) {
				
				//find lunch of G block in the allClasses and put it to the final classes list
				for(int j=0;j<allClasses.size();j++) 
					if(allClasses.get(j).getBlock().toUpperCase().equals("G")&&allClasses.get(j).getName().toLowerCase().equals("lunch")) {
						finalClasses.set(7, allClasses.get(j));
						break;
					}
				
				//refresh and re-setup
				for(int k=0;k<blocksInClasses.size();k++) {
					if(blocksInClasses.get(k).contains("H")) {
						total2.set(k, total2.get(k)-1);
						blocksInClasses.get(k).remove("H");
					}
				}
				for(int k=0;k<classesInBlocks.size();k++) {
					if(classesInBlocks.get(k).contains("lunch")) {
						total1.set(k, total2.get(k)-1);
						classesInBlocks.get(k).remove("lunch");
					}
				}
				int index2 = blocks2.indexOf("H");
				total1.remove(index2);
				blocks2.remove(index2);
				classesInBlocks.remove(index2);
				i++;
				containLunch=true;
				isG=false;
			}
			
			//lunch for H block
			else if((!isG)&&(!(containLunch))){
				
				//find lunch of H block in the allClasses and put it to the final classes list
				for(int j=0;j<allClasses.size();j++) 
					if(allClasses.get(j).getBlock().toUpperCase().equals("H")&&allClasses.get(j).getName().toLowerCase().equals("lunch")) {
						finalClasses.set(6, allClasses.get(j));
						break;
					}
				
				//refresh and re-setup
				for(int k=0;k<blocksInClasses.size();k++) {
					if(blocksInClasses.get(k).contains("G")) {
						total2.set(k, total2.get(k)-1);
						blocksInClasses.get(k).remove("G");
					}
				}
				for(int k=0;k<classesInBlocks.size();k++) {
					if(classesInBlocks.get(k).contains("lunch")) {
						total1.set(k, total2.get(k)-1);
						classesInBlocks.get(k).remove("lunch");
					}
				}

				int index2 = blocks2.indexOf("G");
				total1.remove(index2);
				blocks2.remove(index2);
				classesInBlocks.remove(index2);
				i++;
				containLunch=true;
				isH=false;
			}
			
			
			
			
			
		}
		
		
		
		
		
		//transfer the classes from finalClasses to finalSchedule
		finalSchedule.setA(finalClasses.get(0));
		finalSchedule.setB(finalClasses.get(1));
		finalSchedule.setC(finalClasses.get(2));
		finalSchedule.setD(finalClasses.get(3));
		finalSchedule.setE(finalClasses.get(4));
		finalSchedule.setF(finalClasses.get(5));
		finalSchedule.setG(finalClasses.get(6));
		finalSchedule.setH(finalClasses.get(7));
		return finalSchedule;
	}
	
	
	
	
	
	
	
	public static int minI(ArrayList<Integer> a) {
		//a function to calculate the index of least number of a list
		
		int mini = 0;
		for(int i=0;i<a.size();i++)
			if(a.get(i)<a.get(mini))
				mini = i;
		return mini;
	}
}






