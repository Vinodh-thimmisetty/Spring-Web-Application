package com.vinodh.web.test;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.vinodh.web.BasicFilter;

public class FiltersTest {

	MockFilterChain mockFilterChain = new MockFilterChain();
	MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
	MockHttpServletRequest mockHttpServletRequest2 = new MockHttpServletRequest();
	MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
	MockFilterConfig mockFilterConfig = new MockFilterConfig();

	@Before
	public void setup() {
		mockHttpServletRequest.setParameter("name", "Vinodh");
		mockHttpServletRequest2.setParameter("name", "Yoo");
	}

	@Test
	public void testFilter() throws IOException, ServletException {

		BasicFilter basicFilter = new BasicFilter();
		basicFilter.init(mockFilterConfig);
		basicFilter.doFilter(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain);
		basicFilter.destroy();
		Assert.assertEquals(200,mockHttpServletResponse.getStatus());

	}

	@Test
	public void testFilter2() throws IOException, ServletException {

		BasicFilter basicFilter = new BasicFilter();
		basicFilter.doFilter(mockHttpServletRequest2, mockHttpServletResponse, mockFilterChain);
		Assert.assertEquals(200,mockHttpServletResponse.getStatus());

	}
}
