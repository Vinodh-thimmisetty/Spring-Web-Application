package com.vinodh.hibernate.validation.demo;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLMockingTest {

	public int testURL() {
		int responseCode = 400;
		HttpURLConnection urlConnection = null;
		URL url = null;
		String serviceUrl = null;
		try {
			serviceUrl = "https://www.ti.com";
			url = new URL(serviceUrl);
			urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setRequestMethod("GET");

			urlConnection.setDoInput(true);
			// Setting the Timeout's
			urlConnection.setConnectTimeout(5000);

			urlConnection.setReadTimeout(5000); // 5 sec

			responseCode = urlConnection.getResponseCode();
			
		} catch (Exception exception) {
			System.out.println(exception.getMessage());

		}

		return responseCode;
	}
}