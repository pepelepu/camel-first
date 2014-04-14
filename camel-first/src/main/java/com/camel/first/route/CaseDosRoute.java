package com.camel.first.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import com.camel.first.bean.WithParamsBean;

public class CaseDosRoute extends RouteBuilder {

	public void configure() throws Exception {

		// inicio route
		from("direct:endSecondRouteCase2")
				.id("CaseDosRoute")

				// preparando la llamada al WebService CXF
				.setHeader("operationName", constant("obtenerContratoCuenta"))
				.setHeader("customerCode", constant("2.13"))
				.bean(WithParamsBean.class)
				// llamada al WS
				.to("cxf:bean:avalonEndpoint").id("avalonWS-withParams")

				// log
				.log(LoggingLevel.INFO, "estamos en el caso dos")

				// log del body
				.log(LoggingLevel.INFO, "${body}")

				// transformation
				.setHeader("paramSample", constant("ejemploParamenXSL"))
				.to("xslt:com/camel/templates/transform.xsl")

				// log del body
				.log(LoggingLevel.INFO, "${body}")

				// llamada al WS
				.to("cxf:bean:avalonEndpoint").id("avalonWS-withXSL")

				// log del body
				.log(LoggingLevel.INFO, "${body}");
	}
}
