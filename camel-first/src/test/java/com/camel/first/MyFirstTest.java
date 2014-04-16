package com.camel.first;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyFirstTest extends CamelSpringTestSupport {

	@Override
	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("camel-context-mock.xml");
	}

	@Test
	public void testFirst() {
		template.sendBody("codeFirstEndpoint", "aaaaaaaaaaaaaaa");
	}
}
