package com.practise;
class Plant {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/*
	 * Constructors can be used to initialize class variable. When a class is
	 * initialized the constructor is automatically called
	 */
	public Plant() {
		this("Hibiscus", 8);
		System.out.println("Constructor1");
	}

	public Plant(String name) {
		this.name = name;
		System.out.println("Constructor2");
	}

	public Plant(String name, int age) {
		this.name = name;
		this.age = age;
		System.out.println("Constructor3");
	}
}

public class Arrays {
	public static void main(String[] args) {
		/* Single dimensional integer and String arrays. */
		int[] primeNum = { 1, 3, 5, 7, 11, 13, 17 };
		String[] rainbowColors = { "Voilet", "Indigo", "Blue", "Grey",
				"Yellow", "Orange", "Red" };
		System.out.print("The Prime numbers are:");
		for (int i = 0; i < primeNum.length; i++) {
			System.out.print(primeNum[i] + "\t");
		}

		System.out.print("\nThe colors in the rainbow are: ");
		for (String rainbowColor : rainbowColors) {
			System.out.print(rainbowColor + "\t");
		}

		// Multi-dimensional integer array.
		System.out.println("\n2-Dimensional Array:");
		int[][] evenOddNums = new int[2][2];
		evenOddNums[0][0] = 2;
		evenOddNums[0][1] = 4;
		evenOddNums[1][0] = 3;
		evenOddNums[1][1] = 5;
		for (int row = 0; row < evenOddNums.length; row++) {
			for (int col = 0; col < evenOddNums.length; col++) {
				System.out.print(evenOddNums[row][col] + "\t");
			}
		}
		System.out.println("Array length: "+ evenOddNums.length);

		// 3-dimensional string array.
		System.out.print("\nMultiDimensional Array:");
		String[][][] animals = { { { "cat", "dog", "fish", "snail", "parrot" },
				{ "cockroach", "bed bug", "lizard" },
				{ "lion", "tiger", "elephant" } } };
		for (int i = 0; i < animals.length; i++) {
			for (int j = 0; j < animals[i].length; j++) {
				for (int k = 0; k < animals[i][j].length; k++) {
					System.out.print(animals[i][j][k] + "\t\n");
				}
			}
		}
		Plant plant = new Plant("Neem tree");
		System.out.println("Name: " + plant.getName() + "\tAge: "
				+ plant.getAge());

	}
}
