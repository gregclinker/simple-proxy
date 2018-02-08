package com.greg;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
		
public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {

	private Map<String, String> headerMap = new HashMap<String, String>();

	public HeaderMapRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	public void addHeader(String name, String value) {
		headerMap.put(name, value);
	}

	@Override
	public String getHeader(String name) {
		String headerValue = super.getHeader(name);
		if (headerMap.containsKey(name)) {
			headerValue = headerMap.get(name);
		}
		return headerValue;
	}
	
	@Override
	public Enumeration<String> getHeaderNames() {
		List<String> list = Collections.list(super.getHeaderNames());
		list.addAll(headerMap.keySet());
		return new Vector(list).elements();
	}
}