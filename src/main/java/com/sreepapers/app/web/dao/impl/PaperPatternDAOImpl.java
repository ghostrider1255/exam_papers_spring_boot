package com.sreepapers.app.web.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sreepapers.app.web.dao.PaperPatternDAO;
import com.sreepapers.app.web.model.ExamQuestion;
import com.sreepapers.app.web.model.PaperPattern;
import com.sreepapers.app.web.model.PatternSubjectRecord;
import com.sreepapers.app.web.model.Question;

public class PaperPatternDAOImpl implements PaperPatternDAO{

	private static final Logger logger = LoggerFactory.getLogger(PaperPatternDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addPaperPattern(PaperPattern paperPattern) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(paperPattern);
		logger.info("PaperPattern saved successfully, paperPattern Details="+paperPattern);
	}

	@Override
	public void updatePaperPattern(PaperPattern paperPattern) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(paperPattern);
		logger.info("PaperPattern updated successfully, paperPattern Details="+paperPattern);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaperPattern> listPaperPatterns() {
		Session session = this.sessionFactory.getCurrentSession();
		List<PaperPattern> paperPatternList = session.createQuery("from PaperPattern").list();
		for(PaperPattern paperPattern : paperPatternList){
			logger.info("paperPattern List::"+paperPattern);
		}
		return paperPatternList;
	}

	@Override
	public PaperPattern getPaperPatternById(long paperPatternId) {
		Session session = this.sessionFactory.getCurrentSession();		
		PaperPattern paperPattern = (PaperPattern) session.load(PaperPattern.class, new Long(paperPatternId));
		logger.info("PaperPatternId loaded successfully, paperPatternId details="+paperPattern);
		return paperPattern;
	}

	@Override
	public void removePaperPattern(long paperPatternId) {
		Session session = this.sessionFactory.getCurrentSession();
		PaperPattern paperPattern = (PaperPattern) session.load(PaperPattern.class, new Long(paperPatternId));
		if(null != paperPattern){
			session.delete(paperPattern);
		}
		logger.info("PaperPattern deleted successfully, paperPattern details="+paperPattern);
	}
	
	@Override
	public Map<String,ExamQuestion> getQuestionByPatternId(long patternId){
		
		Session session = this.sessionFactory.getCurrentSession();
		Map<String,ExamQuestion> questionsMapBySubject = new LinkedHashMap<String,ExamQuestion>();
		PaperPattern currentPattern = this.getPaperPatternById(patternId);
		for(PatternSubjectRecord patternSubject:currentPattern.getSubjectRules()){
			ExamQuestion examQuestion = new ExamQuestion();
			List<Question> questions = new ArrayList<Question>();
			long subjectId = patternSubject.getpSubject().getSubjectId();
			Query query = session.createQuery("from Question q join q.subjects s where s.subjectId=:subjectId");
			List<Object> questionsList = (List<Object>)query.setParameter("subjectId", subjectId).list();
			Iterator iterator = questionsList.iterator();
			while(iterator.hasNext()){
				Object[] obj = (Object[])iterator.next();
				if(obj[0] instanceof Question){
					Question tempQuestion = (Question)obj[0];
					questions.add(tempQuestion);
				}
			}
			if(questions.size()> patternSubject.getNumberOfQuestions()){
				questions = questions.subList(0, (int)patternSubject.getNumberOfQuestions());	
			}
			examQuestion.setQuestions(questions);
			questionsMapBySubject.put(patternSubject.getpSubject().getSubjectDesc(), examQuestion);
		}
		return questionsMapBySubject;
	}
}
