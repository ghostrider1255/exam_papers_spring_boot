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

import com.sreepapers.app.web.dao.PatternSubjectRecordDAO;
import com.sreepapers.app.web.model.PatternSubjectRecord;
import com.sreepapers.app.web.utils.HelperUtils;

public class PatternSubjectRecordDAOImpl implements PatternSubjectRecordDAO{

	private static final Logger logger = LoggerFactory.getLogger(PatternSubjectRecordDAOImpl.class);
	
	public static final String SAVE_PATTERN_SUBJECT_REOCRD = "/patternSubRecAction/savePatternSubjectRecord";
	public static final String GET_PATTERN_SUBJECT_REOCRD_LIST ="/patternSubRecAction/patternSubjectRecords";
	public static final String DELETE_PATTERN_SUBJECT_REOCRD ="/patternSubRecAction/deletePatternSubjectRecord/{id}";
	public static final String GET_PATTERN_SUBJECT_REOCRD ="/patternSubRecAction/patternSubjectRecord/{id}";
	public static final String UPDATE_PATTERN_SUBJECT_REOCRD ="/patternSubRecAction/updatePatternSubjectRecord";
	
	@Value(PREFIX+REST_SERVER_HOST_URL+POSTFIX)
	private String webServerRestUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public void addPatternSubjectRecord(PatternSubjectRecord patternSubjectRecord) {
		String url = webServerRestUrl+SAVE_PATTERN_SUBJECT_REOCRD;
		HttpEntity<PatternSubjectRecord> requestEntity = HelperUtils.prepareRequestEntity(patternSubjectRecord);
		
		ResponseEntity<PatternSubjectRecord> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,PatternSubjectRecord.class);
		PatternSubjectRecord newPatternSubjectRecord = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("PatternSubjectRecord saved, PatternSubjectRecord Details={}",newPatternSubjectRecord);
		}else{
			logger.info("PatternSubjectRecord saved successfully");
		}
	}

	@Override
	public void updatePatternSubjectRecord(PatternSubjectRecord patternSubjectRecord) {
		String url = webServerRestUrl+UPDATE_PATTERN_SUBJECT_REOCRD;
		HttpEntity<PatternSubjectRecord> requestEntity = HelperUtils.prepareRequestEntity(patternSubjectRecord);
		
		ResponseEntity<PatternSubjectRecord> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,PatternSubjectRecord.class);
		PatternSubjectRecord newPatternSubjectRecord = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("PatternSubjectRecord updated , PatternSubjectRecord Details={}",newPatternSubjectRecord);
		}
		else{
			logger.info("PatternSubjectRecord updated successfully");
		}
	}

	@Override
	public List<PatternSubjectRecord> listPatternSubjectRecords() {
		String url = webServerRestUrl+GET_PATTERN_SUBJECT_REOCRD_LIST;
		
		ResponseEntity<PatternSubjectRecord[]> responseEntity = restTemplate.getForEntity(url, PatternSubjectRecord[].class);
		PatternSubjectRecord[] newPatternSubjectRecord = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("returning PatternSubjectRecord List of count, {}",newPatternSubjectRecord.length);
		}
		else{
			logger.info("returning PatternSubjectRecord list successfully");
		}
		return Arrays.asList(newPatternSubjectRecord);
	}

	@Override
	public PatternSubjectRecord getPatternSubjectRecordById(long patternSubjectRecordId) {
		String url = webServerRestUrl+GET_PATTERN_SUBJECT_REOCRD;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<PatternSubjectRecord> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,PatternSubjectRecord.class,patternSubjectRecordId);
		PatternSubjectRecord newPatternSubjectRecord = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("returning PatternSubjectRecord object, PatternSubjectRecord Details={}",newPatternSubjectRecord);
		}
		else{
			logger.info("returning PatternSubjectRecord object");
		}
		return newPatternSubjectRecord;
	}

	@Override
	public void removePatternSubjectRecord(long patternSubjectRecordId) {
		String url = webServerRestUrl+DELETE_PATTERN_SUBJECT_REOCRD;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		restTemplate.exchange(url, HttpMethod.DELETE,requestEntity,PatternSubjectRecord.class,patternSubjectRecordId);
	}
}
