package com.camel.first.bean;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.converter.jaxp.XmlConverter;
import org.w3c.dom.Document;

public class WithParamsBean {

	public void doSomething(Exchange exchange,
			@Header("customerCode") String customerCode) {

		XmlConverter converter = new XmlConverter();
		Document d = null;

		try {
			d = converter.toDOMDocument("<customerCode>" + customerCode
					+ "</customerCode>");
		} catch (Exception e) {
			// TODO: handle exception
		}

		exchange.getIn().setBody(d);
	}

}
