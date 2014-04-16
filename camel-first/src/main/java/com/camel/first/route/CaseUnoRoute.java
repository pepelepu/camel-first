package com.camel.first.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import com.camel.first.bean.FromAtoB;

public class CaseUnoRoute extends RouteBuilder {

	public void configure() throws Exception {

		// inicio route
		from("direct:endSecondRouteCase1").id("CaseUnoRoute")

		// log
				.log(LoggingLevel.INFO, "estamos en el caso uno")

				// llamo a un bean (tres formas de llamar a un bean)

				// .to("bean:fromAtoB")
				// .beanRef("fromAtoB")
				.bean(FromAtoB.class) // esta ultima no necesita declarar nada
										// en el camel-context.xml

				// lamada al WS
				.to("spring-ws:http://baslije12.nextel.com.ar:7778/avalonWS/AvalonWS")

				// log del body
				.log(LoggingLevel.INFO, "${body}");

	}
}
