package com.gatech.userreg;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.servlet.http.*;
import java.security.SecureRandom;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;


public class UserRegistrationServletTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	private final String USER_AGENT = "Mozilla/5.0";
	static String correctEmail;
	static String correctName;
	static String correctPassword;
	static String incorrectEmail;
	static String incorrectName;
	static String incorrectPassword;
	static String randomEmail;
	static String charset;
	static String urlStr;
	
	
	
	@BeforeClass
	public static void setupHttp(){
		correctEmail = "an9@gmail.com";
		correctName = "Riet";
		correctPassword = "bbt2rr33";
		charset = "UTF-8";
		urlStr = "http://1-dot-thinking-return-161419.appspot.com/userregistration";
		incorrectEmail = "anu9@gmail.com";
		incorrectName = "Riett";
		incorrectPassword = "bbt2rr33pp";
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(6);
		for( int i = 0; i < 6; i++ ) 
	  		sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		randomEmail = sb.toString() + "@gmail.com";
	}
	
	@Test
	public void successfulGet() {
		try{
			
			String emailquery = String.format("email=%s&name=%s&password=%s", 
	                   URLEncoder.encode(correctEmail, charset),URLEncoder.encode(correctName, charset),URLEncoder.encode(correctPassword, charset));
			
			URL url = new URL(urlStr+"?"+emailquery);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Character-Encoding", "UTF-8");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept-Charset", charset);
			InputStream response = conn.getInputStream();
			String responseBody;
			try (Scanner scanner = new Scanner(response)) {
			    responseBody = scanner.useDelimiter("\\A").next();
			}
			Gson g = new Gson();
			UserError useErr = g.fromJson(responseBody, UserError.class);
			assertEquals(200, useErr.getCode());
		
		} catch (Exception ex){
			
			assertTrue(false);
		}
	}
	
	@Test
	public void failedGetIncorrectEmail() {
		try{
			
			
			String emailquery = String.format("email=%s&name=%s&password=%s", 
	                   URLEncoder.encode(incorrectEmail, charset),URLEncoder.encode(correctName, charset),URLEncoder.encode(incorrectPassword, charset));
			
			URL url = new URL(urlStr+"?"+emailquery);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Character-Encoding", "UTF-8");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept-Charset", charset);
			InputStream response = conn.getInputStream();
			String responseBody;
			try (Scanner scanner = new Scanner(response)) {
			    responseBody = scanner.useDelimiter("\\A").next();
			}
			Gson g = new Gson();
			UserError useErr = g.fromJson(responseBody, UserError.class);
			assertEquals(404, useErr.getCode());
		
		} catch (Exception ex){
			
			assertTrue(false);
		}
	}
	
	
	@Test
	public void failedGetIncorrectPassword(){
		try{
			String emailquery = String.format("email=%s&name=%s&password=%s", 
	                   URLEncoder.encode(correctEmail, charset),URLEncoder.encode(correctName, charset),URLEncoder.encode(correctPassword, charset));
			
			URL url = new URL(urlStr+"?"+emailquery);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Character-Encoding", "UTF-8");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept-Charset", charset);
			InputStream response = conn.getInputStream();
			String responseBody;
			try (Scanner scanner = new Scanner(response)) {
			    responseBody = scanner.useDelimiter("\\A").next();
			}
			Gson g = new Gson();
			UserError useErr = g.fromJson(responseBody, UserError.class);
			assertEquals(404, useErr.getCode());
		
		} catch (Exception ex){
			
			assertTrue(false);
		}
	}
	
	public void failedGetIncorrectName(){
		try{
			String emailquery = String.format("email=%s&name=%s&password=%s", 
	                   URLEncoder.encode(correctEmail, charset),URLEncoder.encode(incorrectName, charset),URLEncoder.encode(correctPassword, charset));
			
			URL url = new URL(urlStr+"?"+emailquery);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Character-Encoding", "UTF-8");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept-Charset", charset);
			InputStream response = conn.getInputStream();
			String responseBody;
			try (Scanner scanner = new Scanner(response)) {
			    responseBody = scanner.useDelimiter("\\A").next();
			}
			Gson g = new Gson();
			UserError useErr = g.fromJson(responseBody, UserError.class);
			assertEquals(404, useErr.getCode());
		
		} catch (Exception ex){
			
			assertTrue(false);
		}
	}
	
	/*
	@Test
	public void successfulPost(){
		User usr = new User("Ron", "Ron@gmail", "12ab");
		try{
			HttpServletRequest mockReq = mock(HttpServletRequest.class);
			HttpServletResponse mockResp = mock(HttpServletResponse.class);
			when(mockReq.getParameter("email")).thenReturn("Ron@gmail.com");
			when(mockReq.getParameter("password")).thenReturn("1234");
			when(mockReq.getParameter("name")).thenReturn("Ron");
			when(mockReq.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8888/userregistration")).toString();
			UserRegistrationServlet servlet = new UserRegistrationServlet();
			servlet.doPost(mockReq, mockResp);
			User user = DBUtilsUser.getUser(usr.getName(),usr.getEmail(), usr.getPassword());
			if(user != null){
				// user exists, pass test. What about persistance?
			}
		} catch (Exception ex){
			//Pass, exception was caught. Narrow down to a specific exception in next version?
		}
	}*/
	
	@Test
	public void failDupeEmailPost(){
		try{
			String emailquery = String.format("email=%s&name=%s&password=%s", 
	                   URLEncoder.encode(correctEmail, charset),URLEncoder.encode(correctName, charset),URLEncoder.encode(correctPassword, charset));
			
			URL url = new URL(urlStr+"?"+emailquery);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Character-Encoding", "UTF-8");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept-Charset", charset);
			InputStream response = conn.getInputStream();
			String responseBody;
			try (Scanner scanner = new Scanner(response)) {
			    responseBody = scanner.useDelimiter("\\A").next();
			}
			Gson g = new Gson();
			UserError useErr = g.fromJson(responseBody, UserError.class);
			assertEquals(404, useErr.getCode());
		
		} catch (Exception ex){

			assertTrue(false);
		}
	}
	
	@Test
	public void failNullPasswordPost(){
		try{
			String emailquery = String.format("email=%s&name=%s", 
	                   URLEncoder.encode(correctEmail, charset),URLEncoder.encode(correctName, charset));
			
			URL url = new URL(urlStr+"?"+emailquery);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Character-Encoding", "UTF-8");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept-Charset", charset);
			InputStream response = conn.getInputStream();
			String responseBody;
			try (Scanner scanner = new Scanner(response)) {
			    responseBody = scanner.useDelimiter("\\A").next();
			}
			Gson g = new Gson();
			UserError useErr = g.fromJson(responseBody, UserError.class);
			assertEquals(402, useErr.getCode());
		
		} catch (Exception ex){
			
			assertTrue(false);
		}
	}
	
	@Test
	public void failNullNamePost(){
		try{
			String emailquery = String.format("email=%s&password=%s", 
	                   URLEncoder.encode(correctEmail, charset),URLEncoder.encode(correctPassword, charset));
			
			URL url = new URL(urlStr+"?"+emailquery);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Character-Encoding", "UTF-8");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept-Charset", charset);
			InputStream response = conn.getInputStream();
			String responseBody;
			try (Scanner scanner = new Scanner(response)) {
			    responseBody = scanner.useDelimiter("\\A").next();
			}
			Gson g = new Gson();
			UserError useErr = g.fromJson(responseBody, UserError.class);
			assertEquals(401, useErr.getCode());
		
		} catch (Exception ex){
			
			assertTrue(false);
		}
	}
	
	@Test
	public void failNullEmailPost(){
		try{
			String emailquery = String.format("name=%s&password=%s", 
	                   URLEncoder.encode(correctName, charset),URLEncoder.encode(correctPassword, charset));
			
			URL url = new URL(urlStr+"?"+emailquery);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Character-Encoding", "UTF-8");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept-Charset", charset);
			InputStream response = conn.getInputStream();
			String responseBody;
			try (Scanner scanner = new Scanner(response)) {
			    responseBody = scanner.useDelimiter("\\A").next();
			}
			Gson g = new Gson();
			UserError useErr = g.fromJson(responseBody, UserError.class);
			assertEquals(403, useErr.getCode());
		
		} catch (Exception ex){
			
			assertTrue(false);
		}
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		try{
			String emailquery = String.format("email=%s&name=%s&password=%s", 
	                   URLEncoder.encode(randomEmail, charset),URLEncoder.encode(incorrectName, charset),URLEncoder.encode(correctPassword, charset));
			
			URL url = new URL(urlStr+"?"+emailquery);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Character-Encoding", "UTF-8");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept-Charset", charset);
			InputStream response = conn.getInputStream();
			String responseBody;
			try (Scanner scanner = new Scanner(response)) {
			    responseBody = scanner.useDelimiter("\\A").next();
			}
			Gson g = new Gson();
			UserError useErr = g.fromJson(responseBody, UserError.class);
			assertEquals(200, useErr.getCode());
		
		} catch (Exception ex){
			
			assertTrue(false);
		}
	}

}
