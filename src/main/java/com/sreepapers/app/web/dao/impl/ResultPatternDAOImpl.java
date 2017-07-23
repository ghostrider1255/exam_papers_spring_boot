package com.sreepapers.app.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sreepapers.app.web.dao.ResultPatternDAO;
import com.sreepapers.app.web.model.ResultPattern;

public class ResultPatternDAOImpl implements ResultPatternDAO{

	private static final Logger logger = LoggerFactory.getLogger(ResultPatternDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addResultPattern(ResultPattern resultPattern) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(resultPattern);
		logger.info("ResultPattern saved successfully, resultPattern Details="+resultPattern);
	}

	@Override
	public void updateResultPattern(ResultPattern resultPattern) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(resultPattern);
		logger.info("ResultPattern updated successfully, resultPattern Details="+resultPattern);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultPattern> listResultPatterns() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ResultPattern> resultPatternsList = session.createQuery("from ResultPattern").list();
		for(ResultPattern resultPattern : resultPatternsList){
			logger.info("ResultPattern List::"+resultPattern);
		}
		return resultPatternsList;
	}

	@Override
	public ResultPattern getResultPatternById(long resultPatternId) {
		Session session = this.sessionFactory.getCurrentSession();		
		ResultPattern resultPattern = (ResultPattern) session.load(ResultPattern.class, new Long(resultPatternId));
		logger.info("ResultPattern loaded successfully, resultPattern details="+resultPattern);
		return resultPattern;
	}

	@Override
	public void removeResultPattern(long resultPatternId) {
		Session session = this.sessionFactory.getCurrentSession();
		ResultPattern resultPattern = (ResultPattern) session.load(ResultPattern.class, new Long(resultPatternId));
		if(null != resultPattern){
			session.delete(resultPattern);
		}
		logger.info("ResultPattern deleted successfully, resultPattern details="+resultPattern);
	}
}
