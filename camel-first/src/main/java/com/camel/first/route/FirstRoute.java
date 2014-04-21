package com.camel.first.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;

public class FirstRoute extends RouteBuilder {

	public void configure() throws Exception {

		from("direct:entradaFirstRoute")
				.id("FirstRoute")

				/**
				 * Log del in.body
				 * 
				 * En este caso será el payload del request al web service, o
				 * sea el body del mensaje SOAP
				 */
				.log(LoggingLevel.INFO, "ws request: \n ${body}")

				/**
				 * Log del exchange
				 * 
				 * Podemos ver los header, y tambien el history de las
				 * diferentes etapas
				 */
				.to("log:com.camel.first.route?showAll=true&multiline=true")

				/**
				 * Transformacion con XSL
				 * 
				 * Transformo el in.body:
				 * 
				 * En este punto tengo en el in.body el payload del request de
				 * la llamada al WS "consumer".
				 * 
				 * Y para hacer la llamada al WS de Avalon, necesito en el
				 * in.body el payload del mensaje de request para Avalon.
				 * 
				 * Atencion:
				 * 
				 * Si la llamada la hago con CXF -> necesito setear en el header
				 * la propiedad "operationName", y en el in.body no se debe
				 * poner el tag root <obtenerContratoCuenta>. Y tener en cunta
				 * que en la configuracion del endpoint en el camel-context.xml
				 * debe estar el atributo wsdlURL.
				 * 
				 * Si la llamada la hago con spring-ws -> necesito que en el
				 * body se encuentre el tag root <obtenerContratoCuenta>
				 */
				.to("xslt:com/camel/templates/fromRequestCamelFirstToRequestAvalon.xsl")

				/**
				 * log after transform
				 */
				.log(LoggingLevel.INFO,
						"body despues del transform: \n ${body}")

				/**
				 * Atencion:
				 * "When the WS endpoint parses an incoming operation invocation in PAYLOAD mode, it automatically sets the operationName header to the name of the invoked operation."
				 * y tambien setea el operationNamespace.
				 * 
				 * Entonces lo borro del header, asi puedo llamar a otro WS con
				 * otro namespace.
				 */
				.removeHeader(CxfConstants.OPERATION_NAMESPACE)

				// .setHeader(
				// CxfConstants.OPERATION_NAMESPACE,
				// constant("http://ar/com/nextel/avalon/facade/AvalonWS.wsdl"))

				/**
				 * Llamada al Web Service de Avalon con CXF.
				 * 
				 * seteamos la operationName
				 */
				.setHeader(CxfConstants.OPERATION_NAME,
						constant("obtenerContratoCuenta"))

				/**
				 * Esta es una llamada "producer", es un cliente.
				 * 
				 * Llamamos al WS de Avalon
				 * 
				 * Tip: sin el prefijo "cxf:bean:" funciona.
				 */
				.to("avalonEndpoint").id("avalonWS")

				// .to("spring-ws:http://baslije12.nextel.com.ar:7778/avalonWS/AvalonWS")

				/**
				 * Ejemplo de cómo llamar a un process en el medio
				 * 
				 * Se puede debugear y ver el contenido del exchange
				 */
				.process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						exchange.getIn().setHeader("NesTest", "lala");
					}

				})

				/**
				 * Log del in.body
				 */
				.log(LoggingLevel.INFO, "${body}")

				/**
				 * Fin de este pedazo de workflow
				 * 
				 * Con el componente "direct" podemos terminar, y seguir en otra
				 * clase java
				 */
				.to("direct:endFirstRoute");
	}
}
