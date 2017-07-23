package com.sreepapers.app.web.dao;

import java.util.List;

import com.sreepapers.app.web.model.Exam;

public interface ExamDAO {
	
	public void addExam(Exam exam);
	public void updateExam(Exam exam);
	public List<Exam> listExams();
	public Exam getExamById(long examId);
	public void removeExam(long examId);
}
