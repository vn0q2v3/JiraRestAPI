package com.walmart.JiraRest.jirarestdb.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import lombok.Value;

@Service
public class JiraService {
	public String apiCall() {
		
		String loginResponse = "";
		String jSessionID = "";
		String jsonData = "";
		String baseURL = "https://jira.walmart.com/rest/";
		String loginURL = "auth/1/session";
		
		
		String loginUserName = "svc_crmcomms";
		String loginPassWord = "";
		boolean errorsOccurred = false;

		if (!errorsOccurred) {
			loginResponse = loginToJira(baseURL, loginURL, loginUserName, loginPassWord);
			if (loginResponse == "ERROR") {
				errorsOccurred = true;
			}
		}
		if (!errorsOccurred) {
			jSessionID = parseJSessionID(loginResponse);
			if (jSessionID == "ERROR") {
				errorsOccurred = true;
			}
		}
		
		if (!errorsOccurred) {
			System.out.println("SUCCESS");
		} else {
			System.out.println("FAILURE");
		}
		return jSessionID;
	}

	public static String loginToJira(String baseURL, String loginURL, String loginUsername, String loginPassword) {
		String loginResponse = "";
		URL url = null;
		HttpURLConnection conn = null;
		String input = "";
		OutputStream os = null;
		BufferedReader br = null;
		String output = null;
		try {
			// Create URL object
			url = new URL(baseURL + loginURL);
			// Use the URL to create connection
			conn = (HttpURLConnection) url.openConnection();

			// Set properties
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			// Create JSON post data
			input = "{\"username\":\"" + loginUsername + "\", \"password\":\"" + loginPassword + "\"}";

			// Send our request
			os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			// Handle our response
			if (conn.getResponseCode() == 200) {
				br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					loginResponse += output;
				}
				conn.disconnect();
			}
		} catch (Exception ex) {
			System.out.println("Error in loginToJira: " + ex.getMessage());
			loginResponse = "ERROR";
		}
		System.out.println("\nloginResponse:");
		System.out.println(loginResponse);
		return loginResponse;

	}

	public static String parseJSessionID(String input) {
		String jSessionID = "";
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(input);
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject sessionJsonObj = (JSONObject) jsonObject.get("session");
			jSessionID = (String) sessionJsonObj.get("value");
		} catch (Exception ex) {
			System.out.println("Error in parseJSessionID: " + ex.getMessage());
			jSessionID = "ERROR";
		}
		System.out.println("\njSessionID:");
		System.out.println(jSessionID);
		return jSessionID;
	}

	public String getJsonData(String jSessionID, String key) {
		String jsonData = "";
		try {
			
			URL url = new URL("https://jira.walmart.com/rest/api/2/search?jql=project="+key+"&maxResults=50");//"&maxResults=100&fields=summary,description,assignee,reporter"

			String cookie = "JSESSIONID=" + jSessionID;
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Cookie", cookie);
			if (conn.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String output = "";
				while ((output = br.readLine()) != null) {
					jsonData += output;
				}
				conn.disconnect();
			}
		} catch (Exception ex) {
			System.out.println("Error in getJsonData: " + ex.getMessage());
			jsonData = "ERROR";
		}

		System.out.println("\njsonData:");
		System.out.println(jsonData);
		int x = jsonData.indexOf('[');
//    	for (int i = 0; i < jsonData.length(); i++) {
//			if (jsonData.charAt(i)=='[') {
//				x = i;
//				break;
//			}
//		}
    	
    	System.out.println("find x done");
    	StringBuilder sb = new StringBuilder();
    	for (int i = x; i < jsonData.length(); i++) {
			sb.append(jsonData.charAt(i));
		}
    	String newStr = sb.toString();
    	System.out.println("***");
    	System.out.println(sb);
		return newStr;

	}
	
	public String getJsonIssue(String jSessionID, String key) {
		String jsonData = "";
		try {
			
			URL url = new URL("https://jira.walmart.com/rest/api/2/search?jql=issue="+key+"&fields=summary,description,assignee,reporter");

			String cookie = "JSESSIONID=" + jSessionID;
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Cookie", cookie);
			if (conn.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String output = "";
				while ((output = br.readLine()) != null) {
					jsonData += output;
				}
				conn.disconnect();
			}
		} catch (Exception ex) {
			System.out.println("Error in getJsonData: " + ex.getMessage());
			jsonData = "ERROR";
		}

		System.out.println("\njsonData:");
		System.out.println(jsonData);
		return jsonData;

	}
	

}
