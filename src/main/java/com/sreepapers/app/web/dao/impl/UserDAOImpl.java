package com.sreepapers.app.web.dao.impl;

import static com.sreepapers.app.web.utils.properties.HelperProperties.POSTFIX;
import static com.sreepapers.app.web.utils.properties.HelperProperties.PREFIX;
import static com.sreepapers.app.web.utils.properties.HelperProperties.REST_SERVER_HOST_URL;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.sreepapers.app.web.dao.UserDAO;
import com.sreepapers.app.web.model.User;
import com.sreepapers.app.web.utils.HelperUtils;

@Repository
public class UserDAOImpl implements UserDAO{

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	public static final String SAVE_USER = "/userAction/user/saveuser";
	public static final String GET_USERS_LIST="/userAction/users";
	public static final String DELETE_USER="/userAction/deleteuser/{id}";
	public static final String GET_USER="/userAction/user/{id}";
	public static final String UPDATE_USER="/userAction/updateuser/{id}";
	
	@Value(PREFIX+REST_SERVER_HOST_URL+POSTFIX)
	private String webServerRestUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Override
	public void addUser(User user) {
		String url = webServerRestUrl+SAVE_USER;
		HttpEntity<User> requestEntity = HelperUtils.prepareRequestEntity(user);
		
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,User.class);
		User newUser = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("User saved, User Details={}",newUser);
		}else{
			logger.info("User saved successfully");
		}
	}

	@Override
	public void updateUser(User user) {
		String url = webServerRestUrl+UPDATE_USER;
		HttpEntity<User> requestEntity = HelperUtils.prepareRequestEntity(user);
		
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,User.class);
		User newUser = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("User updated, User Details={}",newUser);
		}else{
			logger.info("User updated successfully");
		}
	}

	@Override
	public List<User> listUsers() {
		String url = webServerRestUrl+GET_USERS_LIST;
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<User[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,User[].class);
		User[] newUser = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("User list, User length={}",newUser.length);
		}else{
			logger.info("returning User list");
		}
		return Arrays.asList(newUser);
	}

	@Override
	public User getUserById(int userId) {
		String url = webServerRestUrl+GET_USER;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,User.class,userId);
		User newUser = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("returning User object, User Details={}",newUser);
		}
		else{
			logger.info("returning User object");
		}
		return newUser;
	}

	@Override
	public void removeUser(int userId) {
		String url = webServerRestUrl+DELETE_USER;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		restTemplate.exchange(url, HttpMethod.DELETE,requestEntity,User.class,userId);
	}
}
