package model;

public class Vector {

	private Integer x,
					x2,
					y,
					z;

	public Vector(){
		super();

		x  = null;
		x2 = null;
		y  = null;
		z  = null;
	}
	
	public Vector(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void scale(int num){
		// TODO: scale variables
		
		switch(getDimension()){
			case 3: this.z  *= num;
			case 2: this.y  *= num;
			case 1: this.x  *= num;
					if(getDimension() == 1)
						this.x2 *= num;
		}
	};
	
	public void addition(Vector v){

		this.x += v.x;		
		switch(getDimension()){
			case 3: this.z   += v.z;
			case 2: this.y   += v.y; break;
			case 1: this.x2  += v.x2;
		}
	};

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getX2() {
		return x2;
	}

	public void setX2(int x) {
		this.x2 = x;
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


	public void setCoordinates(int i, int val, int dimension) {

		switch(i){
			case 1: x = val; break;
			case 2: if(dimension == 1)
						 x2 = val; 
					else y = val; 
					break;
			case 3: z = val; break;
			default: System.out.println("Error: Invalid input set coordinates");
		}
		
	}
	
	public int getDimension(){
		if(x2 != (Integer) null)
			 return 1;
		else if(z == (Integer)null)
			 return 2;
		else return 3;
	}

	@Override
	public String toString() {
		int dimension = getDimension();
		return dimension + "D Vector [x = " + x + ", " + 
			  (dimension == 1 ? "x2 = " + x2 : "y  = " + y) + 
			  (dimension == 3 ? ", z =" + z : "") + "]";
	}
	
}
