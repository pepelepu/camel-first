package com.camel.first.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface CodeFirstWS {

	@WebMethod
	String init(@WebParam(name = "cliente") String cliente,
			@WebParam(name = "aux") String aux);

}
