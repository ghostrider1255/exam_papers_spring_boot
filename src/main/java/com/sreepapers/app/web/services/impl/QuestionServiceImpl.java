package com.sreepapers.app.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sreepapers.app.web.dao.QuestionDAO;
import com.sreepapers.app.web.model.Question;
import com.sreepapers.app.web.services.QuestionService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionDAO questionDAO;
	
	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}
	
	@Override
	@Transactional
	public void addQuestion(Question question) {
		this.questionDAO.addQuestion(question);
		
	}

	@Override
	@Transactional
	public void updateQuestion(Question question) {
		this.questionDAO.updateQuestion(question);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Question> listQuestions() {
		return this.questionDAO.listQuestions();
	}

	@Override
	@Transactional
	public Question getQuestionById(long questionId) {
		return this.questionDAO.getQuestionById(questionId);
	}

	@Override
	@Transactional
	public void removeQuestion(long questionId) {
		this.questionDAO.removeQuestion(questionId);
		
	}
	
	@Override
	@Transactional
	public List<Question> filterBySubject(long questionId){
		return this.questionDAO.filterBySubject(questionId);
	}

}
