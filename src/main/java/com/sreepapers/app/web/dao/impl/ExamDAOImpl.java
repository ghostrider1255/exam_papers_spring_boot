package com.sreepapers.app.web.dao.impl;

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

import com.sreepapers.app.web.dao.ExamDAO;
import com.sreepapers.app.web.model.Exam;
import com.sreepapers.app.web.utils.HelperUtils;

import static com.sreepapers.app.web.utils.properties.HelperProperties.PREFIX;
import static com.sreepapers.app.web.utils.properties.HelperProperties.POSTFIX;
import static com.sreepapers.app.web.utils.properties.HelperProperties.REST_SERVER_HOST_URL;

public class ExamDAOImpl implements ExamDAO{

	private static final Logger logger = LoggerFactory.getLogger(ExamDAOImpl.class);
	
	public static final String SAVE_EXAM = "/examAction/saveExam";
	public static final String GET_EXAMS="/examAction/exams";
	public static final String DELETE_EXAM="/examAction/deleteExam/{id}";
	public static final String GET_EXAM="/examAction/exam/{id}";
	public static final String UPDATE_EXAM="/examAction/updateExam";
	
	@Value(PREFIX+REST_SERVER_HOST_URL+POSTFIX)
	private String webServerRestUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public void addExam(Exam exam) {
		
		String url = webServerRestUrl+SAVE_EXAM;
		HttpEntity<Exam> requestEntity = HelperUtils.prepareRequestEntity(exam);
		
		ResponseEntity<Exam> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Exam.class);
		Exam newExam = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Exam saved, Exam Details={}",newExam);
		}else{
			logger.info("Exam saved successfully");
		}
	}

	@Override
	public void updateExam(Exam exam) {
		String url = webServerRestUrl+UPDATE_EXAM;
		HttpEntity<Exam> requestEntity = HelperUtils.prepareRequestEntity(exam);
		
		ResponseEntity<Exam> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Exam.class);
		Exam newExam = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Exam saved , Exam Details={}",newExam);
		}
		else{
			logger.info("Exam saved successfully");
		}
	}

	@Override
	public List<Exam> listExams() {
		String url = webServerRestUrl+GET_EXAMS;
		
		ResponseEntity<Exam[]> responseEntity = restTemplate.getForEntity(url, Exam[].class);
		Exam[] newExams = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("returning exams List, {}",newExams.length);
		}
		else{
			logger.info("Exam saved successfully");
		}
		return Arrays.asList(newExams);
	}

	@Override
	public Exam getExamById(long examId) {
		String url = webServerRestUrl+GET_EXAM;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<Exam> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Exam.class,examId);
		Exam newExam = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("Exam saved successfully, Exam Details={}",newExam);
		}
		else{
			logger.info("Exam saved successfully");
		}
		return newExam;
	}

	@Override
	public void removeExam(long examId) {
		String url = webServerRestUrl+DELETE_EXAM;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(header);
		restTemplate.exchange(url, HttpMethod.DELETE,requestEntity,Exam.class,examId);
	}
}
