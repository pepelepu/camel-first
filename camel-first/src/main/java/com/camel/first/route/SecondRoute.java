package com.camel.first.route;

import org.apache.camel.builder.RouteBuilder;

public class SecondRoute extends RouteBuilder {

	public void configure() throws Exception {

		// inicio route
		from("direct:endFirstRoute").id("SecondRoute")
			// log (headers, history)
			.to("log:com.camel.first.route?showAll=true&multiline=true")

// @formatter:off

			.choice()
				.when().xpath("//cantActivos")
					.to("direct:endSecondRouteCase1")
				.otherwise()
					.to("direct:endSecondRouteCase2");

// @formatter:on

	}
}
