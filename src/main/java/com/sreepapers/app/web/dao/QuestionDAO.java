package com.sreepapers.app.web.dao;

import java.util.List;

import com.sreepapers.app.web.model.Question;

public interface QuestionDAO {
	
	public void addQuestion(Question question);
	public void updateQuestion(Question question);
	public List<Question> listQuestions();
	public Question getQuestionById(long id);
	public void removeQuestion(long id);
	public List<Question> filterBySubject(long questionId);
}
