/**
 * 
 */
package com.practise;

/**
 * @author Apeksha
 * except String all the other datatypes can be casted to each other.Some data types need casting 
 * whereas others dont. 
 */
class Machine {
	public void start() {
		System.out.println("Machine started");
	}
}

class Camera extends Machine{
	public void start() {
		System.out.println("Camera started");
	}
	
	public void snap() {
		System.out.println("Photo taken");
	}
}

public class Casting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte byteVal = 28; // 8 bits
		short shortVal = 234; // 16 bits
		int intVal = 5555; // 32 bits
		long longVal = 738345; // 64 bits
		float floatVal = 1234.565f; // 32 bits
		double doubleVal = 78494948; // 64 bits
		String name = "Apeksha";

		// byte castings.
		byte newVal = (byte) shortVal;
		/*byte byteVal1 = (byte) intVal;
		byte byteVal2 = (byte) longVal;
		byte byteVal3 = (byte) floatVal;
		byte byteVal4 = (byte) doubleVal;*/

		short newVal1 = byteVal;
		short newVal11 = (short) intVal;
		
		System.out.println(byteVal);
		System.out.println(newVal1);

		// int castings.
		int intsVal = shortVal;
		int newVa2l2 = (int) longVal;
		int newVal33 = (int) doubleVal;
		int intVal1 = (int) floatVal;
		
		//float castings.
		float newbval = byteVal;
		float newFVal = longVal;
		float newIVal = intVal;
		float newdVal = (float) doubleVal;
		
		//double castings.
		double doubByVal = byteVal;
		double doubIntVal = intVal;
		double doubFVal = floatVal;
		double doubLVal = longVal;
		
		//Upcasting.
		Machine machine1 = new Camera();
		machine1.start();
		
		//Downcasting.
		Machine machine2 = new Camera();
		Camera camera2 = (Camera) machine2;
		camera2.start();
		camera2.snap();
		
		//Error-Class cast exception.
		/*Machine machine3 = new Machine();
		Camera camera3 = (Camera) machine3;
		camera3.start(); 
		camera3.snap();*/
		

	}

}
