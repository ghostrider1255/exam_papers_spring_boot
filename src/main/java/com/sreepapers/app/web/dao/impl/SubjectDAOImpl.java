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
import org.springframework.web.client.RestTemplate;

import com.sreepapers.app.web.dao.SubjectDAO;
import com.sreepapers.app.web.model.Subject;
import com.sreepapers.app.web.utils.HelperUtils;

public class SubjectDAOImpl implements SubjectDAO{

	private static final Logger logger = LoggerFactory.getLogger(SubjectDAOImpl.class);
	
	public static final String SAVE_SUBJECT = "/subjectAction/saveSubject";
	public static final String GET_SUBJECTS ="/subjectAction/subjects";
	public static final String DELETE_SUBJECT ="/subjectAction/deleteSubject/{id}";
	public static final String GET_SUBJECT ="/subjectAction/subject/{id}";
	public static final String UPDATE_SUBJECT ="/subjectAction/updateSubject";
	
	@Value(PREFIX+REST_SERVER_HOST_URL+POSTFIX)
	private String webServerRestUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public void addSubject(Subject subject) {
		String url = webServerRestUrl+SAVE_SUBJECT;
		HttpEntity<Subject> requestEntity = HelperUtils.prepareRequestEntity(subject);
		
		ResponseEntity<Subject> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Subject.class);
		Subject newSubject = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Subject saved, Subject Details={}",newSubject);
		}else{
			logger.info("Subject saved successfully");
		}
	}

	@Override
	public void updateSubject(Subject subject) {
		String url = webServerRestUrl+UPDATE_SUBJECT;
		HttpEntity<Subject> requestEntity = HelperUtils.prepareRequestEntity(subject);
		
		ResponseEntity<Subject> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Subject.class);
		Subject newSubject = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Subject updated, Subject Details={}",newSubject);
		}else{
			logger.info("Subject updated successfully");
		}
	}

	@Override
	public List<Subject> listSubjects() {
		String url = webServerRestUrl+GET_SUBJECTS;
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<Subject[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Subject[].class);
		Subject[] newSubject = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Subject list, Subject length={}",newSubject.length);
		}else{
			logger.info("returning Subject list");
		}
		return Arrays.asList(newSubject);
	}

	@Override
	public Subject getSubjectById(long subjectId) {
		String url = webServerRestUrl+GET_SUBJECT;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<Subject> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Subject.class,subjectId);
		Subject newSubject = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("returning Subject object, Subject Details={}",newSubject);
		}
		else{
			logger.info("returning Subject object");
		}
		return newSubject;
	}

	@Override
	public void removeSubject(long id) {
		String url = webServerRestUrl+DELETE_SUBJECT;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		restTemplate.exchange(url, HttpMethod.DELETE,requestEntity,Subject.class,id);
	}
}
