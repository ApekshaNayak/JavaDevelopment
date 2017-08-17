/**
 * 
 */
package com.wsproject.soap.document;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * @author Apeksha
 *
 */
public class BillBoardClient {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:7779/ws/billBoard");
		QName qname = new QName("http://document.soap.wsproject.com/",
				"BillBoardImplService");
		
		Service service = Service.create(url, qname);
		BillBoard billBoard = service.getPort(BillBoard.class);
		System.out.println(billBoard.getBillBoard("Welcome to Plano"));
		
		
		

	}

}
