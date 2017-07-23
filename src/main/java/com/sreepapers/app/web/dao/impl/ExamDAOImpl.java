package com.sreepapers.app.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sreepapers.app.web.dao.ExamDAO;
import com.sreepapers.app.web.model.Exam;

public class ExamDAOImpl implements ExamDAO{

	private static final Logger logger = LoggerFactory.getLogger(ExamDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addExam(Exam exam) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(exam);
		logger.info("Exam saved successfully, Exam Details="+exam);
	}

	@Override
	public void updateExam(Exam exam) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(exam);
		logger.info("Exam updated successfully, exam Details="+exam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exam> listExams() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Exam> examsList = session.createQuery("from Exam").list();
		for(Exam exam : examsList){
			logger.info("Exams List::"+exam);
		}
		return examsList;
	}

	@Override
	public Exam getExamById(long examId) {
		Session session = this.sessionFactory.getCurrentSession();		
		Exam exam = (Exam) session.load(Exam.class, new Long(examId));
		logger.info("Exam loaded successfully, exam details="+exam);
		return exam;
	}

	@Override
	public void removeExam(long examId) {
		Session session = this.sessionFactory.getCurrentSession();
		Exam exam = (Exam) session.load(Exam.class, new Long(examId));
		if(null != exam){
			session.delete(exam);
		}
		logger.info("Exam deleted successfully, exam details="+exam);
	}
}
