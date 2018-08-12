

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Matrix;
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
		
		int opt = -2;
		
		while (opt != 7){
			
			System.out.print("\n\n" + 
							   "-1 - Matrix determinant\n" 		    +	
							   "0 - Matrix Inverse"					+ 
							   "1 - Vector Addition\n" 			    +	 
							   "2 - Vector Scaling\n"    			+ 
							   "3 - Gauss Jordan Elimination\n"     + 
							   "4 - Add Vector/s\n"    			    + 
							   "5 - Load Vector/s\n"			    + 
							   "6 - Delete All Vector/s\n"	 	    + 
							   "7 - Exit\n"    		   				+ 
							   "\nOption: ");
			opt = sc.nextInt();
			sc.nextLine();
			switch(opt){
				case -1: // Matrix Determinant
						double d2[] = {1, 2};
						v = new Vector(d2,2);
						listV.add(v);
						d2[0] = 3;
						d2[1] = 4;
						v = new Vector(d2,2);
						listV.add(v);
						System.out.println("Determinant: " + new Matrix(listV,2).determinant());
						break;
				case 0:
						List<Vector> list = new ArrayList<Vector>();
						double arr[] = {4,7};
						Vector v1 = new Vector(arr,2);
						list.add(v1);
						arr[0] = 2;
						arr[1] = 6;
						v1 = new Vector(arr,2);
						list.add(v1);
						Matrix matrix = new Matrix(list,2);
						Vector ve = matrix.inverse().getVectors().get(0);
						System.out.println(ve.getSpecificData(0) + " " + ve.getSpecificData(1));
						ve = matrix.inverse().getVectors().get(1);
						System.out.println(ve.getSpecificData(0) + " " + ve.getSpecificData(1));				
						break;
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
					double[] d = {0, 2};
					listV.add(new Vector(d, 2));
					d[0] = 0;
					d[1] = 4;
					//d[3] = 1;
					listV.add(new Vector(d, 2));

					d[0] = 0;
					d[1] = 9;
					//d[3] = 1;
					listV.add(new Vector(d, 2));

					d[0] = 0;
					d[1] = 2;
					//d[3] = 1;
					listV.add(new Vector(d, 2));
					d[0] = 2;
					d[1] = 4;
					v = new Vector(d, 2);
						v = Vector.Gauss_Jordan(listV, 2, v);
						System.out.println();
						if(v != null){
							System.out.print("Values: ");
							for(double val : v.getData()){
								System.out.print(val + " ");
							}
						} else System.out.println("No Solution");
					break; 
				/*case 3: //GaussJordanElimination (with display vector)
						double[] d = {1, 1, -3};
						listV.add(new Vector(d, 3));
						d[0] = 2;
						d[1] = 1;
						d[2] = -1;
						//d[3] = 1;
						listV.add(new Vector(d, 3));

						d[0] = 3;
						d[1] = 2;	
						d[2] = -4;
						//d[3] = -2;
						listV.add(new Vector(d, 3));

						d[0] = 4;
						d[1] = 2;
						d[2] = 7;
						//d[3] = 0;
						v = new Vector(d, 3);

							//System.out.println("\nSpan: " + Vector.span(listV, 3));
						
							v = Vector.Gauss_Jordan(listV, 3, v);
							System.out.println();
							if(v != null){
								System.out.print("Values: ");
								for(double val : v.getData()){
									System.out.print(val + " ");
								}
							} else System.out.println("No Solution");
						break; */
				case 4: newVector(listV);
						break;
				case 5: System.out.print("\nFile name: ");
				//		loadVectors(listV, sc.nextLine());
						break;
				case 6: listV = new ArrayList<Vector>();
				case 7: break;
				default: System.out.println("\nError: Invalid input.\n\n");
			}
		}
	}
}
