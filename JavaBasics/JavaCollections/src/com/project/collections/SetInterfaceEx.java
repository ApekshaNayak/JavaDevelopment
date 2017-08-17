package com.project.collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetInterfaceEx {

	public static void main(String[] args) {
		/* HashSet */
		HashSet<String> animalSet = new HashSet<String>();
		animalSet.add("Cat");
		animalSet.add("Dog");
		animalSet.add("Squirrel");
		animalSet.add("Dog");
		animalSet.add("Turtle");
		animalSet.add("Fish");

		System.out.println("Elements of the set: ");
		for (String hs : animalSet) {
			System.out.print("\t" + hs);
		}

		/* LinkedHashSet */
		LinkedHashSet<String> birds = new LinkedHashSet<String>();
		birds.add("Peacock");
		birds.add("Parrot");
		birds.add("Sparrow");
		birds.add("Pigeon");
		birds.add("Crow");
		birds.add("Parrot");

		System.out.println();
		Iterator<String> iter = birds.iterator();
		while (iter.hasNext()) {
			System.out.println("Birds are: " + iter.next());
		}

		/* Tree Set */
		TreeSet<String> trees = new TreeSet<String>();
		trees.add("Pine");
		trees.add("Mango");
		trees.add("Banyan");
		trees.add("Coconut");
		trees.add("Jackfruit");
		trees.add("Fir");
		
		System.out.println("Trees are: ");
		Iterator<String> treesIter= trees.iterator();
		while(treesIter.hasNext()) {
			System.out.println(treesIter.next());
		}
		
		Iterator<String> treesIterDesc= trees.descendingIterator();
		while(treesIterDesc.hasNext()) {
			System.out.println(treesIterDesc.next());
		}
	}

}
