/**
 * 
 */
package com.wsproject.soap.document;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * @author Apeksha
 *
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface BillBoard {
	@WebMethod String getBillBoard(String item);
}
