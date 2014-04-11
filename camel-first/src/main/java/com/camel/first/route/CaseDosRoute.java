package com.camel.first.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class CaseDosRoute extends RouteBuilder {

	public void configure() throws Exception {

		// inicio route
		from("direct:endSecondRouteCase2").id("CaseDosRoute")

		// log
				.log(LoggingLevel.INFO, "estamos en el caso dos");
	}
}
