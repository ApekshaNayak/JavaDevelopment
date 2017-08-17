/**
 * 
 */
package com.wsproject.soap.rpc;

import javax.xml.ws.Endpoint;

/**
 * @author Apeksha Publisher class.
 */
public class HelloWorldPublisher {

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:7779/ws/hello",
				new HelloWorldInterfaceImpl());
		System.out.println("Success");
	}

}
