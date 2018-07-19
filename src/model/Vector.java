package model;

public class Vector {

	private int x,
				y,
				z;

	public Vector(){
		super();
	}
	
	public Vector(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void scale(int num){
		// TODO: scale variables
		this.x *= num;
		this.y *= num;
		this.z *= num;
	};
	
	public void addition(Vector v){
		// TODO: vector addition
		
	};
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	public void setCoordinates(int i, int val) {

		switch(i){
			case 1: x = val; break;
			case 2: y = val; break;
			case 3: z = val; break;
			default: System.out.println("Error: Invalid input set coordinates");
		}
		
	}
	
	
}
