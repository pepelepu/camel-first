package com.camel.first.mock;

public class AvalonEndpoint {

	public String doSomething(String a) {
		return "<obtenerContratoCuentaReturn>" + "<cantActivos>0</cantActivos>"
				+ "<cantNoActivos>6</cantNoActivos>"
				+ "<cantSuspendidos>0</cantSuspendidos>"
				+ "<customerCode>2.13</customerCode>"
				+ "</obtenerContratoCuentaReturn>";
	}

}
