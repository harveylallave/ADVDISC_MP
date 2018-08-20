package model;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

	private List<Vector> vectors;
	private int dimension;
	
	public Matrix(int dimension) {
		vectors = new ArrayList<Vector>();
		for(int i = 0; i < dimension; i++) {
			vectors.add(new Vector(dimension));
		}
		this.dimension = dimension;
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				if (i==j) {
					vectors.get(i).setSpecificData(i,1);
				}
			}
		}
	}
	
	public Matrix(List<Vector> list, int vectorDimension) {
		vectors = new ArrayList<Vector>();

		for(int i = 0; i < list.size(); i++)
			vectors.add(new Vector(list.get(i).getData(), list.get(i).getDimension()));
		this.dimension = vectorDimension;
	}
	
	public Matrix times(Matrix other) {
		
		/* Sets the row of the result by the column of Matrix other
		 * a x b * b x c = a x c */
		double elementData[] = new double[other.getDimension()];
		Vector resultVector = new Vector(other.getDimension());
		ArrayList<Vector> resultData = new ArrayList<Vector>();

		
		/* If the sizes of the matrices mismatch */
		if (dimension != other.vectors.size()) 
			return null;
		
		for(int i = 0; i < vectors.size(); i++)
			resultData.add(new Vector(other.dimension));
		
			
		for(int i = 0; i < vectors.size(); i++) {
			for(int j = 0; j < other.dimension; j++){
				
				double val = 0;
				
				for(int k = 0; k < vectors.get(i).getDimension(); k++)
					val += vectors.get(i).getSpecificData(k) * other.vectors.get(k).getSpecificData(j);
				
				resultData.get(i).setSpecificData(j, val);
			}
			
		}
		
		/* The second parameter is the dimension of the vectors in the list */

		Matrix result = new Matrix(resultData, other.dimension);
		result.displayMatrix();
		return result;
	}
	
	public Matrix inverse() {

//		displayMatrix();
		if(determinant() == 0) 
			return null;
//		displayMatrix();
		return new Matrix(Vector.Gauss_Jordan(vectors, dimension, new Matrix(dimension).vectors),
						  dimension);
	}
	
	public double determinant(){
		double x = 1;
	
		if (vectors.size() != dimension)
			return (Double) null;
	
		return Vector.Gauss_Jordan_Determinant(vectors, dimension, x);
	}
	
	public List<Vector> getVectors() {
		return vectors;
	}
	public void setVectors(List<Vector> vectors) {
		this.vectors = vectors;
	}
	public int getDimension() {
		return dimension;
	}

	public void displayMatrix() {

		for(int i = 0; i < vectors.size(); i++){
			for(int j = 0; j < dimension; j++)
				System.out.print(vectors.get(i).getSpecificData(j) + " ");
			System.out.println();
		}
		System.out.println();
		
	}
	
	public Matrix transpose(){
		Matrix m2 = new Matrix(vectors.size());
		m2.vectors = new ArrayList<Vector>();
		for(int i = 0; i < dimension; i++)
			m2.vectors.add(new Vector(vectors.size()));
		
		for(int i = 0; i < vectors.size(); i++)
			for(int j = 0; j < dimension; j++)
				m2.vectors.get(j).setSpecificData(i, vectors.get(i).getSpecificData(j));
		return m2;
	}
}
