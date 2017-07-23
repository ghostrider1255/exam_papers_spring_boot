package com.sreepapers.app.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sreepapers.app.web.dao.QuestionDAO;
import com.sreepapers.app.web.model.Question;

public class QuestionDAOImpl implements QuestionDAO{

	private static final Logger logger = LoggerFactory.getLogger(QuestionDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addQuestion(Question question) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(question);
		logger.info("Question saved successfully, Question Details="+question);
	}

	@Override
	public void updateQuestion(Question question) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(question);
		logger.info("Question updated successfully, Question Details="+question);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> listQuestions() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Question> questionList = session.createQuery("from Question").list();
		for(Question question : questionList){
			logger.info("Question List::"+question);
		}
		return questionList;
	}

	@Override
	public Question getQuestionById(long questionId) {
		Session session = this.sessionFactory.getCurrentSession();		
		Question question = (Question) session.load(Question.class, new Long(questionId));
		logger.info("Question loaded successfully, Question details="+question);
		return question;
	}

	@Override
	public void removeQuestion(long questionId) {
		Session session = this.sessionFactory.getCurrentSession();
		Question question = (Question) session.load(Question.class, new Long(questionId));
		if(null != question){
			session.delete(question);
		}
		logger.info("question deleted successfully, question details="+question);
	}
	
	public List<Question> filterBySubject(long questionId){
		Session session = this.sessionFactory.getCurrentSession();
		List<Question> questionList = session.createQuery("select * from QUESTION q join QSUBJECTTAG qs on q.questionId = qs.questionId and qs.subjectId='"+questionId+"'; ").list();
		System.out.println(questionList.toString());
		/*Criteria subjectCriteria = session.createCriteria(Question.class, "question");
		subjectCriteria.createAlias("question.subjects", " subject");
		subjectCriteria.add(Restrictions.eq("subject.subjectId", questionId));*/
		//List<Question> questionList = subjectCriteria.list();
		return questionList;
	}
}
