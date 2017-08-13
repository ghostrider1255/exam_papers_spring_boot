package com.sreepapers.app.web.dao.impl;

import static com.sreepapers.app.web.utils.properties.HelperProperties.POSTFIX;
import static com.sreepapers.app.web.utils.properties.HelperProperties.PREFIX;
import static com.sreepapers.app.web.utils.properties.HelperProperties.REST_SERVER_HOST_URL;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.sreepapers.app.web.dao.PaperPatternDAO;
import com.sreepapers.app.web.model.ExamQuestion;
import com.sreepapers.app.web.model.PaperPattern;
import com.sreepapers.app.web.model.PatternSubjectRecord;
import com.sreepapers.app.web.model.Question;
import com.sreepapers.app.web.utils.HelperUtils;

public class PaperPatternDAOImpl implements PaperPatternDAO{

	private static final Logger logger = LoggerFactory.getLogger(PaperPatternDAOImpl.class);
	
	public static final String SAVE_PAPER_PATTERN = "/patternAction/savePaperPattern";
	public static final String GET_PAPER_PATTERNS="/patternAction/PaperPatterns";
	public static final String DELETE_PAPER_PATTERN="/patternAction/deletePaperPattern/{id}";
	public static final String GET_PAPER_PATTERN="/patternAction/PaperPattern/{id}";
	public static final String UPDATE_PAPER_PATTERN="/patternAction/updatePaperPattern";
	
	@Value(PREFIX+REST_SERVER_HOST_URL+POSTFIX)
	private String webServerRestUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public void addPaperPattern(PaperPattern paperPattern) {
		
		String url = webServerRestUrl+SAVE_PAPER_PATTERN;
		HttpEntity<PaperPattern> requestEntity = HelperUtils.prepareRequestEntity(paperPattern);
		
		ResponseEntity<PaperPattern> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,PaperPattern.class);
		PaperPattern newPaperPattern = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("PaperPattern saved, PaperPattern Details={}",newPaperPattern);
		}else{
			logger.info("PaperPattern saved successfully");
		}
	}

	@Override
	public void updatePaperPattern(PaperPattern paperPattern) {
		String url = webServerRestUrl+UPDATE_PAPER_PATTERN;
		HttpEntity<PaperPattern> requestEntity = HelperUtils.prepareRequestEntity(paperPattern);
		
		ResponseEntity<PaperPattern> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,PaperPattern.class);
		PaperPattern newPaperPattern = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("PaperPattern updated , PaperPattern Details={}",newPaperPattern);
		}
		else{
			logger.info("PaperPattern updated successfully");
		}
	}

	@Override
	public List<PaperPattern> listPaperPatterns() {
		String url = webServerRestUrl+GET_PAPER_PATTERNS;
		
		ResponseEntity<PaperPattern[]> responseEntity = restTemplate.getForEntity(url, PaperPattern[].class);
		PaperPattern[] newPaperPatterns = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("returning PaperPatterns List of count, {}",newPaperPatterns.length);
		}
		else{
			logger.info("returning PaperPattern list successfully");
		}
		return Arrays.asList(newPaperPatterns);
	}

	@Override
	public PaperPattern getPaperPatternById(long paperPatternId) {
		String url = webServerRestUrl+GET_PAPER_PATTERN;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		
		ResponseEntity<PaperPattern> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,PaperPattern.class,paperPatternId);
		PaperPattern newPaperPattern = responseEntity.getBody();
		if(logger.isDebugEnabled()){
			logger.debug("returning PaperPattern object, PaperPattern Details={}",newPaperPattern);
		}
		else{
			logger.info("returning PaperPattern object");
		}
		return newPaperPattern;
	}

	@Override
	public void removePaperPattern(long paperPatternId) {
		String url = webServerRestUrl+DELETE_PAPER_PATTERN;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(header);
		restTemplate.exchange(url, HttpMethod.DELETE,requestEntity,PaperPattern.class,paperPatternId);
	}
	
	@Override
	public Map<String,ExamQuestion> getQuestionByPatternId(long patternId){
		
		Map<String,ExamQuestion> questionsMapBySubject = new LinkedHashMap<>();
		PaperPattern currentPattern = this.getPaperPatternById(patternId);
		for(PatternSubjectRecord patternSubject:currentPattern.getSubjectRules()){
			ExamQuestion examQuestion = new ExamQuestion();
			long subjectId = patternSubject.getpSubject().getSubjectId();
			
			String url = webServerRestUrl+QuestionDAOImpl.GET_QUESTIONS_BY_SUBJECT;
			
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requestEntity = new HttpEntity<>(header);
			
			ResponseEntity<Question[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Question[].class,subjectId);
			Question[] newQuestion = responseEntity.getBody();
			
			/*List<Object> questionsList = (List<Object>)query.setParameter("subjectId", subjectId).list();
			Iterator iterator = questionsList.iterator();
			while(iterator.hasNext()){
				Object[] obj = (Object[])iterator.next();
				if(obj[0] instanceof Question){
					Question tempQuestion = (Question)obj[0];
					questions.add(tempQuestion);
				}
			}*/
			/*if(questions.size()> patternSubject.getNumberOfQuestions()){
				questions = questions.subList(0, patternSubject.getNumberOfQuestions());	
			}*/
			examQuestion.setQuestions(Arrays.asList(newQuestion));
			questionsMapBySubject.put(patternSubject.getpSubject().getSubjectDesc(), examQuestion);
		}
		return questionsMapBySubject;
	}
}
