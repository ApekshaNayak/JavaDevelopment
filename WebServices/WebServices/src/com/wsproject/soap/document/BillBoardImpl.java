/**
 * 
 */
package com.wsproject.soap.document;

import javax.jws.WebService;

/**
 * @author Apeksha
 *
 */
@WebService(endpointInterface = "com.wsproject.soap.document.BillBoard")
public class BillBoardImpl implements BillBoard{

	@Override
	public String getBillBoard(String item) {
		return "WebService JAX-Document style: "+item;
	}

}
