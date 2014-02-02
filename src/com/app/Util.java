package com.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	private static ObjectMapper mapper = new ObjectMapper();

	// This method acts as a simple HTTP client that does the REST request and
	// returns the json response
	public static String sendGet(String url) {
		StringBuffer response = null;
		try {
			URL obj = new URL(url);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");

			// System.out.println("GET : " + url);
			int responseCode = con.getResponseCode();
			// System.out.println("Response Code : " + responseCode);

			if (responseCode != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ responseCode);
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		// System.out.println("Got response for "+url);
		return response != null ? response.toString() : null;

	}

	// Utility method to beautify JSON response
	public static void prettyPrint(Object obj) {
		// pretty print json

		try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(obj));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
