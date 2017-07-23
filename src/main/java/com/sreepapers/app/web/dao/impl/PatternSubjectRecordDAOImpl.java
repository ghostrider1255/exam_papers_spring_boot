package com.sreepapers.app.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sreepapers.app.web.dao.PatternSubjectRecordDAO;
import com.sreepapers.app.web.model.PatternSubjectRecord;

public class PatternSubjectRecordDAOImpl implements PatternSubjectRecordDAO{

	private static final Logger logger = LoggerFactory.getLogger(PatternSubjectRecordDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addPatternSubjectRecord(PatternSubjectRecord patternSubjectRecord) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(patternSubjectRecord);
		logger.info("PatternSubjectRecord saved successfully, patternSubjectRecord Details="+patternSubjectRecord);
	}

	@Override
	public void updatePatternSubjectRecord(PatternSubjectRecord patternSubjectRecord) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(patternSubjectRecord);
		logger.info("PatternSubjectRecord updated successfully, patternSubjectRecord Details="+patternSubjectRecord);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PatternSubjectRecord> listPatternSubjectRecords() {
		Session session = this.sessionFactory.getCurrentSession();
		List<PatternSubjectRecord> patternSubjectRecordList = session.createQuery("from PatternSubjectRecord").list();
		for(PatternSubjectRecord patternSubjectRecord : patternSubjectRecordList){
			logger.info("PaperPattern List::"+patternSubjectRecord);
		}
		return patternSubjectRecordList;
	}

	@Override
	public PatternSubjectRecord getPatternSubjectRecordById(long patternSubjectRecordId) {
		Session session = this.sessionFactory.getCurrentSession();		
		PatternSubjectRecord patternSubjectRecord = (PatternSubjectRecord) session.load(PatternSubjectRecord.class, new Long(patternSubjectRecordId));
		logger.info("PatternSubjectRecord loaded successfully, patternSubjectRecord details="+patternSubjectRecord);
		return patternSubjectRecord;
	}

	@Override
	public void removePatternSubjectRecord(long patternSubjectRecordId) {
		Session session = this.sessionFactory.getCurrentSession();
		PatternSubjectRecord patternSubjectRecord = (PatternSubjectRecord) session.load(PatternSubjectRecord.class, new Long(patternSubjectRecordId));
		if(null != patternSubjectRecord){
			session.delete(patternSubjectRecord);
		}
		logger.info("PatternSubjectRecord deleted successfully, patternSubjectRecord details="+patternSubjectRecord);
	}
}
