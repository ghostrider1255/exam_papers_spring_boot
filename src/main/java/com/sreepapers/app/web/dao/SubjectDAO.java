package com.sreepapers.app.web.dao;

import java.util.List;

import com.sreepapers.app.web.model.Subject;

public interface SubjectDAO {
	
	public void addSubject(Subject subject);
	public void updateSubject(Subject subject);
	public List<Subject> listSubjects();
	public Subject getSubjectById(long id);
	public void removeSubject(long id);
}
