package com.sreepapers.app.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sreepapers.app.web.dao.ExamQuestionDAO;
import com.sreepapers.app.web.model.ExamQuestion;

public class ExamQuestionDAOImpl implements ExamQuestionDAO{

	private static final Logger logger = LoggerFactory.getLogger(ExamQuestionDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addExamQuestion(ExamQuestion examQuestion) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(examQuestion);
		logger.info("ExamQuestion saved successfully, Exam Details="+examQuestion);
	}

	@Override
	public void updateExamQuestion(ExamQuestion examQuestion) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(examQuestion);
		logger.info("ExamQuestion updated successfully, exam Details="+examQuestion);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamQuestion> listExamQuestions() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ExamQuestion> examQuestionsList = session.createQuery("from ExamQuestion").list();
		for(ExamQuestion examQuestion : examQuestionsList){
			logger.info("ExamQuestions List::"+examQuestion);
		}
		return examQuestionsList;
	}

	@Override
	public ExamQuestion getExamQuestionById(long examQuestionId) {
		Session session = this.sessionFactory.getCurrentSession();		
		ExamQuestion examQuestion = (ExamQuestion) session.load(ExamQuestion.class, new Long(examQuestionId));
		logger.info("ExamQuestion loaded successfully, examQuestion details="+examQuestion);
		return examQuestion;
	}

	@Override
	public void removeExamQuestion(long examQuestionId) {
		Session session = this.sessionFactory.getCurrentSession();
		ExamQuestion examQuestion = (ExamQuestion) session.load(ExamQuestion.class, new Long(examQuestionId));
		if(null != examQuestion){
			session.delete(examQuestion);
		}
		logger.info("ExamQuestion deleted successfully, exam details="+examQuestion);
	}
}
