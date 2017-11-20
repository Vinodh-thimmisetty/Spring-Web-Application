package com.vinodh.hibernate.validation.demo.test;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.icegreen.greenmail.mail.MalformedAddressException;
import com.vinodh.hibernate.validation.demo.URLMockingTest;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { Object.class })
public class TestURL {

	@InjectMocks
	URLMockingTest urlMockingTest;

	private URL url;
	private HttpURLConnection connection;

	@Before
	public void setup() throws Exception {
		url = PowerMockito.mock(URL.class);
		connection = PowerMockito.mock(HttpURLConnection.class);
		PowerMockito.whenNew(URL.class).withParameterTypes(String.class).withArguments(Mockito.anyString())
				.thenReturn(url);

	}

	@Test
	public void test1() throws Exception {
		Mockito.when(url.openConnection()).thenReturn(connection);
		Mockito.when(connection.getResponseCode()).thenReturn(200);
		Assert.assertEquals(200, urlMockingTest.testURL());

	}

	@Test(expected = Exception.class)
	public void test11() throws Exception {

		Mockito.when(url.openConnection()).thenThrow(new MalformedAddressException());

	}

}