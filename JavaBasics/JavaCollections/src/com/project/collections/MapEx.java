package com.project.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapEx {
	public static void main(String[] args) {
		//HashMap
		HashMap<Integer, String> employee = new HashMap<Integer, String>();
		employee.put(120, "Aruna");
		employee.put(101, "Apeksha");
		employee.put(102, "Akansha");
		employee.put(103, "Anusha");
		employee.put(107, "Arpita");
		employee.put(105, "Apoorva");
		employee.put(null,null);//doesnt read/display it.
		employee.put(null, "Kavita");//doesnt take more than one null value.
		employee.put(115, null);
		employee.put(null, "Krithi");
		employee.put(125, null);
		System.out.println("HashMap");
		for(Map.Entry<Integer, String> m: employee.entrySet()){
			System.out.println(m.getKey()+" "+m.getValue());
		}
		
		//LinkedHashMap
		LinkedHashMap<Integer, String> emp = new LinkedHashMap<Integer, String>();
		emp.put(120, "Aruna");
		emp.put(101, "Apeksha");
		emp.put(102, "Akansha");
		emp.put(103, "Anusha");
		emp.put(107, "Arpita");
		emp.put(105, "Apoorva");
		emp.put(null,null);
		emp.put(null, "Kavita");//doesnt take more than one null value.
		emp.put(115, null);
		emp.put(null, "Krithi");
		emp.put(125, null);
		System.out.println("LinkedHashMap");
		for(Map.Entry<Integer, String> m2: emp.entrySet()) {
			System.out.println(m2.getKey()+" "+m2.getValue());
		}
	}

}
