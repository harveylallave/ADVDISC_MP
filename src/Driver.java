

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Vector;

public class Driver {

	
	public static void loadVectors(ArrayList<Vector> vectors, String fileName){


	    // This will reference one line at a time
	    String line = null;

	    try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = 
	            new FileReader("src\\" + (fileName.contains(".txt.") ? fileName : fileName +".txt"));

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = 
	            new BufferedReader(fileReader);

	        while((line = bufferedReader.readLine()) != null) {
        		Vector v = new Vector();
        		String[] xyz = line.split(" ");
	        	for(int i = 0; i < xyz.length; i++){
	        		v.setCoordinates(i, Integer.parseInt(xyz[i]));
	        	}
	        	vectors.add(v);
	        }   

	        // Always close files.
	        bufferedReader.close();         
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println(
	            "Error: File '" + 
	            fileName + "' not found");                
	    }
	    catch(IOException ex) {
	        System.out.println(
	            "Error: File '" 
	            + fileName + "' is unreadable");                  
	        // Or we could just do this: 
	        // ex.printStackTrace();
      }
   }
	
	
	public void displayVector(Vector v){
		System.out.println("Display vetor span");
	}
	

	public static String/*?*/ gaussJordanElimination(Vector v){
		return "";
	}
	
	public static void main(String[] args) {
		ArrayList<Vector> vectors = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		Vector v = new Vector(1, 2, 0);
		
		int opt = -1;
		
		while (opt != 0){
			
			System.out.print("\n\n1 - Vector Addition\n" 			+	 
							   "2 - Vector Scaling\n"    			+ 
							   "3 - Gauss Jordan Elimination\n"     + 
							   "4 - Load Vector\n"     + 
							   "0 - Exit\n"    		   				+ 
							   "\nOption: ");
			opt = sc.nextInt();
			sc.nextLine();
			switch(opt){
				case 1: //VectorAddition
						break;
				case 2: //VectorScaling
						break;
				case 3: //GaussJordanElimination (with display vector)
						break;
				case 4: System.out.print("\nFile name: ");
						loadVectors(vectors, sc.nextLine());
						break;
				case 0: break;
				default: System.out.println("\nError: Invalid input.\n\n");
			}
		}
	}

}
