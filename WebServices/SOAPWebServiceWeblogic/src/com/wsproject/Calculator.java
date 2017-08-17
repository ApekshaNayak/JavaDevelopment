package com.wsproject;

import javax.jws.*;

@WebService(serviceName = "CalculatorProgram")
public class Calculator {

	@WebMethod(operationName = "addition")
	public int addition(int param1, int param2) {
		return param1 + param2;
	}
	
	@WebMethod(operationName = "multiplication")
	public int multiplication(int param1, int param2) {
		return param1 * param2;
	}
}
