package com.practise;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 */

/**
 * @author Sainath
 * 
 */
public class UniqueSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> seatList = new ArrayList<String>();
		seatList.add("1A");
		seatList.add("2A");
		seatList.add("1B");
		seatList.add("2B");
		seatList.add("10A");
		seatList.add("3A");
		seatList.add("3B");
		seatList.add("4A");
		seatList.add("4B");
		seatList.add("5B");
		seatList.add("5A");
		seatList.add("6A");
		seatList.add("7A");
		seatList.add("8A");
		seatList.add("8B");
		seatList.add("7B");
		seatList.add("9A");
		Set<String> seatNoSet = new HashSet<String>();
		
		List<String> boardList = new ArrayList<String>();
		boardList.add("1");
		boardList.add("2");
		boardList.add("3");
		boardList.add("4");
		boardList.add("5");
		boardList.add("6");
		boardList.add("7");
		boardList.add("8");
		boardList.add("9");
		boardList.add("10");
		boardList.add("11");
		boardList.add("12");
		boardList.add("13");
		boardList.add("14");
		boardList.add("15");
		boardList.add("16");
		boardList.add("16");
		Set<String> boardNoSet = new HashSet<String>();
		for (String value : seatList)
			if (!seatNoSet.add(value))
				System.out.println("Found Duplicate " + value);

		for (String value : boardList)
			if (!boardNoSet.add(value))
				System.out.println("Found Duplicate " + value);

	}

}
