package com.sreepapers.app.web.services;

import java.util.List;

import com.sreepapers.app.web.model.ExamQuestion;

public interface ExamQuestionService {
	public void addExamQuestion(ExamQuestion examQuestion);
	public void updateExamQuestion(ExamQuestion examQuestion);
	public List<ExamQuestion> listExamQuestions();
	public ExamQuestion getExamQuestionById(long examQuestionId);
	public void removeExamQuestion(long examQuestionId);
}
