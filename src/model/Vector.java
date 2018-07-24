package model;

public class Vector {
	
	private double data[];
	private int dimension;

	public Vector(){
	}
	
	public Vector(int dimension) {
		data = new double[dimension];
		for(int i = 0; i < dimension; i++) {
			data[i] = 0;
		}
	}
	
	public Vector(double data[], int dimension) {
		this.data = data;
		this.dimension = dimension;
		
	}


	public Vector scale(int num){

		for(int i = 0; i < dimension; i++) {
			data[i] = data[i]*num;
		}
		return this;
	};
	
	public Vector add(Vector v){

		for(int i = 0; i < dimension; i++) {
			data[i] += v.getData()[i];
		}
		return this;
	};

	public double[] getData() {
		return data;
	}

	public void setData(double[] data) {
		this.data = data;
	}
	
	
}
