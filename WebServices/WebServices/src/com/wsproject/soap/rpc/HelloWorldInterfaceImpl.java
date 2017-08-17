/**
 * 
 */
package com.wsproject.soap.rpc;

import javax.jws.WebService;

/**
 * @author Apeksha
 *	Service Implementation.
 */
@WebService(endpointInterface = "com.wsproject.soap.rpc.HelloWorldInterface")
public class HelloWorldInterfaceImpl implements HelloWorldInterface{

	@Override
	public String getHelloWorldString(String name) {
		return "WebService: JAX-RPC style: "+name ;
	}

}
