

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Vector;

public class Driver {

	
	public void displayVector(Vector v){
		System.out.println("Display vetor span");
	}
	

	public static String/*?*/ gaussJordanElimination(Vector v){
		return "";
	}
	
	public static Vector pickVector(List<Vector> listV){
		int opt = -1;
		Scanner sc = new Scanner(System.in);
		while (opt > listV.size() || opt < 0){
			System.out.println("\n");
			for(int i = 1; i <= listV.size(); i++)
				System.out.println(i + " - " + listV.get(i - 1).toString());
			System.out.print("0 - New Vector\nOption: ");
			opt = sc.nextInt();
		}
		
		if(opt != 0 )
			return listV.get(opt - 1);
		return newVector(listV);	
	}
	public static Vector newVector(List<Vector> listV){
		int opt = -1;
		Scanner sc = new Scanner(System.in);
		System.out.print("\n\nThe dimention of the vector (1 - 3): ");
		do{
			opt = sc.nextInt();
			if(opt<=0)
				System.out.println("Error: Please input a valid number. ");
		}while(opt<=0);
		System.out.println("\n\nPlease enter the coordinates");
		
		Vector v = new Vector();
		System.out.print("X: ");

		
		listV.add(v);
		return v;
	}
	public static void main(String[] args) {
		List<Vector> listV = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		Vector v, v2;
		
		int opt = -1;
		
		while (opt != 0){
			
			System.out.print("\n\n1 - Vector Addition\n" 			+	 
							   "2 - Vector Scaling\n"    			+ 
							   "3 - Gauss Jordan Elimination\n"     + 
							   "4 - Add Vector/s\n"    			    + 
							   "5 - Load Vector/s\n"			    + 
							   "6 - Delete All Vector/s\n"	 	    + 
							   "0 - Exit\n"    		   				+ 
							   "\nOption: ");
			opt = sc.nextInt();
			sc.nextLine();
			switch(opt){
				case 1: //VectorAddition
						// TODO: Multiple vector addition?
						v = pickVector(listV);
						v2 = pickVector(listV);
						if(v.getDimension() != v2.getDimension())
							System.out.println("Error: Vectors of different dimension cannot be added.");
						else {
							System.out.print(v.toString() + " + " + v2.toString() + "\n = ");
							v.add(v2);
							System.out.println(v.toString());
						}
						break;
				case 2: //VectorScaling
						v = pickVector(listV);
						System.out.print("Scale factor: ");
						v.scale(sc.nextInt());
						System.out.println("New value: " + v.toString());
						break;
				case 3: //GaussJordanElimination (with display vector)
						double[] d = {1, 2, 3};
						listV.add(new Vector(d, 3));

						listV.add(new Vector(d, 3));

						d[0] = 3;
						d[1] = 0;
						d[2] = -1;
						listV.add(new Vector(d, 3));

						d[0] = 9;
						d[1] = 8;
						d[2] = 3;
						v = new Vector(d, 3);

//							System.out.println("\nSpan: " + Vector.span(listV, 3));
						
							v = Vector.Gauss_Jordan(listV, 3, v);
							System.out.println();
							if(v != null){
								System.out.print("Values: ");
								for(double val : v.getData()){
									System.out.print(val + " ");
								}
							} else System.out.println("No Solution");
						break;
				case 4: newVector(listV);
						break;
				case 5: System.out.print("\nFile name: ");
				//		loadVectors(listV, sc.nextLine());
						break;
				case 6: listV = new ArrayList<Vector>();
				case 0: break;
				default: System.out.println("\nError: Invalid input.\n\n");
			}
		}
	}
}
