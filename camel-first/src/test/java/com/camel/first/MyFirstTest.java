package com.camel.first;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyFirstTest extends CamelSpringTestSupport {

	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	@Produce(uri = "direct:start")
	protected ProducerTemplate template;

	@Override
	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("camel-context-mock.xml");
	}

	@Test
	public void testFirst() throws Exception {

		String expectedBody = "<obtenerContratoCuentaReturn><cantActivos>0</cantActivos><cantNoActivos>6</cantNoActivos><cantSuspendidos>0</cantSuspendidos><customerCode>2.13</customerCode></obtenerContratoCuentaReturn>";

		resultEndpoint.expectedBodiesReceived(expectedBody);

		template.sendBody(expectedBody);
		// template.sendBodyAndHeader(expectedBody, "foo", "bar");

		resultEndpoint.assertIsSatisfied();
	}
}
