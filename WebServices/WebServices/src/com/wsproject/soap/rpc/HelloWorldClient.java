/**
 * 
 */
package com.wsproject.soap.rpc;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * @author Apeksha
 *
 */
public class HelloWorldClient {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:7779/ws/hello?wsdl");

		QName qName = new QName("http://rpc.soap.wsproject.com/",
				"HelloWorldInterfaceImplService");
		Service service = Service.create(url, qName);
		HelloWorldInterface helloWorld = service
				.getPort(HelloWorldInterface.class);
		System.out.println(helloWorld.getHelloWorldString("This is the HelloWorld Service"));

	}

}
