package com.app;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MainClass {

	private static ObjectMapper mapper = new ObjectMapper();
	public static void main(String[] args) {
		String url = "http://www.google.com/dictionary/json?callback=a&sl=en&tl=en&q=java";
		String response = Util.sendGet(url);
		String json = response.substring(response.indexOf("{"), response.lastIndexOf("}")+1);
		json=json.replace("\\","");
		System.out.println(json);
		Object jsonObj = null;
		try {
			jsonObj = mapper.readValue(json, Object.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Util.prettyPrint(jsonObj);
	}
}
