package com.vinodh.hibernate.validation.demo.test;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.vinodh.hibernate.validation.demo.URLMockingTest;

@RunWith(MockitoJUnitRunner.class)
public class MockURL {

	@InjectMocks
	URLMockingTest urlMockingTest;

	@Mock
	private URL url;

	@Mock
	private HttpURLConnection connection;

	@Test
	public void test1() throws Exception {

		Mockito.when(url.openConnection()).thenReturn(connection);

		Mockito.when(connection.getResponseCode()).thenReturn(200);
		Assert.assertEquals(200, urlMockingTest.testURL());

	}

}
