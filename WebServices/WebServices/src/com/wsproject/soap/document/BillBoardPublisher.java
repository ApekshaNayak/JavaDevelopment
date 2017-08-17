/**
 * 
 */
package com.wsproject.soap.document;

import javax.xml.ws.Endpoint;

/**
 * @author Apeksha
 *
 */
public class BillBoardPublisher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:7779/ws/billBoard",
				 new BillBoardImpl());
		System.out.println("Service published");

	}

}
