package com.sreepapers.app.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sreepapers.app.web.dao.SubjectDAO;
import com.sreepapers.app.web.model.Subject;

public class SubjectDAOImpl implements SubjectDAO{

	private static final Logger logger = LoggerFactory.getLogger(SubjectDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addSubject(Subject subject) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(subject);
		logger.info("Subject saved successfully, Subject Details="+subject);
	}

	@Override
	public void updateSubject(Subject subject) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(subject);
		logger.info("subject updated successfully, subject Details="+subject);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> listSubjects() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Subject> subjectList = session.createQuery("from Subject").list();
		for(Subject subject : subjectList){
			logger.info("Subject ::"+subject);
		}
		return subjectList;
	}

	@Override
	public Subject getSubjectById(long id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Subject subject = (Subject) session.load(Subject.class, new Long(id));
		logger.info("Subject loaded successfully, Subject details="+subject);
		return subject;
	}

	@Override
	public void removeSubject(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Subject subject = (Subject) session.load(Subject.class, new Long(id));
		if(null != subject){
			session.delete(subject);
		}
		logger.info("subject deleted successfully, Subject details="+subject);
	}
}
