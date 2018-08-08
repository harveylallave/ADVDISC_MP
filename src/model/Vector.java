package model;

import java.util.ArrayList;
import java.util.List;


/*	ADVIDISC MP Phase 1 - S18
 * 
 * 	Caleb Pensica
 * 	Harvey Lallave
 * 	Jeremiah Mojica
 */
public class Vector {
	
	private double data[];
	private int dimension;

	public Vector(){
	}
	
	public Vector(int dimension) {
		
		this.dimension = dimension;
		data = new double[dimension];
		for(int i = 0; i < dimension; i++) {
			data[i] = 0;
		}
	}
	
	public Vector(double data[], int dimension) {
//		System.out.println(data.length + " " + data[0]);
		this.data = new double[dimension];
		for(int i = 0; i < dimension; i++)
			this.data[i] = (i < data.length ? data[i] : 0);
		this.dimension = dimension;
	}

	public Vector scale(double num){
		
		for(int i = 0; i < dimension; i++) {
			data[i] = data[i] * num;
		}

		Vector result = new Vector(dimension);
		result.setData(data);
		return result;
	};
	
	/*
	 * TODO "Errors for size mismatches when adding vectors must also be handled."
	 * -> Not sure if pweds nang return null nalang
	 */
	public Vector add(Vector v){

		if(v.getDimension() == dimension){
			double[] data2 = v.getData();
			for(int i = 0; i < dimension; i++) {
				data[i] += data2[i];
			}
			Vector result = new Vector(dimension);
			result.setData(data);
			return result;
		}
		return null;
	};
	
	public static Vector Gauss_Jordan (List<Vector> vectors, int dimension, Vector constants) {
		Vector result = null;
		
		double[] constantsArr = new double[dimension];
		int[] firstNonZeroIndex = new int[dimension];
		double temp = 0;
		
		for(int i = 0; i < dimension; i++){
			constantsArr[i] = (i < constants.dimension ? constants.data[i] : 0);
			firstNonZeroIndex[i] = Integer.MAX_VALUE;
		}
		
		//displayAugmentedMatrix(vectors, dimension, constantsArr);
		
		// Add zero vector and 0 constant if the size is not the same
		while(vectors.size() < dimension)
			vectors.add(new Vector(dimension));
		
		// Sort vectors based on the position of the first non zero element
		for(int i = 0; i < vectors.size(); i++){
			for(int j = 0; j < dimension && firstNonZeroIndex[i] == Integer.MAX_VALUE; j++)
				if(vectors.get(i).getSpecificData(j) != 0)
					firstNonZeroIndex[i] = j;
		}
		for(int i = 1; i < dimension; i++)
			if(firstNonZeroIndex[i - 1] > firstNonZeroIndex[i]){
				int tempIndex = firstNonZeroIndex[i];
				firstNonZeroIndex[i] = firstNonZeroIndex[i - 1];
				firstNonZeroIndex[i - 1] = tempIndex;
				
				Vector tempVector = vectors.get(i);
				vectors.remove(i);
				vectors.add(i - 1, tempVector);
				
				temp = constantsArr[i];
				constantsArr[i] = constantsArr[i - 1];
				constantsArr[i - 1] = temp;
				i = 0;
			}
		
			

	//	displayAugmentedMatrix(vectors, dimension, constantsArr);
		
		boolean valid = validRowGaussJordan(vectors.get(0), constantsArr[0]);
		// Row Echelon Form (Lower/left half)
		for(int i = 0; i < vectors.size() && valid; i++)
			for(int j = 0; j <= i && valid; j++){
				temp = vectors.get(i).getSpecificData(j);
				if(temp != 0)
					if(i == j){ // Make current value of index [i][j] == 1
							vectors.get(i).scale(1/temp);
							constantsArr[i] /= temp;
					}
					else if(j < i){	// Make current value == 0
						constantsArr[i] += constantsArr[j] * -1 * temp;
						vectors.get(i).add(vectors.get(j).scale(-1*temp));	
						vectors.get(j).scale(-1/temp);
					}
				valid = validRowGaussJordan(vectors.get(i), constantsArr[i]);
			}
	

	//	displayAugmentedMatrix(vectors, dimension, constantsArr);
		
		// Reduced row echelon form (Upper/Right half)
		for(int i = vectors.size() - 1; i >= 0  && valid; i--)
			for(int j = dimension - 1; j > i && valid; j--){
				temp = vectors.get(i).getSpecificData(j);
				if(temp != 0 && j > i){	// Make current value == 0
					constantsArr[i] += constantsArr[j] * -1 * temp;
					vectors.get(i).add(vectors.get(j).scale(-1 * temp));
					vectors.get(j).scale(-1/temp);
				}
				valid = validRowGaussJordan(vectors.get(i), constantsArr[i]);
			}

		//displayAugmentedMatrix(vectors, dimension, constantsArr);
		
		if(valid){
			result = new Vector(dimension);
			// TODO How to initialize result vector with the case of infinite number of solutions
//			for(int i = 0; i < dimension; i++)
//				vectors.get(i), constantsArr[i], i);
				
			result.setData(constantsArr);
		}
		//System.out.println(valid);
		return result;
	}

	private static void displayAugmentedMatrix(List<Vector> vectors, int dimension, double[] constantsArr) {

		for(int i = 0; i < vectors.size(); i++){
			for(int j = 0; j < dimension; j++)
				System.out.print(vectors.get(i).getSpecificData(j) + " ");
			System.out.println(" | " + constantsArr[i]);
		}
		System.out.println();
		
	}

	private static boolean validRowGaussJordan(Vector vector, double d) {
		// TODO Auto-generated method stub
		boolean allZeroes = true;
		for(int i = 0; i < vector.getDimension(); i++)
			if(vector.getSpecificData(i) != 0)
				allZeroes = false;
		
		return d == 0 || !allZeroes; 
	}


	public static int span(List<Vector> vectors, int dimension){
		//System.out.println();
		Gauss_Jordan(vectors, dimension, new Vector(dimension));
		int nZeroRows = 0;
		List<double[]> nonZeroRows = new ArrayList<double[]>();
		
		
		boolean next; 
		for(int i = 0; i < vectors.size(); i++){
			next = false;
			for(int j = 0; j < dimension && !next; j++)
				if(vectors.get(i).getSpecificData(j) != 0 && 
				   !nonZeroRows.contains(vectors.get(i).getData())){
					next = true;
					nZeroRows += 1;
					nonZeroRows.add(vectors.get(i).getData());
				}
		}
		return nZeroRows;
	}
	
	public double[] getData() {
		return data;
	}
	
	public double getSpecificData(int index) {
		return data[index];
	}

	public void setData(double[] data) {
		this.data = new double[dimension];
		for(int i = 0; i < dimension; i++)
			this.data[i] = (i < data.length ? data[i] : 0);
	}

	public int getDimension() {
		return dimension;
	}
	
	public void setSpecificData(int index, double data) {
		this.data[index] = data;
	}
}
