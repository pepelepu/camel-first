package com.camel.first.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class FirstRoute extends RouteBuilder {

	public void configure() throws Exception {

		// inicio route, entrada por archivo
		from("file:src/main/resources/msg?noop=true")
				.id("FirstRoute")

				// log del filename y body
				.log(LoggingLevel.INFO,
						"Leyendo el archivo ${file:name} \n ${body}")

				// llamada al WebService CXF
				.setHeader("operationName", constant("obtenerContratoCuenta"))
				.to("cxf:bean:avalonEndpoint").id("avalonWS")
				// con esta forma el in.body es solo
				// <customerCode>2.11</customerCode>
				// ojo, esta forma de llamada necesita el
				// wsdlURL="src/main/resources/META-INF/AvalonWS.wsdl"

				// la misma llamada se puede hacer con
				// .to("cxf:bean:avalonEndpoint").id("avalonWS")
				// y poniendo en el in.body el
				// <obtenerContratoCuenta>...</obtenerContratoCuenta>

				// process ejemplo
				.process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						exchange.getIn().setHeader("NesTest", "lala");
					}

				})

				// log del body
				.log(LoggingLevel.INFO, "${body}")

				// fin de este pedazo de workflow
				.to("direct:endFirstRoute");
	}
}
