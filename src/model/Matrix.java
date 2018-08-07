package model;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

	private List<Vector> vectors;
	private int dimension;
	
	public Matrix(int dimension) {
		vectors = new ArrayList<Vector>();
		Vector vector = new Vector(dimension);
		for(int i = 0; i < dimension; i++) {
			vectors.add(vector);
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
		this.vectors = list;
		this.dimension = list.size();
	}
	
	public Matrix times(Matrix other) {
		
		/* Sets the row of the result by the column of Matrix other
		 * a x b * b x c = a x c */
		double elementData[] = new double[other.getDimension()];
		Vector resultVector = new Vector(other.getDimension());
		ArrayList<Vector> resultData = new ArrayList<Vector>();
		
		
		/* If the sizes of the matrices mismatch */
		if (dimension!=other.getVectors().get(0).getDimension()) {
			return null;
		}
		
		for(int i = 0; i < other.getDimension(); i++) {
			
		}
		
		/* The second parameter is the dimension of the vectors in the list */

		Matrix result = new Matrix(resultData, other.getDimension());
		return result;
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
	
}
