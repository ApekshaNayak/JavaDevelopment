/**
 * 
 */
package com.wsproject.soap.rpc;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * @author Apeksha
 *	Service Endpoint Interface.
 */

@WebService
@SOAPBinding(style =Style.RPC)
public interface HelloWorldInterface {
	 @WebMethod String getHelloWorldString(String name);
}
