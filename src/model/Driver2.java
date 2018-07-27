package model;

public class Driver2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] arr = {1,2,3};
		Vector v1 = new Vector(arr,3);
		
		v1.add(v1);
		v1.scale(2);
		System.out.println(v1.getData()[0] + " " + v1.getData()[1] + " " + v1.getData()[2]);
	}

}
