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

import com.sreepapers.app.web.dao.ExamQuestionDAO;
import com.sreepapers.app.web.model.Exam;
import com.sreepapers.app.web.model.ExamQuestion;
import com.sreepapers.app.web.utils.HelperUtils;

public class ExamQuestionDAOImpl implements ExamQuestionDAO{

	private static final Logger logger = LoggerFactory.getLogger(ExamQuestionDAOImpl.class);
	
	public static final String SAVE_EXAM_QUESTION = "/examQuestionAction/addexamquestion";
	public static final String GET_EXAM_QUESTIONS="/examQuestionAction/examquestions";
	public static final String DELETE_EXAM_QUESTION="/examQuestionAction/deleteexamquestion/{id}";
	public static final String GET_EXAM_QUESTION="/examQuestionAction/examquestion/{id}";
	public static final String UPDATE_EXAM_QUESTION="/examQuestionAction/updateexamquestion/{id}";

	@Value(PREFIX+REST_SERVER_HOST_URL+POSTFIX)
	private String webServerRestUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public void addExamQuestion(ExamQuestion examQuestion) {
		String url = webServerRestUrl+SAVE_EXAM_QUESTION;
		HttpEntity<ExamQuestion> requestEntity = HelperUtils.prepareRequestEntity(examQuestion);
		
		ResponseEntity<ExamQuestion> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,ExamQuestion.class);
		ExamQuestion newExamQuestion = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("ExamQuestion saved, ExamQuestion Details={}",newExamQuestion);
		}else{
			logger.info("ExamQuestion saved successfully");
		}
	}

	@Override
	public void updateExamQuestion(ExamQuestion examQuestion) {
		String url = webServerRestUrl+UPDATE_EXAM_QUESTION;
		HttpEntity<ExamQuestion> requestEntity = HelperUtils.prepareRequestEntity(examQuestion);
		
		ResponseEntity<ExamQuestion> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,ExamQuestion.class);
		ExamQuestion newExamQuestion = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("ExamQuestion updated , Exam Details={}",newExamQuestion);
		}
		else{
			logger.info("ExamQuestion updated successfully");
		}
	}

	@Override
	public List<ExamQuestion> listExamQuestions() {
		String url = webServerRestUrl+GET_EXAM_QUESTIONS;
		
		ResponseEntity<ExamQuestion[]> responseEntity = restTemplate.getForEntity(url, ExamQuestion[].class);
		ExamQuestion[] newExamQuestionList = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("returning ExamQuestion List with length, {}",newExamQuestionList.length);
		}
		else{
			logger.info("ExamQuestion list returned");
		}
		return Arrays.asList(newExamQuestionList);
	}

	@Override
	public ExamQuestion getExamQuestionById(long examQuestionId) {
		String url = webServerRestUrl+GET_EXAM_QUESTION;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<ExamQuestion> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,ExamQuestion.class,examQuestionId);
		ExamQuestion newExamQuestion = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("ExamQuestion returning successfully, ExamQuestion Details={}",newExamQuestion);
		}
		else{
			logger.info("ExamQuestion returning");
		}
		return newExamQuestion;
	}

	@Override
	public void removeExamQuestion(long examQuestionId) {
		String url = webServerRestUrl+DELETE_EXAM_QUESTION;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		restTemplate.exchange(url, HttpMethod.DELETE,requestEntity,Exam.class,examQuestionId);
		if(logger.isDebugEnabled()){
			logger.debug("deleting ExamQustion record, examQuestionID:{}",examQuestionId);
		}
		else{
			logger.info("deleted ExamQuestion record");
		}
	}
}
