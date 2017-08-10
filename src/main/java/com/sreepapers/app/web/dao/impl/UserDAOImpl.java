package com.sreepapers.app.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.sreepapers.app.web.dao.UserDAO;
import com.sreepapers.app.web.model.User;

@Repository
public class UserDAOImpl implements UserDAO{

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addUser(User user) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		String url = "http://localhost:8080/myrestserver/userAction/user/saveuser";
		HttpEntity<String> requestEntity = new HttpEntity<String>(header);
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,User.class);
		User addedUser = responseEntity.getBody();
		logger.info("Person saved successfully, User Details="+addedUser);
	}

	@Override
	public void updateUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
		logger.info("User updated successfully, User Details="+user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> usersList = session.createQuery("from User").list();
		for(User user : usersList){
			logger.info("Users List::"+user);
		}
		return usersList;
	}

	@Override
	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User user = (User) session.load(User.class, new Integer(id));
		logger.info("User loaded successfully, User details="+user);
		return user;
	}

	@Override
	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, new Integer(id));
		if(null != user){
			session.delete(user);
		}
		logger.info("User deleted successfully, User details="+user);
		
	}

}
