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

import static com.sreepapers.app.web.utils.properties.HelperProperties.PREFIX;
import static com.sreepapers.app.web.utils.properties.HelperProperties.POSTFIX;
import static com.sreepapers.app.web.utils.properties.HelperProperties.REST_SERVER_HOST_URL;

public class ExamDAOImpl implements ExamDAO{

	private static final Logger logger = LoggerFactory.getLogger(ExamDAOImpl.class);
	
	@Value(PREFIX+REST_SERVER_HOST_URL+POSTFIX)
	private String WEB_SERVER_REST_URL;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private HttpEntity<String> prepareRequestEntity(){
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(header);
		return requestEntity;
	}
	
	@Override
	public void addExam(Exam exam) {
		
		String url = WEB_SERVER_REST_URL+"/examAction/save_exam";
		HttpEntity<String> requestEntity = prepareRequestEntity();
		
		ResponseEntity<Exam> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Exam.class);
		Exam newExam = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.info("Exam saved successfully, Exam Details="+newExam);
		}
		logger.info("Exam saved successfully");
	}

	@Override
	public void updateExam(Exam exam) {
		String url = WEB_SERVER_REST_URL+"/examAction/updateExam";
		HttpEntity<String> requestEntity = prepareRequestEntity();
		
		ResponseEntity<Exam> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Exam.class);
		Exam newExam = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.info("Exam saved successfully, Exam Details="+newExam);
		}
		logger.info("Exam saved successfully");
	}

	@Override
	public List<Exam> listExams() {
		String url = WEB_SERVER_REST_URL+"/examAction/exams";
		
		ResponseEntity<Exam[]> responseEntity = restTemplate.getForEntity(url, Exam[].class);
		Exam[] newExams = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.info("Exam saved successfully, Exam Details="+newExams);
		}
		logger.info("Exam saved successfully");
		return Arrays.asList(newExams);
	}

	@Override
	public Exam getExamById(long examId) {
		String url = WEB_SERVER_REST_URL+"/examAction/exam/{id}";
		HttpEntity<String> requestEntity = prepareRequestEntity();
		
		ResponseEntity<Exam> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Exam.class);
		Exam newExam = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.info("Exam saved successfully, Exam Details="+newExam);
		}
		logger.info("Exam saved successfully");
		return newExam;
	}

	@Override
	public void removeExam(long examId) {
		String url = WEB_SERVER_REST_URL+"/examAction/exam/{id}";
		HttpEntity<String> requestEntity = prepareRequestEntity();
		
		restTemplate.exchange(url, HttpMethod.DELETE,requestEntity,Long.class,examId);
	}
}
