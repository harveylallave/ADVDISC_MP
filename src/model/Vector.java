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
	

	public static double Gauss_Jordan_Determinant (List<Vector> vectors, int dimension, double x) {
		Vector result = null;

		// Add zero vector if needed
		while(vectors.size() < dimension)
			vectors.add(new Vector(dimension));
		
		int[] firstNonZeroIndex = new int[vectors.size()];
		double temp = 0;
		boolean changed = false;
		
		// Add 0 constant if needed
		for(int i = 0; i < vectors.size(); i++){
			firstNonZeroIndex[i] = Integer.MAX_VALUE;
		}
		
		// Sort vectors based on the position of the first non zero element
		for(int i = 0; i < vectors.size(); i++){
			for(int j = 0; j < dimension && firstNonZeroIndex[i] == Integer.MAX_VALUE; j++)
				if(vectors.get(i).getSpecificData(j) != 0)
					firstNonZeroIndex[i] = j;
		}
		for(int i = 1; i < vectors.size(); i++)
			if(firstNonZeroIndex[i - 1] > firstNonZeroIndex[i]){
				int tempIndex = firstNonZeroIndex[i];
				firstNonZeroIndex[i] = firstNonZeroIndex[i - 1];
				firstNonZeroIndex[i - 1] = tempIndex;
				
				Vector tempVector = vectors.get(i);
				vectors.remove(i);
				vectors.add(i - 1, tempVector);
				
				
				x *= -1;
				i = 0;
			}
		
		do{
			// Row Echelon Form (Lower/left half)
			for(int i = 0; i < vectors.size(); i++)
				for(int j = 0; j <= i && j < dimension; j++){
					temp = vectors.get(i).getSpecificData(j);
					if(temp != 0)
						if(i == j){ // Make current value of index [i][j] == 1
								vectors.get(i).scale(1/temp);
								x /= temp;
						}
						else if(j < i && vectors.get(j).getSpecificData(j) != 0){	// Make current value == 0
							vectors.get(i).add(vectors.get(j).scale(-1*temp));	
							vectors.get(j).scale(-1/temp);
						}
				}
		
			firstNonZeroIndex = new int[vectors.size()];
			temp = 0;
			changed = false;
			
			// Add 0 constant if needed
			for(int i = 0; i < vectors.size(); i++){
				firstNonZeroIndex[i] = Integer.MAX_VALUE;
			}
			
			// Sort vectors based on the position of the first non zero element
			for(int i = 0; i < vectors.size(); i++){
				for(int j = 0; j < dimension && firstNonZeroIndex[i] == Integer.MAX_VALUE; j++)
					if(vectors.get(i).getSpecificData(j) != 0)
						firstNonZeroIndex[i] = j;
			}
			for(int i = 1; i < vectors.size(); i++)
				if(firstNonZeroIndex[i - 1] > firstNonZeroIndex[i]){
					changed = true;
					int tempIndex = firstNonZeroIndex[i];
					firstNonZeroIndex[i] = firstNonZeroIndex[i - 1];
					firstNonZeroIndex[i - 1] = tempIndex;
					
					Vector tempVector = vectors.get(i);
					vectors.remove(i);
					vectors.add(i - 1, tempVector);
					
					x *= -1;
					i = 0;
				}
		} while(changed);
		
		boolean identityREF = true;
		for(int i = 0; i < vectors.size() && identityREF; i++)
			for(int j = 0; j <= i && identityREF; j++)
				if(i == j && vectors.get(i).getSpecificData(j) != 1)
					identityREF = false;
				
		x  = identityREF ?  1/x : 0/x;
		return x;
	}
	
	// Original Gauss Jordan
	public static Vector Gauss_Jordan (List<Vector> vectors, int dimension, Vector constants) {
		Vector result = null;

		// Add zero vector if needed
		while(vectors.size() < dimension)
			vectors.add(new Vector(dimension));
		
		double[] constantsArr = new double[vectors.size()];
		int[] firstNonZeroIndex = new int[vectors.size()];
		double temp = 0;
		boolean changed = false;
		
		// Add 0 constant if needed
		for(int i = 0; i < vectors.size(); i++){
			constantsArr[i] = (i < constants.dimension ? constants.data[i] : 0);
			firstNonZeroIndex[i] = Integer.MAX_VALUE;
		}
		
		// Sort vectors based on the position of the first non zero element
		for(int i = 0; i < vectors.size(); i++){
			for(int j = 0; j < dimension && firstNonZeroIndex[i] == Integer.MAX_VALUE; j++)
				if(vectors.get(i).getSpecificData(j) != 0)
					firstNonZeroIndex[i] = j;
		}
		for(int i = 1; i < vectors.size(); i++)
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
		
		do{
			// Row Echelon Form (Lower/left half)
			for(int i = 0; i < vectors.size(); i++)
				for(int j = 0; j <= i && j < dimension; j++){
					temp = vectors.get(i).getSpecificData(j);
					if(temp != 0)
						if(i == j){ // Make current value of index [i][j] == 1
								vectors.get(i).scale(1/temp);
								constantsArr[i] /= temp;
						}
						else if(j < i && vectors.get(j).getSpecificData(j) != 0){	// Make current value == 0
							constantsArr[i] += constantsArr[j] * -1 * temp;
							vectors.get(i).add(vectors.get(j).scale(-1*temp));	
							vectors.get(j).scale(-1/temp);
						}
				}
		
			firstNonZeroIndex = new int[vectors.size()];
			temp = 0;
			changed = false;
			
			// Add 0 constant if needed
			for(int i = 0; i < vectors.size(); i++){
				firstNonZeroIndex[i] = Integer.MAX_VALUE;
			}
			
			// Sort vectors based on the position of the first non zero element
			for(int i = 0; i < vectors.size(); i++){
				for(int j = 0; j < dimension && firstNonZeroIndex[i] == Integer.MAX_VALUE; j++)
					if(vectors.get(i).getSpecificData(j) != 0)
						firstNonZeroIndex[i] = j;
			}
			for(int i = 1; i < vectors.size(); i++)
				if(firstNonZeroIndex[i - 1] > firstNonZeroIndex[i]){
					changed = true;
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
		} while(changed);
		
		
		boolean valid = true;
		for(int i = vectors.size() - 1; i >= 0  && valid; i--)
			valid = validRowGaussJordan(vectors.get(i), constantsArr[i]);
		
		if(valid){
			// Reduced row echelon form (Upper/Right half)
			for(int i = vectors.size() - 1; i >= 0 ; i--)
				for(int j = dimension - 1; j > i; j--){
					temp = vectors.get(i).getSpecificData(j);
					if(temp != 0 && j > i){	// Make current value == 0
						constantsArr[i] += constantsArr[j] * -1 * temp;
						vectors.get(i).add(vectors.get(j).scale(-1 * temp));
						vectors.get(j).scale(-1/temp);
					}
				}
	
			for(int i = vectors.size() - 1; i >= 0  && valid; i--)
				valid = validRowGaussJordan(vectors.get(i), constantsArr[i]);	
			
			if(valid){
				result = new Vector(dimension);				
				result.setData(constantsArr);
			}
		}		

		return result;
	}
	
	public static List<Vector> Gauss_Jordan (List<Vector> vectors, int dimension, List<Vector> constants) {
		List<Vector> result = null;
		
		List<Vector> constantsArr = constants;
		//double[] constantsArr = new double[dimension];
		int[] firstNonZeroIndex = new int[dimension];
		double temp = 0;
		for(int i = 0; i < constants.size(); i++) { 
			for(int j = 0; j < dimension; j++){
				constantsArr.get(i).data[j] = (j < constants.get(i).dimension ? constants.get(i).data[j] : 0);
				firstNonZeroIndex[j] = Integer.MAX_VALUE;
			}
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
				
				Vector temp2;
				
				temp2 = constantsArr.get(i);
				constantsArr.set(i, constantsArr.get(i-1));
				constantsArr.set(i,temp2);
				i = 0;
			}
		
		
			

	//	displayAugmentedMatrix(vectors, dimension, constantsArr);
		
		boolean valid = validRowGaussJordan(vectors.get(0), constantsArr.get(0).data[0]);
		
		// Row Echelon Form (Lower/left half)
		for(int i = 0; i < vectors.size() && valid; i++)
			for(int j = 0; j <= i && valid; j++){
				temp = vectors.get(i).getSpecificData(j);
				if(temp != 0)
					if(i == j){ // Make current value of index [i][j] == 1
							vectors.get(i).scale(1/temp);
							constantsArr.get(i).scale(1/temp);
					}
					else if(j < i){	// Make current value == 0
						constantsArr.get(i).add(constantsArr.get(j).scale(-1*temp));
						vectors.get(i).add(vectors.get(j).scale(-1*temp));	
						vectors.get(j).scale(-1/temp);
					}
				
				for(int x = 0; x< constantsArr.get(i).dimension; x++) {
					valid = validRowGaussJordan(vectors.get(i), constantsArr.get(i).data[x]);
					if(valid==false) {
						valid = false;
						break;
					}
				}
			}
	

	//	displayAugmentedMatrix(vectors, dimension, constantsArr);
		
		// Reduced row echelon form (Upper/Right half)
		for(int i = vectors.size() - 1; i >= 0  && valid; i--)
			for(int j = dimension - 1; j > i && valid; j--){
				temp = vectors.get(i).getSpecificData(j);
				if(temp != 0 && j > i){	// Make current value == 0
					constantsArr.get(i).add(constantsArr.get(j).scale(-1 * temp));
					vectors.get(i).add(vectors.get(j).scale(-1 * temp));
					vectors.get(j).scale(-1/temp);
				}
				for(int x = 0; x< constantsArr.get(i).dimension; x++) {
					valid = validRowGaussJordan(vectors.get(i), constantsArr.get(i).data[x]);
					if(valid==false) {
						valid = false;
						break;
					}
				}
			}

		//displayAugmentedMatrix(vectors, dimension, constantsArr);
		
		if(valid){
			result = new ArrayList<Vector>();
			// TODO How to initialize result vector with the case of infinite number of solutions
//			for(int i = 0; i < dimension; i++)
//				vectors.get(i), constantsArr[i], i);
			for(int i = 0; i < constantsArr.size(); i++)
				result.add(constantsArr.get(i));
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
		for(int i = 0; i < vector.getDimension() && allZeroes; i++)
			if(vector.getSpecificData(i) != 0)
				allZeroes = false;
		
		return d == 0 && allZeroes || d != 0 && !allZeroes; 
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
