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

import com.sreepapers.app.web.dao.QuestionDAO;
import com.sreepapers.app.web.model.Question;
import com.sreepapers.app.web.utils.HelperUtils;

public class QuestionDAOImpl implements QuestionDAO{

	private static final Logger logger = LoggerFactory.getLogger(QuestionDAOImpl.class);
	
	public static final String SAVE_QUESTION = "/questionAction/saveQuestion";
	public static final String GET_QUESTIONS_LIST ="/questionAction/questions";
	public static final String DELETE_QUESTION ="/questionAction/deleteQuestion/{id}";
	public static final String GET_QUESTION ="/questionAction/question/{id}";
	public static final String UPDATE_QUESTION ="/questionAction/updateQuestion/{id}";
	public static final String GET_QUESTIONS_BY_SUBJECT="/questionAction/questions/{subjectId}";
	
	@Value(PREFIX+REST_SERVER_HOST_URL+POSTFIX)
	private String webServerRestUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public void addQuestion(Question question) {
		String url = webServerRestUrl+SAVE_QUESTION;
		HttpEntity<Question> requestEntity = HelperUtils.prepareRequestEntity(question);
		
		ResponseEntity<Question> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Question.class);
		Question newQuestion = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Question saved, Question Details={}",newQuestion);
		}else{
			logger.info("Question saved successfully");
		}
	}

	@Override
	public void updateQuestion(Question question) {
		String url = webServerRestUrl+UPDATE_QUESTION;
		HttpEntity<Question> requestEntity = HelperUtils.prepareRequestEntity(question);
		
		ResponseEntity<Question> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Question.class);
		Question newQuestion = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Question updated, Question Details={}",newQuestion);
		}else{
			logger.info("Question updated successfully");
		}
	}

	@Override
	public List<Question> listQuestions() {
		String url = webServerRestUrl+GET_QUESTIONS_LIST;
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<Question[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Question[].class);
		Question[] newQuestion = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Question list, Question length={}",newQuestion.length);
		}else{
			logger.info("returning Questions list");
		}
		return Arrays.asList(newQuestion);
	}

	@Override
	public Question getQuestionById(long questionId) {
		String url = webServerRestUrl+GET_QUESTION;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<Question> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Question.class,questionId);
		Question newQuestion = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Question saved successfully, Question Details={}",newQuestion);
		}
		else{
			logger.info("Question saved successfully");
		}
		return newQuestion;
	}

	@Override
	public void removeQuestion(long questionId) {
		String url = webServerRestUrl+DELETE_QUESTION;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		restTemplate.exchange(url, HttpMethod.DELETE,requestEntity,Question.class,questionId);
	}
	
	public List<Question> filterBySubject(long questionId){
		String url = webServerRestUrl+GET_QUESTIONS_BY_SUBJECT;
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<Question[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Question[].class);
		Question[] newQuestion = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Question list, Question length={}",newQuestion.length);
		}else{
			logger.info("returning Questions list");
		}
		return Arrays.asList(newQuestion);
	}
}
