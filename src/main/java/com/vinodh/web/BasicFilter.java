package com.vinodh.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class BasicFilter implements Filter {

	public void destroy() {
		// Nothing to implement

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {

		String requestName = request.getParameter("name");
		if (!"Vinodh".equalsIgnoreCase(requestName)) {
			filter.doFilter(request, response);
		} else {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.getWriter().write("Vinodh is blocked from accessing the page");
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// Nothing to implement

	}

}
