package com.camel.first.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "com.camel.first.ws.CodeFirstWS")
public class CodeFirstWSImpl implements CodeFirstWS {

	public String init(String cliente, String aux) {
		return "Hola";
	}

}
