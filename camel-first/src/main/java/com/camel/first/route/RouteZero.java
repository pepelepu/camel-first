package com.camel.first.route;

import org.apache.camel.builder.RouteBuilder;

public class RouteZero extends RouteBuilder {

	public void configure() throws Exception {

		/**
		 * ESTE ES EL ROUTE ZERO
		 * 
		 * Este route solo incluye el punto de entrada. Este route no esta
		 * incluido en el TEST. No agregar transformaciones, ni beans ni
		 * process, porque no serán testeados por el test.
		 * 
		 * Para mockear el WS consumer, para el testing, la mejor manera que
		 * encontré es llamar directo al "direct:entradaFirstRoute", sin pasar
		 * por aca.
		 */

		// inicio route, entrada por archivo
		// from("file:src/main/resources/msg2?noop=true")
		// log del filename y body
		// .log(LoggingLevel.INFO,
		// "Leyendo el archivo ${file:name} \n ${body}")

		// from("soapMessageEndpoint")

		/**
		 * Punto de entrada del route.
		 * 
		 * Es un web service "consumer" que se expone, realiza todas las
		 * operaciones del route, y responde.
		 * 
		 * Tip: sin el prefijo "cxf:bean:" funciona.
		 */
		from("codeFirstEndpoint").id("RouteZero")
				.to("direct:entradaFirstRoute");
	}
}
