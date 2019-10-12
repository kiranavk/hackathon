package com.hcl.hackathon;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OpenBankController {
	
	@Autowired
	RestTemplate restTemplate;
	
	private String token;
	
	@GetMapping("/token")
	public String getJwtToken() throws URISyntaxException {
		if(token == null) {
			JsonToken jsonToken = getToken();
			token = jsonToken.getToken();
		}
		return token;
	}
	
	@GetMapping("/user")
	public String getCurrentUser(@RequestAttribute String token) throws URISyntaxException {
//		if(token == null) {
//			JsonToken jsonToken = getToken();
//			token = jsonToken.getToken();
//		}
		String url = "https://apisandbox.openbankproject.com/obp/v4.0.0/users/current";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "DirectLogin token="+token);
		
		HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return response.getBody();
	}
	
	@PostMapping(value = "/user", consumes = {"application/json"})
	public void createUser(@RequestBody User requestBody,
			@RequestAttribute String token) throws URISyntaxException {
//		if(token == null) {
//			JsonToken jsonToken = getToken();
//			token = jsonToken.getToken();
//		}
		String url = "https://apisandbox.openbankproject.com/obp/v4.0.0/users";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "DirectLogin token="+token);
		
		HttpEntity<User> entity = new HttpEntity<User>(requestBody, httpHeaders);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		System.out.println("Create user resp: "+response);
	}

	public JsonToken getToken() throws URISyntaxException {
//		RestTemplate restTemplate = new RestTemplate();
		String url = "https://apisandbox.openbankproject.com/my/logins/direct";
//		URI uri = new URI(url);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "DirectLogin username=avkkiran,password=ExpImp123$,consumer_key=2vwzqknzt1yozrzpm5fe31gku3wvnx1aattenaix");
		
//		restTemplate.getForEntity(uri, String.class);
		HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
		ResponseEntity<JsonToken> response = restTemplate.exchange(url, HttpMethod.POST, entity, JsonToken.class);
		return response.getBody();
	}
}
