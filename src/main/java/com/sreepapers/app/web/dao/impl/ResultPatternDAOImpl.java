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

import com.sreepapers.app.web.dao.ResultPatternDAO;
import com.sreepapers.app.web.model.ResultPattern;
import com.sreepapers.app.web.utils.HelperUtils;

public class ResultPatternDAOImpl implements ResultPatternDAO{

	private static final Logger logger = LoggerFactory.getLogger(ResultPatternDAOImpl.class);
	
	public static final String SAVE_RESULT_PATTERN = "/resultPatternAction/resultPattern";
	public static final String GET_RESULT_PATTERN_LIST ="/resultPatternAction/resultPatterns";
	public static final String DELETE_RESULT_PATTER ="/resultPatternAction/deleteResultPattern/{id}";
	public static final String GET_RESULT_PATTERN ="/resultPatternAction/resultPattern/{id}";
	public static final String UPDATE_RESULT_PATTERN ="/resultPatternAction/updateResultPattern/{id}";
	
	@Value(PREFIX+REST_SERVER_HOST_URL+POSTFIX)
	private String webServerRestUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Override
	public void addResultPattern(ResultPattern resultPattern) {
		String url = webServerRestUrl+SAVE_RESULT_PATTERN;
		HttpEntity<ResultPattern> requestEntity = HelperUtils.prepareRequestEntity(resultPattern);
		
		ResponseEntity<ResultPattern> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,ResultPattern.class);
		ResultPattern newResultPattern = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("ResultPattern saved, Question Details={}",newResultPattern);
		}else{
			logger.info("ResultPattern saved successfully");
		}
	}

	@Override
	public void updateResultPattern(ResultPattern resultPattern) {
		String url = webServerRestUrl+UPDATE_RESULT_PATTERN;
		HttpEntity<ResultPattern> requestEntity = HelperUtils.prepareRequestEntity(resultPattern);
		
		ResponseEntity<ResultPattern> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,ResultPattern.class);
		ResultPattern newResultPattern = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("ResultPattern updated, ResultPattern Details={}",newResultPattern);
		}else{
			logger.info("ResultPattern updated successfully");
		}
	}

	@Override
	public List<ResultPattern> listResultPatterns() {
		String url = webServerRestUrl+GET_RESULT_PATTERN_LIST;
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<ResultPattern[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,ResultPattern[].class);
		ResultPattern[] newResultPattern = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("ResultPattern list, Question length={}",newResultPattern.length);
		}else{
			logger.info("returning ResultPattern list");
		}
		return Arrays.asList(newResultPattern);
	}

	@Override
	public ResultPattern getResultPatternById(long resultPatternId) {
		String url = webServerRestUrl+GET_RESULT_PATTERN;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<ResultPattern> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,ResultPattern.class,resultPatternId);
		ResultPattern newResultPattern = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("returning ResultPattern object, ResultPattern Details={}",newResultPattern);
		}
		else{
			logger.info("returning ResultPattern object");
		}
		return newResultPattern;
	}

	@Override
	public void removeResultPattern(long resultPatternId) {
		String url = webServerRestUrl+DELETE_RESULT_PATTER;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		restTemplate.exchange(url, HttpMethod.DELETE,requestEntity,ResultPattern.class,resultPatternId);
	}
}
