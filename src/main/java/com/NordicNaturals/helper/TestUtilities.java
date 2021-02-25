package com.NordicNaturals.helper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestUtilities extends TestBase {
	
	public static String message;

	public static String linkValidation(String url) {

		
		//Using HttpURLConnection class for sending and capturing the Http response code
		HttpURLConnection httpConn = null;
		
		
		// using By.tagName locator find all the links and Store them in a List
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		
		//Iterator to traverse through the List
		Iterator<WebElement> iterator = links.iterator();

		while (iterator.hasNext()) {

			url = iterator.next().getAttribute("href");

			try {
				
				
				httpConn = (HttpURLConnection) (new URL(url).openConnection());
				
				
				//set Request type as "HEAD" for returning only headers
				httpConn.setRequestMethod("HEAD");
				
				
				//invoking the connection
				httpConn.connect();
				

				int respCode = httpConn.getResponseCode();
				
				//	printing on console for reference
				String serverResponse = httpConn.getResponseMessage();
				System.out.println("Checking url " + url);
				System.out.println("Code from server is " + respCode);
				System.out.println("Response from server is " + serverResponse);

				//Based on response code check link status

				if (respCode == 200 || respCode == 301 || respCode == 302 || respCode == 403) {
					System.out.println("Success");
					message="Success";
					
				} else {
					System.out.println("Broken link " + url);
					message="Broken link";
					
				}
			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return message;

	}
	
	
	

}
