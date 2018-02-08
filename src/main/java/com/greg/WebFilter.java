package com.greg;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebFilter implements Filter {

	@Autowired
	private StandardEnvironment environment;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(req);
		showHeaders("Before", requestWrapper);
		addHaders(requestWrapper);
		showHeaders("After", requestWrapper);
		filterChain.doFilter(requestWrapper, response);
	}

	private void addHaders(HeaderMapRequestWrapper requestWrapper) {
		Properties headerProperties = (Properties) environment.getPropertySources()
				.get("class path resource [header.properties]").getSource();
		for (Object key : headerProperties.keySet()) {
			String value = (String) headerProperties.get(key);
			requestWrapper.addHeader((String) key, value);
		}
	}

	private void showHeaders(String label, HeaderMapRequestWrapper requestWrapper) {

		System.out.println("========== " + label + " Filter ==========");
		Enumeration<String> headerNames = requestWrapper.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String header = headerNames.nextElement();
			System.out.println(header + "=" + requestWrapper.getHeader(header));
		}
		System.out.println("\n");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}