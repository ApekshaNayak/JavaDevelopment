package com.project.collections;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertyFileEx {
	public static void main(String[] args) throws Exception {
		// Reading a property file.
		System.out.println("Reading a properties file:");
		Properties propFile = new Properties();
		FileReader reader = new FileReader("resources/DB.properties");
		propFile.load(reader);

		System.out.println("User: " + propFile.getProperty("user"));
		System.out.println("password: " + propFile.getProperty("password"));

		// Getting system properties.
		System.out.println("Reading a System properties:");
		Properties sysProp = System.getProperties();
		Set<Entry<Object, Object>> sysSet = sysProp.entrySet();

		for (Map.Entry<Object, Object> es : sysSet) {
			System.out.println(es.getKey() + "\t" + es.getValue());
		}

		//creating a properties file.
		Properties createFile = new Properties();
		createFile.setProperty("Name", "Apeksha Nayak");
		createFile.setProperty("email","apeksha.g.nayak@gmail.com");
		createFile.setProperty("Address", "8201 Memorial Lane, Plano TX 75024");
		createFile.store(new FileWriter("resources/info.properties"), "Personal Information");
	}
}
