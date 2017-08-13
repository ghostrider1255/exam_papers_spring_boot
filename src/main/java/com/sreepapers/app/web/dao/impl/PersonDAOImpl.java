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

import com.sreepapers.app.web.dao.PersonDAO;
import com.sreepapers.app.web.model.Person;
import com.sreepapers.app.web.utils.HelperUtils;

@Repository
public class PersonDAOImpl implements PersonDAO{
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	public static final String SAVE_PERSON = "savePerson";
	public static final String GET_PERSONS ="persons";
	public static final String DELETE_PERSON ="deletePerson/{id}";
	public static final String GET_PERSON ="person/{id}";
	public static final String UPDATE_PERSON ="updatePerson";
	
	@Value(PREFIX+REST_SERVER_HOST_URL+POSTFIX)
	private String webServerRestUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Override
	public void addPerson(Person person) {
		String url = webServerRestUrl+SAVE_PERSON;
		HttpEntity<Person> requestEntity = HelperUtils.prepareRequestEntity(person);
		
		ResponseEntity<Person> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Person.class);
		Person newPerson = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Person saved, Person Details={}",newPerson);
		}else{
			logger.info("Person saved successfully");
		}
	}
	
	@Override
	public void updatePerson(Person person) {
		String url = webServerRestUrl+UPDATE_PERSON;
		HttpEntity<Person> requestEntity = HelperUtils.prepareRequestEntity(person);
		
		ResponseEntity<Person> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Person.class);
		Person newPerson = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Person updated , Person Details={}",newPerson);
		}
		else{
			logger.info("Person updated successfully");
		}
	}

	@Override
	public List<Person> listPersons() {
		String url = webServerRestUrl+GET_PERSONS;
		
		ResponseEntity<Person[]> responseEntity = restTemplate.getForEntity(url, Person[].class);
		Person[] newPersonList = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("returning PersonList List of count, {}",newPersonList.length);
		}
		else{
			logger.info("returning PersonList list successfully");
		}
		return Arrays.asList(newPersonList);
	}

	@Override
	public Person getPersonById(int personId) {
		String url = webServerRestUrl+GET_PERSON;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<Person> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Person.class,personId);
		Person newPerson = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("returning Person object, Person Details={}",newPerson);
		}
		else{
			logger.info("returning Person object");
		}
		return newPerson;
	}

	@Override
	public void removePerson(int personId) {
		String url = webServerRestUrl+DELETE_PERSON;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		restTemplate.exchange(url, HttpMethod.DELETE,requestEntity,Person.class,personId);
	}

}
