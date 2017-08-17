package com.practise;
class Robot {
	// Method passing by parameters.
	void speak(String text) {
		System.out.println(text);
	}

	void move(String direction, double distance) {
		System.out.printf("Travelled %.2f miles in the %s direction\n", distance,
				direction);
		double newDist = distance + 3;
		System.out.println("I further travelled "+newDist+ " miles in the "+direction+" direction");
	}
}

public class MethodParameters {

	public static void main(String[] args) {
		String greeting = "Good evening.";
		String direction = "North";
		double dist = 243;
		
		Robot myRobot = new Robot();
		myRobot.speak(greeting);
		myRobot.move(direction, dist);

	}

}
