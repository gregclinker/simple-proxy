package com.santander;

import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ProxyController {

	private String redirectUrl = System.getProperty("REDIRECT_URL");

	@RequestMapping("/*")
	public String proxy(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		if (redirectUrl != null) {
			System.out.println("redirecting to " + redirectUrl);
			httpServletResponse.setHeader("Location", redirectUrl);
		} else {
			return getHeaders(request);
		}
		return null;
	}

	private String getHeaders(HttpServletRequest request) {
		StringBuilder stringBuilder = new StringBuilder();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String header = headerNames.nextElement();
			stringBuilder.append(header + "=" + request.getHeader(header) + "\n");
		}
		return stringBuilder.toString();
	}

}
